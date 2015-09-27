package com.mifincaraiz.jpa.entitys;

import com.mifincaraiz.jpa.entitys.Ciudad;
import com.mifincaraiz.jpa.entitys.TipoVivienda;
import com.mifincaraiz.jpa.entitys.TransaccionVivienda;
import com.mifincaraiz.jpa.entitys.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-09-25T16:01:47")
@StaticMetamodel(Vivienda.class)
public class Vivienda_ { 

    public static volatile SingularAttribute<Vivienda, Ciudad> ciudad;
    public static volatile SingularAttribute<Vivienda, Integer> idVivienda;
    public static volatile SingularAttribute<Vivienda, String> precioVivienda;
    public static volatile SingularAttribute<Vivienda, String> descripcion;
    public static volatile SingularAttribute<Vivienda, TipoVivienda> tipoViviendaIdTipoVivienda;
    public static volatile ListAttribute<Vivienda, TransaccionVivienda> transaccionViviendaList;
    public static volatile SingularAttribute<Vivienda, String> corregimiento;
    public static volatile SingularAttribute<Vivienda, Usuario> usuarioIdUsuario;
    public static volatile SingularAttribute<Vivienda, String> cantidadHabitaciones;
    public static volatile SingularAttribute<Vivienda, Boolean> estado;
    public static volatile SingularAttribute<Vivienda, String> cantidadBanos;
    public static volatile SingularAttribute<Vivienda, String> direccionVivienda;
    public static volatile SingularAttribute<Vivienda, String> cantidadPisos;
    public static volatile SingularAttribute<Vivienda, String> barrio;
    public static volatile SingularAttribute<Vivienda, Date> fechaPublicacionVivienda;

}