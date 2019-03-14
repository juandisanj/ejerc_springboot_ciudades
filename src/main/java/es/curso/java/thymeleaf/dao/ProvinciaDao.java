package es.curso.java.thymeleaf.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import es.curso.java.thymeleaf.entity.Provincia;
import io.micrometer.core.instrument.util.StringUtils;

@Repository
public class ProvinciaDao {
	
	private Set<Provincia> provincias = new HashSet<>();
	
	public Optional<Provincia> find(String id){
		return provincias
				.stream()
				.filter(p -> p.getId().equals(id))
				.findFirst();
	}
	
	public void add(Provincia provincia) {
		provincias.add(provincia);
	}
	
	public void update(Provincia provincia) {
		remove(provincia.getId());
		add(provincia);
	}
	
	public void remove(String id) {
		if(StringUtils.isNotBlank(id)) {
			provincias.removeIf(p -> p.getId().contentEquals(id));
		}
	}
	
	public List<Provincia> getAll(){
		List<Provincia> provinciaList = new ArrayList<>(provincias);
		provinciaList.sort(Comparator.comparing(Provincia::getNombre));
		return provinciaList;
	}

}
