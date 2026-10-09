package oit.is.z3183.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken(
      @RequestParam(required = false) String username,
      Model model) {

    if (username != null) {
      model.addAttribute("username", username);
    }

    return "janken";
  }
}
