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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.PrePersist;


@Entity
@Table(name = "impresora")
public class Impresora implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String numeroserie;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_mov;

    private String observacion;

    @PrePersist /* Metodo para insertar la fecha automaticamente */
    public void PrePersist() {
        fecha_mov = new Date();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    
    @ManyToOne
    @JoinColumn(name = "marca_id")
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
