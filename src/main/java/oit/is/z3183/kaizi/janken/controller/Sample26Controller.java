package oit.is.z3183.kaizi.janken.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Sample26Controller {

  @GetMapping("/sample26")
  public String sample26() {
    return "sample26";
  }

  @PostMapping("/sample26")
  public String sample26Post(
      @RequestParam int min,
      @RequestParam int max,
      ModelMap model) {

    ArrayList<Integer> numList = new ArrayList<Integer>();

    int sum = 0;

    for (int i = min; i <= max; i++) {
      numList.add(i);
      sum += i;
    }

    model.addAttribute("numList", numList);
    model.addAttribute("sum", sum);

    return "sample26";
  }
}
