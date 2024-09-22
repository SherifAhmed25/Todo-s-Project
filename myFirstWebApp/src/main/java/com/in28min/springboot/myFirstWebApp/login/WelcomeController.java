package com.in28min.springboot.myFirstWebApp.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String WelcomePage( ModelMap model) {
        model.put("name",getLoggedinUsername());
        return "welcome";
    }
    private String getLoggedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
       return authentication.getName();
    }
//    @RequestMapping(value = "loginPage",method = RequestMethod.POST)
//    public String goToWelocomePage(@RequestParam String name,@RequestParam String password, ModelMap model) {
//        model.put("name",name);
//
//        if  (loginAuthenticationService.authenticate(name, password)) {
//            return "welcome";
//        }
//
//        model.put("errorMessage", "Invalid Credentials");
//
//        return "sayHelloLogin";
//    }
}
