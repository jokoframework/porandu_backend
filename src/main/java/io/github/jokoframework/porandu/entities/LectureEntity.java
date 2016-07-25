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
    private Long id;

    @Basic
    private String code;

    @Basic
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private PersonEntity author;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
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
                .append(this.id, rhs.id)
                .append(this.code, rhs.code)
                .append(this.description, rhs.description)
                .append(this.event, rhs.event)
                .append(this.author, rhs.author)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(code)
                .append(description)
                .append(event)
                .append(author)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("code", code)
                .append("description", description)
                .append("event", event)
                .append("author", author)
                .toString();
    }
}
