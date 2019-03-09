
package es.curso.java.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  static final String VIEW_INDEX = "pages/index";

  @RequestMapping(value = "")
  public String getHome() {
    return VIEW_INDEX;
  }
}