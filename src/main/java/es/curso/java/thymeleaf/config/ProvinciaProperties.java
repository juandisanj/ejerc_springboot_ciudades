package es.curso.java.thymeleaf.config;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix="provincias")
public class ProvinciaProperties {
	
	@NotEmpty
	private String provinciasFile;

	public String getProvinciasFile() {
		return provinciasFile;
	}

	public void setProvinciasFile(String provinciasFile) {
		this.provinciasFile = provinciasFile;
	}
	
}
