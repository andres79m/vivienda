/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mifincaraiz.jsf.controlador;

import com.mifincaraiz.jpa.entitys.Rol;
import com.mifincaraiz.jpa.entitys.Usuario;
import com.mifincaraiz.jpa.sessions.RolFacade;
import com.mifincaraiz.jpa.sessions.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author andresM
 */
@ManagedBean
//@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario usuarioActual;
    private List<Usuario> listaUsuarios = null;
    private List<Rol> listaRoles = null;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private RolFacade rolFacade;

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    public Usuario getUsuarioActual() {
        if (usuarioActual == null) {//
            usuarioActual = new Usuario();//error 
        }
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Usuario> getListaUsuarios() {

        if (listaUsuarios == null) {
            try {
                listaUsuarios = getUsuarioFacade().findAll();
            } catch (Exception e) {

                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            }
        }
        return listaUsuarios;
    }

    private void recargarLista() {
        listaUsuarios = null;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public void setRolFacade(RolFacade rolFacade) {
        this.rolFacade = rolFacade;
    }

    public String addUsuario() {
        
        listaRoles = new ArrayList<>();
        try {
            usuarioActual.setFechaRegistro(new Date());
            usuarioActual.setEstadoUsuario(true);
            if (listaRoles == null || listaRoles.isEmpty()) {
                listaRoles.add(new Rol("1", "cliente"));
                usuarioActual.setRolList(listaRoles);
            } else {
                usuarioActual.setRolList(listaRoles);
            }

            getUsuarioFacade().create(usuarioActual);
            addSuccesMessage("Usuario Creado Exitosamente", "Crear Usuario");
            recargarLista();
            return "";

        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());

            return null;
        }
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccesMessage(String title, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage("SuccesInfo", facesMsg);
    }

    public void validarEdad(FacesContext context, UIComponent component, Object o) throws ValidatorException {
        Date f = (Date) o;
        Date s = new Date();
        if ((s.getYear() - f.getYear()) < 18) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "XXXX", "No cumples con el requisito de edad necesario para registrarte."));
        } else {
            getUsuarioActual().setFechaNacimiento(f);
        }
    }
}
