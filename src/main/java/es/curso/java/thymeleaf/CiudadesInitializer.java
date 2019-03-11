
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

import es.curso.java.thymeleaf.config.ProjectProperties;
import es.curso.java.thymeleaf.dao.CiudadDao;
import es.curso.java.thymeleaf.entity.Ciudad;

@Configuration
@EnableConfigurationProperties(ProjectProperties.class)
public class CiudadesInitializer implements InitializingBean {

  @Resource
  private CiudadDao cityDao;
  @Resource
  private ObjectMapper objectMapper;
  @Resource
  private ProjectProperties projectProperties;

  @Override
  public void afterPropertiesSet() throws Exception {
    org.springframework.core.io.Resource resource = new ClassPathResource(projectProperties.getCiudadesFile());

    List<Ciudad> cities;
    try (InputStream inputStream = resource.getInputStream()) {
      cities = objectMapper.readValue(inputStream, new TypeReference<List<Ciudad>>() {
      });
    }
    cities.forEach(city -> cityDao.add(city));
  }
}