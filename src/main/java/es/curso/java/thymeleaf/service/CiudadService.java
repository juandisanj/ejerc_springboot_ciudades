package es.curso.java.thymeleaf.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.curso.java.thymeleaf.dao.CiudadDao;
import es.curso.java.thymeleaf.entity.Ciudad;

@Service
public class CiudadService {

	@Autowired
	CiudadDao ciudadDao;

	public Optional<Ciudad> find(String id) {
		return ciudadDao.find(id);
	}

	public void add(Ciudad ciudad) {
		ciudadDao.add(ciudad);
	}

	public void update(Ciudad ciudad) {
		ciudadDao.update(ciudad);
	}

	public void remove(String id) {
		ciudadDao.remove(id);
	}

	public List<Ciudad> getAll() {
		return ciudadDao.getAll();
	}

}
