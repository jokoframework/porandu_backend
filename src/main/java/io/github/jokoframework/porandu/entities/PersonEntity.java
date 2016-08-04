package io.github.jokoframework.porandu.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * Created by afeltes on 25/07/16.
 */
@Entity
@Table(name = "persons", schema = "porandu")
@SequenceGenerator(name = "person_id_seq", sequenceName = "porandu.person_id_seq", schema = "porandu", initialValue = 1, allocationSize = 1)
public class PersonEntity extends BaseEntity {

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_id_seq")
    @Column(name = "person_id")
    private Long personId;

    @Basic
    @Column(name = "full_name")
    private String fullName;

    @Basic
    private String email;
    
    @Basic
    @Column(name = "link_social")
    private String linkSocial;
    
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long pId) {
        personId = pId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String pFullName) {
        fullName = pFullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }
    

    public String getLinkSocial() {
		return linkSocial;
	}

	public void setLinkSocial(String linkSocial) {
		this.linkSocial = linkSocial;
	}

	@Override
    public Long getId() {
        return personId;
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
        PersonEntity rhs = (PersonEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.personId, rhs.personId)
                .append(this.fullName, rhs.fullName)
                .append(this.email, rhs.email)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(personId)
                .append(fullName)
                .append(email)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("personId", personId)
                .append("fullName", fullName)
                .append("email", email)
                .toString();
    }
}
