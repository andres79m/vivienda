/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.sessions;

import com.mifincaraiz.jpa.entitys.Ciudad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author andresM
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
    @PersistenceContext(unitName = "mifincaraizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    public List<Ciudad> findByNombre (String nombre){
        Query q= getEntityManager().createNamedQuery("Ciudad.findByNombreCiudad");
        q.setParameter("nombreCiudad",nombre +"%");
        return q.getResultList();
    }
}
