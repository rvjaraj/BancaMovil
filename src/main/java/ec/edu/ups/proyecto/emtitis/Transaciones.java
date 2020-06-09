/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.emtitis;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicio
 */
@Entity
@Table(name = "transaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaciones.findAll", query = "SELECT t FROM Transaciones t"),
    @NamedQuery(name = "Transaciones.findByCedula", query = "SELECT t FROM Transaciones t WHERE t.cuentaid.cliente.cedula = :cedula ORDER BY t.id DESC"),
    @NamedQuery(name = "Transaciones.findById", query = "SELECT t FROM Transaciones t WHERE t.id = :id"),
    @NamedQuery(name = "Transaciones.findByTipo", query = "SELECT t FROM Transaciones t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Transaciones.findByCantidad", query = "SELECT t FROM Transaciones t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Transaciones.findByFecha", query = "SELECT t FROM Transaciones t WHERE t.cuentaid.cliente.cedula = :cedula AND t.fecha BETWEEN :fecha1 AND :fecha2  ")})
public class Transaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "cantidad")
    private String cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Cuenta_id", referencedColumnName = "id")
    @ManyToOne( fetch = FetchType.EAGER)
    private Cuenta cuentaid;

    public Transaciones() {
    }

    public Transaciones(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuentaid() {
        return cuentaid;
    }

    public void setCuentaid(Cuenta cuentaid) {
        this.cuentaid = cuentaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaciones)) {
            return false;
        }
        Transaciones other = (Transaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.proyecto.emtitis.Transaciones[ id=" + id + " ]";
    }
    
}
