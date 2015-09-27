/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mifincaraiz.jsf.controlador;

import com.mifincaraiz.jpa.entitys.TipoVivienda;
import com.mifincaraiz.jpa.sessions.TipoViviendaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author andresM
 */
@ManagedBean
@SessionScoped
public class TipoViviendaController implements Serializable{
    @EJB
    private TipoViviendaFacade tipoVivienda;
    /**
     * Creates a new instance of TipoViviendaController
     */
    public TipoViviendaController() {
    }

    public TipoViviendaFacade getTipoVivienda() {
        return tipoVivienda;
    }

    public TipoVivienda getTipoVivienda(java.lang.Integer id) {
        return getTipoVivienda().find(id);
    }
    
 @FacesConverter(forClass = TipoVivienda.class)
    public static class TipoViviendaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoViviendaController controller = (TipoViviendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoViviendaController");
            return controller.getTipoVivienda(getKey(value));
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
            if (object instanceof TipoVivienda) {
                TipoVivienda o = (TipoVivienda) object;
                return getStringKey(o.getIdTipoVivienda());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoVivienda.class.getName());
            }
        }

    }    
}
