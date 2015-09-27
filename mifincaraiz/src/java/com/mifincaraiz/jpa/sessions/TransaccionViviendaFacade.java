/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.sessions;

import com.mifincaraiz.jpa.entitys.TransaccionVivienda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andresM
 */
@Stateless
public class TransaccionViviendaFacade extends AbstractFacade<TransaccionVivienda> {
    @PersistenceContext(unitName = "mifincaraizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransaccionViviendaFacade() {
        super(TransaccionVivienda.class);
    }
    
}
