/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author andresM
 */
@Embeddable
public class CiudadPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ciudad")
    private int idCiudad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departamento_id_departamento")
    private int departamentoIdDepartamento;

    public CiudadPK() {
    }

    public CiudadPK(int idCiudad, int departamentoIdDepartamento) {
        this.idCiudad = idCiudad;
        this.departamentoIdDepartamento = departamentoIdDepartamento;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getDepartamentoIdDepartamento() {
        return departamentoIdDepartamento;
    }

    public void setDepartamentoIdDepartamento(int departamentoIdDepartamento) {
        this.departamentoIdDepartamento = departamentoIdDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCiudad;
        hash += (int) departamentoIdDepartamento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CiudadPK)) {
            return false;
        }
        CiudadPK other = (CiudadPK) object;
        if (this.idCiudad != other.idCiudad) {
            return false;
        }
        if (this.departamentoIdDepartamento != other.departamentoIdDepartamento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mifincaraiz.jpa.entitys.CiudadPK[ idCiudad=" + idCiudad + ", departamentoIdDepartamento=" + departamentoIdDepartamento + " ]";
    }
    
}
