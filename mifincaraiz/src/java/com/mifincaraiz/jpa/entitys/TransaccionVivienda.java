/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andresM
 */
@Entity
@Table(name = "transaccion_vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransaccionVivienda.findAll", query = "SELECT t FROM TransaccionVivienda t"),
    @NamedQuery(name = "TransaccionVivienda.findByIdTransaccionVivienda", query = "SELECT t FROM TransaccionVivienda t WHERE t.idTransaccionVivienda = :idTransaccionVivienda"),
    @NamedQuery(name = "TransaccionVivienda.findByNombreTransaccion", query = "SELECT t FROM TransaccionVivienda t WHERE t.nombreTransaccion = :nombreTransaccion")})
public class TransaccionVivienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_transaccion_vivienda")
    private Integer idTransaccionVivienda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_transaccion")
    private String nombreTransaccion;
    @ManyToMany(mappedBy = "transaccionViviendaList")//esta linea se la trae de la entidad Vivienda y se le cambia el valor entre comillas "viviendaList" por "transaccionViviendaList"
//    @JoinTable(name = "vivienda_has_transaccion_vivienda", joinColumns = {
//        @JoinColumn(name = "transaccion_vivienda_id_transaccion_vivienda", referencedColumnName = "id_transaccion_vivienda")}, inverseJoinColumns = {
//        @JoinColumn(name = "vivienda_id_vivienda", referencedColumnName = "id_vivienda")})
//    @ManyToMany
    private List<Vivienda> viviendaList;

    public TransaccionVivienda() {
    }

    public TransaccionVivienda(Integer idTransaccionVivienda) {
        this.idTransaccionVivienda = idTransaccionVivienda;
    }

    public TransaccionVivienda(Integer idTransaccionVivienda, String nombreTransaccion) {
        this.idTransaccionVivienda = idTransaccionVivienda;
        this.nombreTransaccion = nombreTransaccion;
    }

    public Integer getIdTransaccionVivienda() {
        return idTransaccionVivienda;
    }

    public void setIdTransaccionVivienda(Integer idTransaccionVivienda) {
        this.idTransaccionVivienda = idTransaccionVivienda;
    }

    public String getNombreTransaccion() {
        return nombreTransaccion;
    }

    public void setNombreTransaccion(String nombreTransaccion) {
        this.nombreTransaccion = nombreTransaccion;
    }

    @XmlTransient
    public List<Vivienda> getViviendaList() {
        return viviendaList;
    }

    public void setViviendaList(List<Vivienda> viviendaList) {
        this.viviendaList = viviendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccionVivienda != null ? idTransaccionVivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionVivienda)) {
            return false;
        }
        TransaccionVivienda other = (TransaccionVivienda) object;
        if ((this.idTransaccionVivienda == null && other.idTransaccionVivienda != null) || (this.idTransaccionVivienda != null && !this.idTransaccionVivienda.equals(other.idTransaccionVivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreTransaccion;
    }
    
}
