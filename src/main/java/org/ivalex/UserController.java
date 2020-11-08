package org.ivalex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/people")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public ModelAndView hello() {

        ModelAndView mav = new ModelAndView("index");

        List<User> userList = service.listAll();

        mav.addObject("message", userList);
        return mav;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("person", service.showById(id));
        return "/show";
    }


    @GetMapping("/new")
    public String newPerson(Model model) {

        model.addAttribute("person", new User());
        return "new_user";
    }

    @PostMapping
    public String create(@ModelAttribute("person") User user) {

        service.add(user);
        return "redirect:/people";
    }
}
