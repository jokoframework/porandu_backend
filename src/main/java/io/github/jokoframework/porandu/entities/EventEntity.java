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
    private Long id;

    @Basic
    private String code;

    @Basic
    private String description;

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
                .append(this.id, rhs.id)
                .append(this.code, rhs.code)
                .append(this.description, rhs.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(code)
                .append(description)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("code", code)
                .append("description", description)
                .toString();
    }
}
