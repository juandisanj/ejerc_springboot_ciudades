

package es.curso.java.thymeleaf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class CiudadDao {

  private Set<Ciudad> cities = new HashSet<>();

  public Optional<Ciudad> find(String id) {
    return cities
        .stream()
        .filter(c -> c.getId().equals(id))
        .findFirst();
  }

  public void add(Ciudad ciudad) {
    cities.add(ciudad);
  }

  public void update(Ciudad ciudad) {
    remove(ciudad.getId());
    add(ciudad);
  }


  public void remove(String id) {
    if (StringUtils.isNotBlank(id)) {
      cities.removeIf(c -> c.getId().equals(id));
    }
  }

  public List<Ciudad> getAll() {
    List<Ciudad> ciudadList = new ArrayList<>(cities);
    ciudadList.sort(Comparator.comparing(Ciudad::getNombre));
    return ciudadList;
  }
}