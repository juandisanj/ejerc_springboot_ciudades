package es.curso.java.thymeleaf;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.curso.java.thymeleaf.config.ProvinciaProperties;
import es.curso.java.thymeleaf.dao.ProvinciaDao;
import es.curso.java.thymeleaf.entity.Provincia;

@Configuration
@EnableConfigurationProperties(ProvinciaProperties.class)
public class ProvinciasInitializer implements InitializingBean {

	@Resource
	private ProvinciaDao provinciaDao;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private ProvinciaProperties provinciaProperties;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		org.springframework.core.io.Resource resource = new ClassPathResource(provinciaProperties.getProvinciasFile());
		
		List<Provincia> provincias;
		try (InputStream inputStream = resource.getInputStream()) {
			provincias = objectMapper.readValue(inputStream, new TypeReference<List<Provincia>>(){
			});
		}
		provincias.forEach(provincia -> provinciaDao.add(provincia));
	}

}
