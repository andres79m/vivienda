/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tipo_vivienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoVivienda.findAll", query = "SELECT t FROM TipoVivienda t"),
    @NamedQuery(name = "TipoVivienda.findByIdTipoVivienda", query = "SELECT t FROM TipoVivienda t WHERE t.idTipoVivienda = :idTipoVivienda"),
    @NamedQuery(name = "TipoVivienda.findByNombreVivienda", query = "SELECT t FROM TipoVivienda t WHERE t.nombreVivienda = :nombreVivienda")})
public class TipoVivienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_vivienda")
    private Integer idTipoVivienda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_vivienda")
    private String nombreVivienda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoViviendaIdTipoVivienda")
    private List<Vivienda> viviendaList;

    public TipoVivienda() {
    }

    public TipoVivienda(Integer idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    public TipoVivienda(Integer idTipoVivienda, String nombreVivienda) {
        this.idTipoVivienda = idTipoVivienda;
        this.nombreVivienda = nombreVivienda;
    }

    public Integer getIdTipoVivienda() {
        return idTipoVivienda;
    }

    public void setIdTipoVivienda(Integer idTipoVivienda) {
        this.idTipoVivienda = idTipoVivienda;
    }

    public String getNombreVivienda() {
        return nombreVivienda;
    }

    public void setNombreVivienda(String nombreVivienda) {
        this.nombreVivienda = nombreVivienda;
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
        hash += (idTipoVivienda != null ? idTipoVivienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoVivienda)) {
            return false;
        }
        TipoVivienda other = (TipoVivienda) object;
        if ((this.idTipoVivienda == null && other.idTipoVivienda != null) || (this.idTipoVivienda != null && !this.idTipoVivienda.equals(other.idTipoVivienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreVivienda;
    }
    
}
