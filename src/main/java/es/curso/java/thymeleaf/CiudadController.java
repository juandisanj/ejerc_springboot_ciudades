
package es.curso.java.thymeleaf;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.function.Supplier;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("ciudades")
public class CiudadController {

  static final String VIEW_CITIES = "pages/ciudades";
  static final String VIEW_CITY_FORM = "pages/ciudades-form";
  static final String VIEW_CITY_DELETE = "pages/ciudad-delete";
  static final String MODEL_ATTRIBUTE_CITIES = "ciudades";
  static final String MODEL_ATTRIBUTE_CITY = "ciudad";
  static final String FRAGMENT_FORM = " :: form";
  static final String SECTION_CITIES = "ciudades";
  private static final Logger LOG = LoggerFactory.getLogger(CiudadController.class);
  private static final String ID = "id";
  private static final String PATH_ID = "/{id}";
  private static final String X_REQUESTED_WITH_XML_HTTP_REQUEST = "X-Requested-With=XMLHttpRequest";

  @Resource
  private CiudadDao ciudadDao;

  @RequestMapping
  public String overview(ModelMap modelMap) {
    modelMap.addAttribute(MODEL_ATTRIBUTE_CITIES, ciudadDao.getAll());
    return VIEW_CITIES;
  }

  @GetMapping(value = PATH_ID)
  public String showUpdateCityPage(@PathVariable(ID) String id,
                                   ModelMap modelMap) {
    Ciudad ciudad = ciudadDao.find(id).orElseThrow(notFoundException());
    modelMap.addAttribute(MODEL_ATTRIBUTE_CITY, ciudad);
    return VIEW_CITY_FORM;
  }

  @GetMapping(value = PATH_ID, headers = { X_REQUESTED_WITH_XML_HTTP_REQUEST })
  public String showUpdateCityForm(@PathVariable(ID) String id,
                                   ModelMap modelMap) {
    LOG.info("Requesting ciudad {} via XHR", id);

    // Let Thymeleaf only return the th:fragment="form" within the view
    return showUpdateCityPage(id, modelMap) + FRAGMENT_FORM;
  }

  @PostMapping(value = PATH_ID)
  public RedirectView updateCity(@PathVariable(ID) String id,
                                 @ModelAttribute("ciudad") Ciudad ciudad) {
    LOG.info("Updating ciudad {}", id);

    ciudadDao.update(ciudad);
    return new RedirectView("");
  }

  @PostMapping(headers = { X_REQUESTED_WITH_XML_HTTP_REQUEST }, params = { "pk" })
  @ResponseStatus(code = NO_CONTENT)
  public void partialUpdateCity(@RequestParam("pk") String id,
                                @RequestParam("name") String parameterNombre,
                                @RequestParam("value") String value) {
    Ciudad ciudad = ciudadDao.find(id).orElseThrow(notFoundException());
    if ("nombre".equalsIgnoreCase(parameterNombre)) {
      ciudad.setNombre(value);
    } else if ("poblacion".equalsIgnoreCase(parameterNombre)) {
      ciudad.setPoblacion(Integer.parseInt(value));
    } else if ("foundedIn".equalsIgnoreCase(parameterNombre)) {
      ciudad.setFundadaEn(Integer.parseInt(value));
    } else {
      LOG.warn("Invalid request for updating a ciudad. Parameter nombre '{}', value '{}'", parameterNombre, value);
      return;
    }
    ciudadDao.update(ciudad);
  }

  @GetMapping(value = PATH_ID + "/delete")
  public String showDeleteCityPage(@PathVariable(ID) String id,
                                   ModelMap modelMap) {
    Ciudad ciudad = ciudadDao.find(id).orElseThrow(notFoundException());
    modelMap.addAttribute(MODEL_ATTRIBUTE_CITY, ciudad);

    return VIEW_CITY_DELETE;
  }

  @GetMapping(value = PATH_ID + "/delete", headers = { X_REQUESTED_WITH_XML_HTTP_REQUEST })
  public String showDeleteCityForm(@PathVariable(ID) String id,
                                   ModelMap modelMap) {
    LOG.info("Requesting delete ciudad form {} via XHR", id);

    return showDeleteCityPage(id, modelMap) + FRAGMENT_FORM;
  }

  @PostMapping(value = PATH_ID + "/delete")
  public RedirectView deleteCity(@PathVariable(ID) String id) {
    LOG.info("Deleting ciudad {}", id);

    ciudadDao.remove(id);
    return new RedirectView("/ciudades");
  }

  @ModelAttribute("section")
  public String section() {
    return SECTION_CITIES;
  }

  private Supplier<HttpClientErrorException> notFoundException() {
    return () -> new HttpClientErrorException(HttpStatus.NOT_FOUND);
  }
}