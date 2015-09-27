/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mifincaraiz.jsf.controlador;

import com.mifincaraiz.jpa.entitys.Ciudad;
import com.mifincaraiz.jpa.entitys.TipoVivienda;
import com.mifincaraiz.jpa.entitys.TransaccionVivienda;
import com.mifincaraiz.jpa.entitys.Usuario;
import com.mifincaraiz.jpa.entitys.Vivienda;
import com.mifincaraiz.jpa.sessions.CiudadFacade;
import com.mifincaraiz.jpa.sessions.TipoViviendaFacade;
import com.mifincaraiz.jpa.sessions.TransaccionViviendaFacade;
import com.mifincaraiz.jpa.sessions.UsuarioFacade;
import com.mifincaraiz.jpa.sessions.ViviendaFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author andresM
 */
@ManagedBean
@RequestScoped
//@SessionScoped
public class ViviendaController implements Serializable {

    private Vivienda viviendaActual;
    private List<Vivienda> listaViviendas = null;
    private Usuario usuario = null;
    private List<TransaccionVivienda> listaTransaccionVivienda = null;
    @EJB
    private ViviendaFacade viviendaFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @EJB
    private TipoViviendaFacade tipoVivienda;
    @EJB
    private TransaccionViviendaFacade transaccionViviendaFacade;

    /**
     * Creates a new instance of ViviendaController
     */
    public ViviendaController() {
    }

    public Vivienda getViviendaActual() {
        if (viviendaActual == null) {
            viviendaActual = new Vivienda();
        }
        return viviendaActual;
    }

    public void setViviendaActual(Vivienda viviendaActual) {
        this.viviendaActual = viviendaActual;
    }
//

    public List<Vivienda> getListaViviendas() {
        if (listaViviendas == null) {
            try {
                listaViviendas = getViviendasFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            }
        }
        return listaViviendas;
    }

    private void recargarLista() {
        listaViviendas = null;
    }

    public void setListaViviendas(List<Vivienda> listaViviendas) {
        this.listaViviendas = listaViviendas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
//

    public List<TransaccionVivienda> getListaTransaccionVivienda() {
        return listaTransaccionVivienda;
    }

    public void setListaTransaccionVivienda(List<TransaccionVivienda> listaTransaccionVivienda) {
        this.listaTransaccionVivienda = listaTransaccionVivienda;
    }

    public ViviendaFacade getViviendasFacade() {
        return viviendaFacade;
    }

    public List<Vivienda> getListaViviendasUsuario(Usuario usuario) {
        return getViviendasFacade().consultaUsuario(usuario);
    }
//

    public void setViviendaFacade(ViviendaFacade viviendaFacade) {
        this.viviendaFacade = viviendaFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
//

    public CiudadFacade getCiudadFacade() {
        return ciudadFacade;
    }
//

    public void setCiudadFacade(CiudadFacade ciudadFacade) {
        this.ciudadFacade = ciudadFacade;
    }
//    

    public List<Ciudad> getListaCiudadesAutoComplete(String query) {
        try {
            return getCiudadFacade().findByNombre(query);
        } catch (Exception ex) {
            Logger.getLogger(ViviendaController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//

    public TipoViviendaFacade getTipoVivienda() {
        return tipoVivienda;
    }
//

    public void setTipoVivienda(TipoViviendaFacade tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }
//    

    public TransaccionViviendaFacade getTransaccionViviendaFacade() {
        return transaccionViviendaFacade;
    }

    public List<TransaccionVivienda> getListtransaccionViviendaSelctOne() {
        return getTransaccionViviendaFacade().findAll();
    }

    public String publicar(ActionEvent event) {

        try {
            viviendaActual.setEstado(true);
            viviendaActual.setFechaPublicacionVivienda(new Date());
            viviendaActual.setUsuarioIdUsuario((Usuario) event.getComponent().getAttributes().get("userLoguiado"));
            viviendaActual.setTransaccionViviendaList(listaTransaccionVivienda);
            getViviendasFacade().create(viviendaActual);
            addSuccessMessage("exito", "se guardaron los datos");
            recargarLista();
            return "index";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public List<TipoVivienda> getListTipoViviendaSelectOne() {
        return getTipoVivienda().findAll();
    }

}
