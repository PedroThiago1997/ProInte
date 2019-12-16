package com.cibertec.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the recorrido_ruta database table.
 * 
 */
@Entity
@Table(name="recorrido_ruta")
@NamedQuery(name="RecorridoRuta.findAll", query="SELECT r FROM RecorridoRuta r")
public class RecorridoRuta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECORRIDO")
	private Long idRecorrido;

	private String latitud;

	private String longitud;

	private String referencia;

	//bi-directional many-to-one association to Ruta
	@ManyToOne
	@JoinColumn(name="ID_RUTA")
	private Ruta ruta;

	public RecorridoRuta() {
	}

	public Long getIdRecorrido() {
		return this.idRecorrido;
	}

	public void setIdRecorrido(Long idRecorrido) {
		this.idRecorrido = idRecorrido;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Ruta getRuta() {
		return this.ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

}