/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jsf.controlador;

import com.mifincaraiz.jpa.entitys.Usuario;
import com.mifincaraiz.jpa.entitys.Vivienda;
import com.mifincaraiz.jpa.sessions.UsuarioFacade;
import com.mifincaraiz.jpa.sessions.ViviendaFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;//esta import es el que da el enlace al controlador
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andresM
 */

@ManagedBean
@SessionScoped

public class LoginController implements Serializable{
    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private String email;
    private Vivienda viviendaActual;
    private List<Vivienda> listaViviendas =null;
    private String password;
    private Usuario usuarioActual;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ViviendaFacade viviendaFacade;
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
    public Usuario getUsuarioActual(){
        if (usuarioActual == null){
            usuarioActual=new Usuario();
        }
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vivienda getViviendaActual() {
        return viviendaActual;
    }

    public void setViviendaActual(Vivienda viviendaActual) {
        this.viviendaActual = viviendaActual;
    }

    public List<Vivienda> getListaViviendas() {
        return listaViviendas;
    }

    public void setListaViviendas(List<Vivienda> listaViviendas) {
        this.listaViviendas = listaViviendas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public ViviendaFacade getViviendaFacade() {
        return viviendaFacade;
    }

    public void setViviendaFacade(ViviendaFacade viviendaFacade) {
        this.viviendaFacade = viviendaFacade;
    }
    
    public boolean isAuthenticated() {
        return getRequest().getUserPrincipal() != null;
    }

    public Principal getPrincipal() {
        return getRequest().getUserPrincipal();
    }

    private HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object request = externalContext.getRequest();
        return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
    }

    private String getLogueado() {
        return getPrincipal().getName();
    }
     public Usuario getUserLogueado() {
        String user = getLogueado();
        return getUsuarioFacade().findByEmail(user);
       
    }
//    public Usuario getUserLogueado() {
//        string 
//        return getUsuarioFacade().findByEmail(getLogueado());
//    }
    
    public boolean isAdmin(){
        return getRequest().isUserInRole("webAdmin");
    }
    public boolean isUser(){
        return getRequest().isUserInRole("webCustomer");
    }
    
//     public void validarCorreo(FacesContext contex, UIComponent component, Object value)throws ValidatorException{
//        Usuario usuarioConsulta=getUsuarioFacade().findByEmail((String)value);
//        if(usuarioConsulta != null){
//           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña Repetida","Ya la cuenta de correo electronico se encuentra registrada en youhobby.com, por favor cambiala"));   
//        }else{
//            String correo=(String) value;
//            usuarioActual.setEmail(correo); 
//        }
//    }
    public String login() {
        try{
        getRequest().login(email, password);
        usuarioActual = getUserLogueado();
        limpiar();
        if(!usuarioActual.getEstadoUsuario()){
            logout();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario inactivo",null));
            return "/index";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ " + email.toString(), null));
        return "/customer/publicarfr";
    }catch (ServletException ex){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña invalida", null));
        return "/registro";
        }
     }

    private void limpiar() {
        email = "";
        password = "";
    }
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
            return "/index";
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            return "/customer/publicarfr";
        }
    }
}
