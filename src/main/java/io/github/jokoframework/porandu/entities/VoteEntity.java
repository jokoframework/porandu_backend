package io.github.jokoframework.porandu.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author agimenez
 */
@Entity
@Table(name = "votes", schema = "porandu")
@SequenceGenerator(name = "vote_id_seq", sequenceName = "porandu.vote_id_seq", schema = "porandu", initialValue = 1, allocationSize = 1)
public class VoteEntity extends BaseEntity {
    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "vote_id_seq")
    @Column(name = "vote_id")
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public Long getId() {
        return voteId;
    }

    public void setId(Long voteId) {
        this.voteId = voteId;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        VoteEntity that = (VoteEntity) o;

        return new EqualsBuilder()
                .append(voteId, that.voteId)
                .append(question, that.question)
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(voteId).append(question).append(user).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("voteId", voteId)
                .append("question", question)
                .append("user", user)
                .toString();
    }
}
