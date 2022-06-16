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
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_mov;

    private String observacion;

    private String ip;

    private String usuariologeado;

    

    public String getUsuariologeado() {
        return usuariologeado;
    }


    public void setUsuariologeado(String usuariologeado) {
        this.usuariologeado = usuariologeado;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }



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

    @ManyToOne
    @JoinColumn(name = "piso_id")
    private Piso piso;

    @ManyToOne
    @JoinColumn(name = "sociedad_id")
    private Sociedades sociedad;

    @ManyToOne
    @JoinColumn(name = "centro_id")
    private CentroCosto costo;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    

    


    public Piso getPiso() {
        return piso;
    }


    public void setPiso(Piso piso) {
        this.piso = piso;
    }


  


    public Sociedades getSociedad() {
        return sociedad;
    }


    public void setSociedad(Sociedades sociedad) {
        this.sociedad = sociedad;
    }


    public CentroCosto getCosto() {
        return costo;
    }


    public void setCosto(CentroCosto costo) {
        this.costo = costo;
    }


    public Ubicacion getUbicacion() {
        return ubicacion;
    }


    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }


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
