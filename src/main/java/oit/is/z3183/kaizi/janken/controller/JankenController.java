package oit.is.z3183.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import oit.is.z3183.kaizi.janken.model.Janken;
import oit.is.z3183.kaizi.janken.model.Entry;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @GetMapping("/janken")
  public String janken(
      @RequestParam(required = false) String hand,
      Principal principal,
      Model model) {

    //if (principal != null) {
    //  model.addAttribute("username", principal.getName());


    String loginUser = principal.getName();

    entry.addUser(loginUser);
    model.addAttribute("username", loginUser);
    model.addAttribute("users", entry.getUsers());

    if (hand != null) {
      Janken janken = new Janken(hand, "グー");
      model.addAttribute("myHand", janken.getMyHand());
      model.addAttribute("cpuHand", janken.getCpuHand());
      model.addAttribute("result", janken.getResult());
    }

    return "janken.html";
  }

    @GetMapping("step8")
  public String sample38(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);

    return "sample37.html";
  }
}
