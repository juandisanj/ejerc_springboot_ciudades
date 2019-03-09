

package es.curso.java.thymeleaf;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ciudad implements Serializable {

  private static final long serialVersionUID = 5276571601486161777L;

  private String id;
  private String nombre;
  private int fundadaEn;
  private int poblacion;
   

public Ciudad(String id, String nombre, int fundadaEn, int poblacion) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.fundadaEn = fundadaEn;
	this.poblacion = poblacion;
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
public int getFundadaEn() {
	return fundadaEn;
}
public void setFundadaEn(int fundadaEn) {
	this.fundadaEn = fundadaEn;
}
public int getPoblacion() {
	return poblacion;
}
public void setPoblacion(int poblacion) {
	this.poblacion = poblacion;
}
  
  
  
  
}