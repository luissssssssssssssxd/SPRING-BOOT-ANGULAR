package com.sistema.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "impresora")
public class Impresora implements Serializable {

    @Id
    private String numeroserie;

    private Date fecha_mov;

    private String observacion;

    
    @ManyToOne
    @JoinColumn(name = "marca")
    private Marca marca;
    
    public Marca getMarca() {
        return marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
   
    
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado_imprsora estado;

    public Estado_imprsora getEstado() {
        return estado;
    }

    public void setEstado(Estado_imprsora estado) {
        this.estado = estado;
    }

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getNumeroserie() {
        return numeroserie;
    }

    public void setNumeroserie(String numeroserie) {
        this.numeroserie = numeroserie;
    }

    public Date getfecha_mov() {
        return fecha_mov;
    }

    public void setfecha_mov(Date fecha_mov) {
        this.fecha_mov = fecha_mov;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    


    

}
