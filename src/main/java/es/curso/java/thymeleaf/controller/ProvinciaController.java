package es.curso.java.thymeleaf.controller;

import java.util.function.Supplier;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import es.curso.java.thymeleaf.dao.ProvinciaDao;
import es.curso.java.thymeleaf.entity.Provincia;

@Controller
@RequestMapping("provincias")
public class ProvinciaController {
	
	public static final String VIEW_PROVINCIAS = "pages/provincias";
	public static final String VIEW_PROVINCIA_FORM = "pages/provincias-form";
	public static final String VIEW_PROVINCIA_DELETE = "pages/provincia-delete";
	public static final String MODEL_ATTRIBUTE_PROVINCIAS = "provincias";
	public static final String MODEL_ATTRIBUTE_PROVINCIA = "provincia";
	public static final String FRAGMENT_FORM = " :: form";
	public static final String SECTION_PROVINCIAS = "provincias";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvinciaController.class);
	private static final String ID = "id";
	private static final String PATH_ID = "/{id}";
	private static final String X_REQUESTED_WITH_XML_HTTP_REQUEST = "X-Requested-With=XMLHttpRequest";
	
	@Resource
	private ProvinciaDao provinciaDao;
	
	/*
	 * Método para la página principal, para mostrar el listado de provincias
	 */
	@RequestMapping
	public String overview(ModelMap modelMap) {
		modelMap.addAttribute(MODEL_ATTRIBUTE_PROVINCIAS, provinciaDao.getAll());
		return VIEW_PROVINCIAS;
	}
	
	@GetMapping(value=PATH_ID)
	public String showUpdateProvinciaPage(@PathVariable(ID) String id, ModelMap modelMap) {
		Provincia provincia = provinciaDao.find(id).orElseThrow(notFoundException());
		modelMap.addAttribute(MODEL_ATTRIBUTE_PROVINCIA, provincia);
		return VIEW_PROVINCIA_FORM;
	}
	
	/*
	 * Devuelve el th:fragment="form".
	 * Utiliza una petición XHR (XMLHttpRequest), que permite realizar peticiones a un servidor 
	 * permitiendo actualizar la página sin recargarla, solicitar y recibir datos del servidor 
	 * tras haber cargado la página, y enviar datos al servido en segundo plano.
	 */
	@GetMapping(value=PATH_ID, headers= {X_REQUESTED_WITH_XML_HTTP_REQUEST})
	public String showUpdateProvinciaForm(@PathVariable(ID) String id, ModelMap modelMap) {
		
		LOGGER.info("Requesting provincia {} via XHR", id);
		return showUpdateProvinciaPage(id, modelMap) + FRAGMENT_FORM;
	}
	
	/*
	 * HttpClientErrorException es la excepción que se lanza cuando se recibe una excepción 4xx
	 * Supplier<T>: proveedor de resultados, utilizado en contextos en los que no hay inputs, pero se espera un output.
	 */
	private Supplier<HttpClientErrorException> notFoundException(){
		return () -> new HttpClientErrorException(HttpStatus.NOT_FOUND);
	}

}
