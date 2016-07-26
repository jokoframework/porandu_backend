package io.github.jokoframework.porandu.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * Created by afeltes on 25/07/16.
 */
@Entity
@Table(name = "lectures", schema = "porandu")
@SequenceGenerator(name = "lecture_id_seq", sequenceName = "porandu.lecture_id_seq", schema = "porandu", initialValue = 1, allocationSize = 1)
public class LectureEntity extends BaseEntity {

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "lecture_id_seq")
    @Column(name = "lecture_id")
    private Long lectureId;

    @Basic
    private String code;

    @Basic
    @Column(length = 4096)
    private String description;

    @Basic
    @Column(length = 2048)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private PersonEntity author;

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long pId) {
        lectureId = pId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String pCode) {
        code = pCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        description = pDescription;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity pEvent) {
        event = pEvent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String pImageUrl) {
        imageUrl = pImageUrl;
    }

    public PersonEntity getAuthor() {
        return author;
    }

    public void setAuthor(PersonEntity pAuthor) {
        author = pAuthor;
    }

    @Override
    public Long getId() {
        return lectureId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        LectureEntity rhs = (LectureEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.lectureId, rhs.lectureId)
                .append(this.code, rhs.code)
                .append(this.description, rhs.description)
                .append(this.imageUrl, rhs.imageUrl)
                .append(this.event, rhs.event)
                .append(this.author, rhs.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(lectureId)
                .append(code)
                .append(description)
                .append(imageUrl)
                .append(event)
                .append(author)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("lectureId", lectureId)
                .append("code", code)
                .append("description", description)
                .append("imageUrl", imageUrl)
                .append("event", event)
                .append("author", author)
                .toString();
    }
}
