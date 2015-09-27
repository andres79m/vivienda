package com.mifincaraiz.jpa.entitys;

import com.mifincaraiz.jpa.entitys.Departamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2015-09-25T16:01:47")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile SingularAttribute<Pais, String> nombrePais;
    public static volatile SingularAttribute<Pais, String> idPais;
    public static volatile ListAttribute<Pais, Departamento> departamentoList;

}