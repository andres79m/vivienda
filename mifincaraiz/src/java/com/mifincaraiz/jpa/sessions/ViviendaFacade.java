/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jpa.sessions;

import com.mifincaraiz.jpa.entitys.Usuario;
import com.mifincaraiz.jpa.entitys.Vivienda;
import java.util.Date;
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
public class ViviendaFacade extends AbstractFacade<Vivienda> {
    @PersistenceContext(unitName = "mifincaraizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViviendaFacade() {
        super(Vivienda.class);
    }

public List<Vivienda> consultaFechaPublicacion(){
        Query q= getEntityManager().createNamedQuery("Vivienda.findByFechaPublicacionVivienda");
        q.setParameter("FechaPublicacionVivienda", new Date());
        
        return q.getResultList();
    }
    public List<Vivienda> consultaUsuario(Vivienda usuario){
        Query q= getEntityManager().createNamedQuery("Vivienda.findByIdUsuario");
         q.setParameter("idUsuario", usuario);
        return q.getResultList();
    }  

    public List<Vivienda> consultaUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
