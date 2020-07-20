/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.emtitis;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicio
 */
@Entity
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findById", query = "SELECT s FROM Solicitud s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitud.findByPlazo", query = "SELECT s FROM Solicitud s WHERE s.plazo = :plazo"),
    @NamedQuery(name = "Solicitud.findByHistorial", query = "SELECT s FROM Solicitud s WHERE s.historial = :historial"),
    @NamedQuery(name = "Solicitud.findByProposito", query = "SELECT s FROM Solicitud s WHERE s.proposito = :proposito"),
    @NamedQuery(name = "Solicitud.findByCantidad", query = "SELECT s FROM Solicitud s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "Solicitud.findBySaldocuenta", query = "SELECT s FROM Solicitud s WHERE s.saldocuenta = :saldocuenta"),
    @NamedQuery(name = "Solicitud.findByTiempoempleo", query = "SELECT s FROM Solicitud s WHERE s.tiempoempleo = :tiempoempleo"),
    @NamedQuery(name = "Solicitud.findByTasadepagos", query = "SELECT s FROM Solicitud s WHERE s.tasadepagos = :tasadepagos"),
    @NamedQuery(name = "Solicitud.findByEstadocivil", query = "SELECT s FROM Solicitud s WHERE s.estadocivil = :estadocivil"),
    @NamedQuery(name = "Solicitud.findByGarante", query = "SELECT s FROM Solicitud s WHERE s.garante = :garante"),
    @NamedQuery(name = "Solicitud.findByAvaluovivienda", query = "SELECT s FROM Solicitud s WHERE s.avaluovivienda = :avaluovivienda"),
    @NamedQuery(name = "Solicitud.findByActivos", query = "SELECT s FROM Solicitud s WHERE s.activos = :activos"),
    @NamedQuery(name = "Solicitud.findByEdad", query = "SELECT s FROM Solicitud s WHERE s.edad = :edad"),
    @NamedQuery(name = "Solicitud.findByTipovivienda", query = "SELECT s FROM Solicitud s WHERE s.tipovivienda = :tipovivienda"),
    @NamedQuery(name = "Solicitud.findByNumerocreditos", query = "SELECT s FROM Solicitud s WHERE s.numerocreditos = :numerocreditos"),
    @NamedQuery(name = "Solicitud.findByEmpleo", query = "SELECT s FROM Solicitud s WHERE s.empleo = :empleo"),
    @NamedQuery(name = "Solicitud.findByTrebajadorextrangero", query = "SELECT s FROM Solicitud s WHERE s.trebajadorextrangero = :trebajadorextrangero"),
    @NamedQuery(name = "Solicitud.findByTipocliente", query = "SELECT s FROM Solicitud s WHERE s.tipocliente = :tipocliente"),
    @NamedQuery(name = "Solicitud.findByEstado", query = "SELECT s FROM Solicitud s WHERE s.estado = :estado"),
    @NamedQuery(name = "Solicitud.findByElimado", query = "SELECT s FROM Solicitud s WHERE s.elimado = :elimado")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "plazo")
    private int plazo;
    @Basic(optional = false)
    @Column(name = "historial")
    private String historial;
    @Basic(optional = false)
    @Column(name = "proposito")
    private String proposito;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private double cantidad;
    @Basic(optional = false)
    @Column(name = "saldocuenta")
    private String saldocuenta;
    @Basic(optional = false)
    @Column(name = "tiempoempleo")
    private double tiempoempleo;
    @Basic(optional = false)
    @Column(name = "tasadepagos")
    private double tasadepagos;
    @Basic(optional = false)
    @Column(name = "estadocivil")
    private String estadocivil;
    @Basic(optional = false)
    @Column(name = "garante")
    private String garante;
    @Basic(optional = false)
    @Column(name = "avaluovivienda")
    private double avaluovivienda;
    @Basic(optional = false)
    @Column(name = "activos")
    private String activos;
    @Basic(optional = false)
    @Column(name = "edad")
    private int edad;
    @Basic(optional = false)
    @Column(name = "tipovivienda")
    private String tipovivienda;
    @Basic(optional = false)
    @Column(name = "numerocreditos")
    private int numerocreditos;
    @Basic(optional = false)
    @Column(name = "empleo")
    private String empleo;
    @Basic(optional = false)
    @Column(name = "trebajadorextrangero")
    private short trebajadorextrangero;
    @Basic(optional = false)
    @Column(name = "tipocliente")
    private String tipocliente;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Column(name = "elimado")
    private Short elimado;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Solicitud() {
    }

    public Solicitud(Integer id) {
        this.id = id;
    }

    public Solicitud(Integer id, int plazo, String historial, String proposito, double cantidad, String saldocuenta, double tiempoempleo, double tasadepagos, String estadocivil, String garante, double avaluovivienda, String activos, int edad, String tipovivienda, int numerocreditos, String empleo, short trebajadorextrangero, String tipocliente, String estado) {
        this.id = id;
        this.plazo = plazo;
        this.historial = historial;
        this.proposito = proposito;
        this.cantidad = cantidad;
        this.saldocuenta = saldocuenta;
        this.tiempoempleo = tiempoempleo;
        this.tasadepagos = tasadepagos;
        this.estadocivil = estadocivil;
        this.garante = garante;
        this.avaluovivienda = avaluovivienda;
        this.activos = activos;
        this.edad = edad;
        this.tipovivienda = tipovivienda;
        this.numerocreditos = numerocreditos;
        this.empleo = empleo;
        this.trebajadorextrangero = trebajadorextrangero;
        this.tipocliente = tipocliente;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getSaldocuenta() {
        return saldocuenta;
    }

    public void setSaldocuenta(String saldocuenta) {
        this.saldocuenta = saldocuenta;
    }

    public double getTiempoempleo() {
        return tiempoempleo;
    }

    public void setTiempoempleo(double tiempoempleo) {
        this.tiempoempleo = tiempoempleo;
    }

    public double getTasadepagos() {
        return tasadepagos;
    }

    public void setTasadepagos(double tasadepagos) {
        this.tasadepagos = tasadepagos;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getGarante() {
        return garante;
    }

    public void setGarante(String garante) {
        this.garante = garante;
    }

    public double getAvaluovivienda() {
        return avaluovivienda;
    }

    public void setAvaluovivienda(double avaluovivienda) {
        this.avaluovivienda = avaluovivienda;
    }

    public String getActivos() {
        return activos;
    }

    public void setActivos(String activos) {
        this.activos = activos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipovivienda() {
        return tipovivienda;
    }

    public void setTipovivienda(String tipovivienda) {
        this.tipovivienda = tipovivienda;
    }

    public int getNumerocreditos() {
        return numerocreditos;
    }

    public void setNumerocreditos(int numerocreditos) {
        this.numerocreditos = numerocreditos;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    public short getTrebajadorextrangero() {
        return trebajadorextrangero;
    }

    public void setTrebajadorextrangero(short trebajadorextrangero) {
        this.trebajadorextrangero = trebajadorextrangero;
    }

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Short getElimado() {
        return elimado;
    }

    public void setElimado(Short elimado) {
        this.elimado = elimado;
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
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ups.proyecto.emtitis.Solicitud[ id=" + id + " ]";
    }
    
}
