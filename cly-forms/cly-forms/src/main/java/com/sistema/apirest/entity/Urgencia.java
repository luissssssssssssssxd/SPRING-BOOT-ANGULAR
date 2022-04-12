package com.sistema.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "urgencia")
public class Urgencia implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombrepaciente;
	private String nombreacompanante;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-3")
	private Date fechaingreso;
	
	
//	@JsonFormat(pattern=" HH:mm:ss", timezone="America/Santiago")
//	@Temporal(TemporalType.TIME)
//	private Date horaingreso;
//	
		
	private String horaespera;
	
	private String observacion;
	
	
	@PrePersist
	public void prePersit() {
		fechaingreso = new Date();
//		horaingreso = new Date();
	}
	

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrepaciente() {
		return nombrepaciente;
	}

	public void setNombrepaciente(String nombrepaciente) {
		this.nombrepaciente = nombrepaciente;
	}

	public String getNombreacompanante() {
		return nombreacompanante;
	}

	public void setNombreacompanante(String nombreacompanante) {
		this.nombreacompanante = nombreacompanante;
	}

	public Date getFechaingreso() {
		return fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

//	public Date getHoraingreso() {
//		return horaingreso;
//	}
//
//	public void setHoraingreso(Date horaingreso) {
//		this.horaingreso = horaingreso;
//	}

	public String getHoraespera() {
		return horaespera;
	}

	public void setHoraespera(String horaespera) {
		this.horaespera = horaespera;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
