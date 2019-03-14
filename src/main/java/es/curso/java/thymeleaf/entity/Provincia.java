package es.curso.java.thymeleaf.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Provincia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nombre;
	private int superficie;
	
	public Provincia() {
	}

	public Provincia(String id, String nombre, int superficie) {
		this.id = id;
		this.nombre = nombre;
		this.superficie = superficie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	
	

}
