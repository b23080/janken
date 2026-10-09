package oit.is.z3183.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z3183.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken(
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String hand,
      Model model) {

    if (username != null) {
      model.addAttribute("username", username);
    }

    if (hand != null) {
      Janken janken = new Janken(hand, "グー");
      model.addAttribute("myHand", janken.getMyHand());
      model.addAttribute("cpuHand", janken.getCpuHand());
      model.addAttribute("result", janken.getResult());
    }

    return "janken";
  }
}
