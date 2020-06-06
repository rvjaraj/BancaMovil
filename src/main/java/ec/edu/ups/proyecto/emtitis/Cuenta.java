/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.emtitis;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vinicio
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c"),
    @NamedQuery(name = "Cuenta.findById", query = "SELECT c FROM Cuenta c WHERE c.id = :id"),
    @NamedQuery(name = "Cuenta.findByNumero", query = "SELECT c FROM Cuenta c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cuenta.findByFecha", query = "SELECT c FROM Cuenta c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cuenta.findByEliminado", query = "SELECT c FROM Cuenta c WHERE c.eliminado = :eliminado")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "eliminado")
    private boolean eliminado;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta", fetch = FetchType.EAGER)
    private List<Credito> creditoList;
    @OneToMany( mappedBy = "cuentaid")
    private List<Transaciones> transacionesList;
    @OneToMany(mappedBy = "ordenante")
    private List<Transferencias> transferenciasList;
    @OneToMany(mappedBy = "beneficiario")
    private List<Transferencias> transferenciasList1;

    public Cuenta() {
    }

    public Cuenta(Integer id) {
        this.id = id;
    }

    public Cuenta(Integer id, String numero, double saldo, Date fecha) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public List<Credito> getCreditoList() {
        return creditoList;
    }

    public void setCreditoList(List<Credito> creditoList) {
        this.creditoList = creditoList;
    }

    @XmlTransient
    public List<Transaciones> getTransacionesList() {
        return transacionesList;
    }

    public void setTransacionesList(List<Transaciones> transacionesList) {
        this.transacionesList = transacionesList;
    }

    @XmlTransient
    public List<Transferencias> getTransferenciasList() {
        return transferenciasList;
    }

    public void setTransferenciasList(List<Transferencias> transferenciasList) {
        this.transferenciasList = transferenciasList;
    }

    @XmlTransient
    public List<Transferencias> getTransferenciasList1() {
        return transferenciasList1;
    }

    public void setTransferenciasList1(List<Transferencias> transferenciasList1) {
        this.transferenciasList1 = transferenciasList1;
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
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.proyecto.emtitis.Cuenta[ id=" + id + " ]";
    }
    
}
