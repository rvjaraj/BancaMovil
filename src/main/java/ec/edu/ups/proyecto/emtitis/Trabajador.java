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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicio
 */
@Entity
@Table(name = "trabajador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajador.findAll", query = "SELECT t FROM Trabajador t"),
    @NamedQuery(name = "Trabajador.findById", query = "SELECT t FROM Trabajador t WHERE t.id = :id"),
    @NamedQuery(name = "Trabajador.findByCedula", query = "SELECT t FROM Trabajador t WHERE t.cedula = :cedula"),
    @NamedQuery(name = "Trabajador.findByNombres", query = "SELECT t FROM Trabajador t WHERE t.nombres = :nombres"),
    @NamedQuery(name = "Trabajador.findByApellido", query = "SELECT t FROM Trabajador t WHERE t.apellido = :apellido"),
    @NamedQuery(name = "Trabajador.findByTelefono", query = "SELECT t FROM Trabajador t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Trabajador.findByDireccion", query = "SELECT t FROM Trabajador t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Trabajador.findByCorreo", query = "SELECT t FROM Trabajador t WHERE t.correo = :correo"),
    @NamedQuery(name = "Trabajador.findByContracenia", query = "SELECT t FROM Trabajador t WHERE t.contracenia = :contracenia"),
    @NamedQuery(name = "Trabajador.findByRol", query = "SELECT t FROM Trabajador t WHERE t.rol = :rol"),
    @NamedQuery(name = "Trabajador.findBySueldo", query = "SELECT t FROM Trabajador t WHERE t.sueldo = :sueldo"),
    @NamedQuery(name = "Trabajador.findByEliminado", query = "SELECT t FROM Trabajador t WHERE t.eliminado = :eliminado"),
    @NamedQuery(name = "Trabajador.findByActivo", query = "SELECT t FROM Trabajador t WHERE t.activo = :activo")})
public class Trabajador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "contracenia")
    private String contracenia;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @Basic(optional = false)
    @Column(name = "sueldo")
    private double sueldo;
    @Column(name = "eliminado")
    private boolean eliminado;
    @Column(name = "activo")
    private boolean activo;

    public Trabajador() {
    }

    public Trabajador(Integer id) {
        this.id = id;
    }

    public Trabajador(Integer id, String cedula, String nombres, String apellido, String telefono, String direccion, String correo, String contracenia, String rol, double sueldo) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.contracenia = contracenia;
        this.rol = rol;
        this.sueldo = sueldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContracenia() {
        return contracenia;
    }

    public void setContracenia(String contracenia) {
        this.contracenia = contracenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "id=" + id + ", cedula=" + cedula + ", nombres=" + nombres + ", apellido=" + apellido + ", telefono=" + telefono + ", direccion=" + direccion + ", correo=" + correo + ", contracenia=" + contracenia + ", rol=" + rol + ", sueldo=" + sueldo + ", eliminado=" + eliminado + ", activo=" + activo + '}';
    }

    
    
}
