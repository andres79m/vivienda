/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mifincaraiz.jsf.controlador;

import com.mifincaraiz.jpa.entitys.TransaccionVivienda;
import com.mifincaraiz.jpa.sessions.TransaccionViviendaFacade;
import com.mifincaraiz.jsf.controller.util.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author acosta
 */
//@Named(value = "transaccionViviendaController")
@ManagedBean
@SessionScoped
public class TransaccionViviendaController implements Serializable{
    
@EJB
private TransaccionViviendaFacade transaccionViviendaFacade;
    
    public TransaccionViviendaController() {
    }

    public TransaccionViviendaFacade getTransaccionViviendaFacade() {
        return transaccionViviendaFacade;
    }
    
    
//     public SelectItem[] getItemsAvailableSelectOne() {
//        return JsfUtil.getSelectItems(transaccionViviendaFacade.findAll(), true);
//    }
    
//    public TransaccionVivienda getTransaccionVivienda(java.lang.String id){
//    return getTransaccionViviendaFacade().find(id);
//    }

    @FacesConverter(forClass = TransaccionVivienda.class, value = "transaccionConverter")
    public static class TransaccionViviendaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TransaccionViviendaController controller = (TransaccionViviendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "transaccionViviendaController");
            return controller.transaccionViviendaFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TransaccionVivienda) {
                TransaccionVivienda o = (TransaccionVivienda) object;
                return getStringKey(o.getIdTransaccionVivienda());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TransaccionVivienda.class.getName());
            }
        }

    }
}
