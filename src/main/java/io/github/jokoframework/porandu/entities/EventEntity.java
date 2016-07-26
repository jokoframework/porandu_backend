package io.github.jokoframework.porandu.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * Created by afeltes on 25/07/16.
 */
@Entity
@Table(name = "events", schema = "porandu")
@SequenceGenerator(name = "event_id_seq", sequenceName = "porandu.event_id_seq", schema = "porandu", initialValue = 1, allocationSize = 1)
public class EventEntity extends BaseEntity {

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "event_id_seq")
    @Column(name = "event_id")
    private Long eventId;

    @Basic
    private String code;

    @Basic
    @Column(length = 4096)
    private String description;

    @Basic
    @Column(length = 2048)
    private String imageUrl;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long pId) {
        eventId = pId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String pImageUrl) {
        imageUrl = pImageUrl;
    }

    @Override
    public Long getId() {
        return eventId;
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
        EventEntity rhs = (EventEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.eventId, rhs.eventId)
                .append(this.code, rhs.code)
                .append(this.description, rhs.description)
                .append(this.imageUrl, rhs.imageUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(eventId)
                .append(code)
                .append(description)
                .append(imageUrl)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("eventId", eventId)
                .append("code", code)
                .append("description", description)
                .append("imageUrl", imageUrl)
                .toString();
    }
}
