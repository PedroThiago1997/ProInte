package com.cibertec.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the preguntas_frecuentes database table.
 * 
 */
@Entity
@Table(name="preguntas_frecuentes")
@NamedQuery(name="PreguntasFrecuente.findAll", query="SELECT p FROM PreguntasFrecuente p")
public class PreguntasFrecuente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idpregunta;

	private String pregunta;

	private String respuestapregunta;

	private String rutaimagen;

	public PreguntasFrecuente() {
	}

	public Long getIdpregunta() {
		return this.idpregunta;
	}

	public void setIdpregunta(Long idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuestapregunta() {
		return this.respuestapregunta;
	}

	public void setRespuestapregunta(String respuestapregunta) {
		this.respuestapregunta = respuestapregunta;
	}

	public String getRutaimagen() {
		return this.rutaimagen;
	}

	public void setRutaimagen(String rutaimagen) {
		this.rutaimagen = rutaimagen;
	}

}