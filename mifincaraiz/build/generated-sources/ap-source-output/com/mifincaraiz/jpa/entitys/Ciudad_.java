package com.mifincaraiz.jpa.entitys;

import com.mifincaraiz.jpa.entitys.CiudadPK;
import com.mifincaraiz.jpa.entitys.Departamento;
import com.mifincaraiz.jpa.entitys.Vivienda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-09-25T16:01:47")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile SingularAttribute<Ciudad, CiudadPK> ciudadPK;
    public static volatile SingularAttribute<Ciudad, Departamento> departamento;
    public static volatile ListAttribute<Ciudad, Vivienda> viviendaList;
    public static volatile SingularAttribute<Ciudad, String> nombreCiudad;

}