package io.github.jokoframework.porandu.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jokoframework.porandu.Application;
import io.github.jokoframework.porandu.entities.LectureEntity;
import io.github.jokoframework.porandu.repositories.LecturesRepository;
import io.github.jokoframework.porandu.repositories.VotesRepository;
import io.github.jokoframework.porandu.rest.ApiPaths;
import io.github.jokoframework.porandu.service.QuestionsService;
import io.github.jokoframework.porandu.web.dto.response.QuestionResponseDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class QuestionsIntegrationTest {
    private Long lectureId;

    private List<QuestionResponseDTO> questions;

    @Autowired
    private LecturesRepository lecturesRepository;

    @Autowired
    private VotesRepository votesRepository;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private static final String host = "http://localhost:8181/";

    @Before
    public void init() {
        List<LectureEntity> lectures = lecturesRepository.findAll();
        Assert.assertFalse(lectures.isEmpty());
        lectureId = lectures.get(0).getLectureId();

        questions = questionsService.findByLecture(lectureId);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

    }

    @Test
    public void testGetVotes() throws Exception {
        MvcResult result = this.mockMvc.perform(get(ApiPaths.ROOT_QUESTIONS).accept(MediaType.APPLICATION_JSON)
                .param("lectureId", lectureId.toString()))
                .andExpect(status().isOk()).andReturn();

        List<QuestionResponseDTO> questionList = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<QuestionResponseDTO>>() {});

        Assert.assertFalse(questionList.isEmpty());
        Assert.assertEquals(questions.size(), questionList.size());
        for (int i = 0; i < questionList.size(); i++) {
            Assert.assertEquals(questionList.get(i).getTitle(), questions.get(i).getTitle());
            Assert.assertEquals(questionList.get(i).getDetail(), questions.get(i).getDetail());
            Assert.assertEquals(questionList.get(i).getQuestionId(), questions.get(i).getQuestionId());
            Assert.assertNotNull(questionList.get(i).getVotes());
        }

    }

    @Test
    public void testVote() throws Exception {
        Long questionId = questions.get(0).getQuestionId();
        Long initial = votesRepository.countByQuestionQuestionId(questionId);

        MvcResult result = this.mockMvc.perform(post(ApiPaths.QUESTION_VOTE.replace("{questionId}", questionId.toString()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


        QuestionResponseDTO question = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionResponseDTO.class);
        Assert.assertNotNull(question);
        Assert.assertNotNull(question.getVotes());
        Assert.assertTrue(question.isVoted());
        Assert.assertTrue(question.getVotes() > initial);
    }

    @Test
    public void testDownVote() throws Exception {
        Long questionId = questions.get(0).getQuestionId();
        Long initial = votesRepository.countByQuestionQuestionId(questionId);

        MvcResult result = this.mockMvc.perform(post(ApiPaths.QUESTION_VOTE.replace("{questionId}", questionId.toString()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        QuestionResponseDTO question = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionResponseDTO.class);
        Assert.assertNotNull(question);
        Assert.assertNotNull(question.getVotes());
        if (initial > 0) {
            Assert.assertTrue(question.getVotes() < initial);
        }
    }

}