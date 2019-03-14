package es.curso.java.thymeleaf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.curso.java.thymeleaf.dao.ProvinciaDao;
import es.curso.java.thymeleaf.entity.Provincia;

@Service
public class ProvinciaService {
	
	@Autowired
	ProvinciaDao provinciaDao;
	
	public Optional<Provincia> find(String id){
		return provinciaDao.find(id);
	}
	
	public void add(Provincia provincia) {
		provinciaDao.add(provincia);
	}
	
	public void update(Provincia provincia) {
		provinciaDao.update(provincia);
	}
	
	public void remove(String id) {
		provinciaDao.remove(id);
	}
	
	public List<Provincia> getAll(){
		return provinciaDao.getAll();
	}

}
