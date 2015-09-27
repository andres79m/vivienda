package com.mifincaraiz.jpa.entitys;

import com.mifincaraiz.jpa.entitys.Ciudad;
import com.mifincaraiz.jpa.entitys.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-09-25T16:01:47")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, Integer> idDepartamento;
    public static volatile ListAttribute<Departamento, Ciudad> ciudadList;
    public static volatile SingularAttribute<Departamento, Pais> paisIdPais;
    public static volatile SingularAttribute<Departamento, String> nombreDepartamento;

}