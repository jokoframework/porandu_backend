package io.github.jokoframework.porandu.entities;

import io.github.jokoframework.porandu.enums.TipoParametroEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * Entidad que representa un parametro.
 */

@Entity
@Table(name = "parameters", schema = "sys")
@SequenceGenerator(name = "parameter_id_seq", sequenceName = "sys.parameter_id_seq", schema = "sys", initialValue = 1, allocationSize = 1)
public class ParametersEntity extends BaseEntity {


    @Id
    @Basic
    @Column(name = "parameter_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "parameter_id_seq")
    private Long parameterId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "value")
    private String value;


    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TipoParametroEnum type;

    @Override
    public Long getId() {
        return parameterId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long pParameterId) {
        parameterId = pParameterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String pValue) {
        value = pValue;
    }

    public TipoParametroEnum getType() {
        return type;
    }

    public void setType(TipoParametroEnum pType) {
        type = pType;
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
        ParametersEntity rhs = (ParametersEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.parameterId, rhs.parameterId)
                .append(this.name, rhs.name)
                .append(this.value, rhs.value)
                .append(this.type, rhs.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(parameterId)
                .append(name)
                .append(value)
                .append(type)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("parameterId", parameterId)
                .append("name", name)
                .append("value", value)
                .append("type", type)
                .toString();
    }
}
