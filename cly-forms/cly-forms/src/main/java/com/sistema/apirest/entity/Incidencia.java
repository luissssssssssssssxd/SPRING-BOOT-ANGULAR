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

@Entity
@Table(name = "incidencias")
public class Incidencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_inicio;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_fin;



    private String impresorareemplazo;




    public String getImpresorareemplazo() {
        return impresorareemplazo;
    }

    public void setImpresorareemplazo(String impresorareemplazo) {
        this.impresorareemplazo = impresorareemplazo;
    }

    private String usuariologeado;
    

    public String getUsuariologeado() {
        return usuariologeado;
    }

    public void setUsuariologeado(String usuariologeado) {
        this.usuariologeado = usuariologeado;
    }

    @ManyToOne
    @JoinColumn(name = "impresora_id")
    private Impresora impresora;

    public Impresora getImpresora() {
        return this.impresora;
    }

    public void setImpresora(Impresora impresora) {
        this.impresora = impresora;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return this.fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return this.fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

}
