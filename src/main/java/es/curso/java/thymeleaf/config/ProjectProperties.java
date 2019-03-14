
package es.curso.java.thymeleaf.config;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
// @PropertySource("classpath:configprops.properties") Define la localización del archivo de propiedades, si no se define usa
// la localización por defecto classpath:application.properties.
@ConfigurationProperties(prefix = "ciudades")
public class ProjectProperties {

	@NotEmpty
	private String ciudadesFile;

	public String getCiudadesFile() {
		return ciudadesFile;
	}

	public void setCiudadesFile(String ciudadesFile) {
		this.ciudadesFile = ciudadesFile;
	}

}