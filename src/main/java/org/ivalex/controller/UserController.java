package org.ivalex.controller;

import org.ivalex.model.User;
import org.ivalex.dao.UserDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/people")
public class UserController {


    @GetMapping("")
    public ModelAndView hello() {

        ModelAndView mav = new ModelAndView("index");
        List<User> userList = UserDaoImpl.listAll();
        mav.addObject("message", userList);
        return mav;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("person", UserDaoImpl.showById(id));
        return "/show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") User user) {

        return "new_user";
    }

    @PostMapping
    public String create(@ModelAttribute("person") User user) {

        UserDaoImpl.add(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {

        model.addAttribute("person", UserDaoImpl.showById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")User user, @PathVariable("id") Long id) {


        UserDaoImpl.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {


        UserDaoImpl.delete(id);
        return "redirect:/people";
    }
}
