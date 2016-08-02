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
@Table(name = "users", schema = "porandu")
@SequenceGenerator(name = "user_id_seq", sequenceName = "porandu.user_id_seq", schema = "porandu", initialValue = 1, allocationSize = 1)
public class UserEntity extends BaseEntity {

    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(length = 256)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = true)
    private PersonEntity person;

    @Override
    public Long getId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UserEntity that = (UserEntity) o;

        return new EqualsBuilder().append(userId, that.userId).append(userName, that.userName)
                .append(person, that.person).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(userName).append(person).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("userId", userId)
                .append("userName", userName)
                .append("person", person)
                .toString();
    }
}
