package com.sistema.apirest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados_impresoras")
public class Estado_imprsora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estadoimpresora;
    private String usuariologeado;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEstadoimpresora() {
        return estadoimpresora;
    }
    public void setEstadoimpresora(String estadoimpresora) {
        this.estadoimpresora = estadoimpresora;
    }
    public String getUsuariologeado() {
        return usuariologeado;
    }
    public void setUsuariologeado(String usuariologeado) {
        this.usuariologeado = usuariologeado;
    }
    

    
    
}
