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
@Table(name = "parametros", schema = "sys")
@SequenceGenerator(name = "parametro_id_seq", sequenceName = "sys.parametro_id_seq", schema = "sys", initialValue = 1, allocationSize = 1)
public class ParametroEntity extends BaseEntity {


    @Id
    @Basic
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "parametro_id_seq")
    private Long id;

    @Basic
    @Column(name = "nombre")
    private String nombre;

    @Basic
    @Column(name = "valor")
    private String valor;


    @Basic
    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoParametroEnum tipo;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoParametroEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoParametroEnum tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        ParametroEntity rhs = (ParametroEntity) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.nombre, rhs.nombre)
                .append(this.valor, rhs.valor)
                .append(this.tipo, rhs.tipo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(nombre)
                .append(valor)
                .append(tipo)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("nombre", nombre)
                .append("valor", valor)
                .append("tipo", tipo)
                .toString();
    }
}
