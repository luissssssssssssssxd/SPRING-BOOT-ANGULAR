package com.sistema.apirest.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "obs_incidencias")
public class Obs_incidencias implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String observacion;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_obs;

    private String usuariologeado;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    @PrePersist
    public void PrePersist(){
        fecha_obs=new Date();
    }

    

    public String getUsuariologeado() {
        return usuariologeado;
    }



    public void setUsuariologeado(String usuariologeado) {
        this.usuariologeado = usuariologeado;
    }



    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha_obs() {
        return this.fecha_obs;
    }

    public void setFecha_obs(Date fecha_obs) {
        this.fecha_obs = fecha_obs;
    }

    public Incidencia getIncidencia() {
        return this.incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }
    
}
