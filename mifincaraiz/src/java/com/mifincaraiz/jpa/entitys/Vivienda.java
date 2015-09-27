/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresM
 */
@Entity
@Table(name = "vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vivienda.findAll", query = "SELECT v FROM Vivienda v"),
    @NamedQuery(name = "Vivienda.findByIdVivienda", query = "SELECT v FROM Vivienda v WHERE v.idVivienda = :idVivienda"),
    @NamedQuery(name = "Vivienda.findByCantidadHabitaciones", query = "SELECT v FROM Vivienda v WHERE v.cantidadHabitaciones = :cantidadHabitaciones"),
    @NamedQuery(name = "Vivienda.findByPrecioVivienda", query = "SELECT v FROM Vivienda v WHERE v.precioVivienda = :precioVivienda"),
    @NamedQuery(name = "Vivienda.findByCantidadBanos", query = "SELECT v FROM Vivienda v WHERE v.cantidadBanos = :cantidadBanos"),
    @NamedQuery(name = "Vivienda.findByCantidadPisos", query = "SELECT v FROM Vivienda v WHERE v.cantidadPisos = :cantidadPisos"),
    @NamedQuery(name = "Vivienda.findByCorregimiento", query = "SELECT v FROM Vivienda v WHERE v.corregimiento = :corregimiento"),
    @NamedQuery(name = "Vivienda.findByBarrio", query = "SELECT v FROM Vivienda v WHERE v.barrio = :barrio"),
    @NamedQuery(name = "Vivienda.findByDireccionVivienda", query = "SELECT v FROM Vivienda v WHERE v.direccionVivienda = :direccionVivienda"),
    @NamedQuery(name = "Vivienda.findByEstado", query = "SELECT v FROM Vivienda v WHERE v.estado = :estado"),
    @NamedQuery(name = "Vivienda.findByDescripcion", query = "SELECT v FROM Vivienda v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Vivienda.findByFechaPublicacionVivienda", query = "SELECT v FROM Vivienda v WHERE v.fechaPublicacionVivienda = :fechaPublicacionVivienda")})
public class Vivienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vivienda")
    private Integer idVivienda;
    @Column(name = "cantidad_habitaciones")
    private String cantidadHabitaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_vivienda")
    private String precioVivienda;
    @Column(name = "cantidad_banos")
    private String cantidadBanos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_pisos")
    private String cantidadPisos;
    @Size(max = 45)
    @Column(name = "corregimiento")
    private String corregimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "barrio")
    private String barrio;
    
    @JoinTable(name = "vivienda_has_transaccion_vivienda", joinColumns = {
        @JoinColumn(name = "vivienda_id_vivienda", referencedColumnName = "id_vivienda")}, inverseJoinColumns = {//esta linea se intercambia  con la linea siguiente pero solo lo que esta entre comillas
        @JoinColumn(name = "transaccion_vivienda_id_transaccion_vivienda", referencedColumnName = "id_transaccion_vivienda")})//y este mapeo se lo trae desde la entidad TransaccionVivienda segun la necesidad.
    @ManyToMany
    private List<TransaccionVivienda> transaccionViviendaList;//esta linea debe ir debajo del mapeo 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "direccion_vivienda")
    private String direccionVivienda;
    // @ManyToMany(mappedBy = "viviendaList")
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_publicacion_vivienda")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacionVivienda;
    @JoinColumn(name = "tipo_vivienda_id_tipo_vivienda", referencedColumnName = "id_tipo_vivienda")
    @ManyToOne(optional = false)
    private TipoVivienda tipoViviendaIdTipoVivienda;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;
    @JoinColumns({
        @JoinColumn(name = "ciudad_id_ciudad", referencedColumnName = "id_ciudad"),
        @JoinColumn(name = "ciudad_departamento_id_departamento", referencedColumnName = "departamento_id_departamento")})
    @ManyToOne(optional = false)
    private Ciudad ciudad;

    public Vivienda() {
    }

    public Vivienda(Integer idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Vivienda(Integer idVivienda, String precioVivienda, String cantidadPisos, String barrio, String direccionVivienda, boolean estado, String descripcion,Date fechaPublicacionVivienda) {
        this.idVivienda = idVivienda;
        this.precioVivienda = precioVivienda;
        this.cantidadPisos = cantidadPisos;
        this.barrio = barrio;
        this.direccionVivienda = direccionVivienda;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaPublicacionVivienda = fechaPublicacionVivienda;
    }

    public Integer getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Integer idVivienda) {
        this.idVivienda = idVivienda;
    }

    public String getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(String cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public String getPrecioVivienda() {
        return precioVivienda;
    }

    public void setPrecioVivienda(String precioVivienda) {
        this.precioVivienda = precioVivienda;
    }

    public String getCantidadBanos() {
        return cantidadBanos;
    }

    public void setCantidadBanos(String cantidadBanos) {
        this.cantidadBanos = cantidadBanos;
    }

    public String getCantidadPisos() {
        return cantidadPisos;
    }

    public void setCantidadPisos(String cantidadPisos) {
        this.cantidadPisos = cantidadPisos;
    }

    public String getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(String corregimiento) {
        this.corregimiento = corregimiento;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccionVivienda() {
        return direccionVivienda;
    }

    public void setDireccionVivienda(String direccionVivienda) {
        this.direccionVivienda = direccionVivienda;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPublicacionVivienda() {
        return fechaPublicacionVivienda;
    }

    public void setFechaPublicacionVivienda(Date fechaPublicacionVivienda) {
        this.fechaPublicacionVivienda = fechaPublicacionVivienda;
    }
    

    @XmlTransient
    public List<TransaccionVivienda> getTransaccionViviendaList() {
        return transaccionViviendaList;
    }

    public void setTransaccionViviendaList(List<TransaccionVivienda> transaccionViviendaList) {
        this.transaccionViviendaList = transaccionViviendaList;
    }

    public TipoVivienda getTipoViviendaIdTipoVivienda() {
        return tipoViviendaIdTipoVivienda;
    }

    public void setTipoViviendaIdTipoVivienda(TipoVivienda tipoViviendaIdTipoVivienda) {
        this.tipoViviendaIdTipoVivienda = tipoViviendaIdTipoVivienda;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVivienda != null ? idVivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vivienda)) {
            return false;
        }
        Vivienda other = (Vivienda) object;
        if ((this.idVivienda == null && other.idVivienda != null) || (this.idVivienda != null && !this.idVivienda.equals(other.idVivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mifincaraiz.jpa.entitys.Vivienda[ idVivienda=" + idVivienda + " ]";
    }
    
}
