package me.ljseokd.studyspringbootwebapp.account;

import me.ljseokd.studyspringbootwebapp.dto.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute SignUpForm signUpForm){
        return "account/sign-up";
    }
}
