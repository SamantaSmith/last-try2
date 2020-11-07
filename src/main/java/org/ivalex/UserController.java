package org.ivalex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hi")
    public ModelAndView hello() {

        ModelAndView mav = new ModelAndView("index");

        List<User> userList = service.listAll();

        mav.addObject("message", userList);
        return mav;
    }
}
