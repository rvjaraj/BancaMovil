/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.emtitis;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "transferencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferencias.findAll", query = "SELECT t FROM Transferencias t"),
    @NamedQuery(name = "Transferencias.findById", query = "SELECT t FROM Transferencias t WHERE t.id = :id"),
    @NamedQuery(name = "Transferencias.findByFecha", query = "SELECT t FROM Transferencias t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Transferencias.findByCantidad", query = "SELECT t FROM Transferencias t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Transferencias.findByConcepto", query = "SELECT t FROM Transferencias t WHERE t.concepto = :concepto")})
public class Transferencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Column(name = "concepto")
    private String concepto;
    @JoinColumn(name = "Ordenante", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cuenta ordenante;
    @JoinColumn(name = "Beneficiario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cuenta beneficiario;

    public Transferencias() {
    }

    public Transferencias(Integer id) {
        this.id = id;
    }

    public Transferencias(Integer id, Date fecha, double cantidad, String concepto) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.concepto = concepto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Cuenta getOrdenante() {
        return ordenante;
    }

    public void setOrdenante(Cuenta ordenante) {
        this.ordenante = ordenante;
    }

    public Cuenta getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Cuenta beneficiario) {
        this.beneficiario = beneficiario;
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
        if (!(object instanceof Transferencias)) {
            return false;
        }
        Transferencias other = (Transferencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.proyecto.emtitis.Transferencias[ id=" + id + " ]";
    }
    
}
