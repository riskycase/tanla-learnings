package com.tanlaplatforms.hrishikeshpatil.employeeapp.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "security/login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage() {
        return "security/access-denied";
    }

}
