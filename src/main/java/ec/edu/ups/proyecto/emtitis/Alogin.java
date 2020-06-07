/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.emtitis;

import java.io.Serializable;
import java.net.InetAddress;
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
@Table(name = "alogin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alogin.findAll", query = "SELECT a FROM Alogin a"),
    @NamedQuery(name = "Alogin.findById", query = "SELECT a FROM Alogin a WHERE a.id = :id"),
    @NamedQuery(name = "Alogin.findByCedula", query = "SELECT a FROM Alogin a, Cliente c WHERE a.cliente = c and c.cedula = :cedula"),
    @NamedQuery(name = "Alogin.findByFecha", query = "SELECT a FROM Alogin a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Alogin.findByIp", query = "SELECT a FROM Alogin a WHERE a.ip = :ip"),
    @NamedQuery(name = "Alogin.findByAcceso", query = "SELECT a FROM Alogin a WHERE a.acceso = :acceso")})
public class Alogin implements Serializable {

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
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "acceso")
    private boolean acceso;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente cliente;

    public Alogin() {
    }

    public Alogin(Integer id) {
        this.id = id;
    }

    public Alogin(Integer id, Date fecha, String ip, boolean acceso) {
        this.id = id;
        this.fecha = fecha;
        this.ip = ip;
        this.acceso = acceso;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean getAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof Alogin)) {
            return false;
        }
        Alogin other = (Alogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.proyecto.emtitis.Alogin[ id=" + id + " ]";
    }

    public void setIp(InetAddress IP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
