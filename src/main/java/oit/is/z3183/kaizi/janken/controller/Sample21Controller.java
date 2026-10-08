//package oit.is.controller;
package oit.is.z3183.kaizi.janken.controller;
//package oit.is.inudaisuki.springboot_samples.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Sample21Controller {

  // http://localhost:8080/sample21 にアクセスされたとき
  @GetMapping("/sample21")
  public String sample21() {
    return "sample21"; // templates/sample21.html を表示
  }

  @GetMapping("/sample22/{x}/{y}")
  public String sample22(
      @PathVariable int x,
      @PathVariable int y,
      ModelMap model) {
    model.addAttribute("result", x + y);

    return "sample21";
  }

  @GetMapping("/sample23")
  public String sample23(
      @RequestParam int tasu1,
      @RequestParam int tasu2,
      ModelMap model) {

    model.addAttribute("x", tasu1);
    model.addAttribute("y", tasu2);
    model.addAttribute("result", tasu1 + tasu2);

    return "sample21";
  }

  @GetMapping("/sample24")
  public String sample24() {
    return "sample24";
  }

  @PostMapping("/sample25")
  public String sample25(
      @RequestParam int kakeru1,
      @RequestParam int kakeru2,
      ModelMap model) {

    model.addAttribute("result", kakeru1 * kakeru2);

    return "sample24";
  }

}
