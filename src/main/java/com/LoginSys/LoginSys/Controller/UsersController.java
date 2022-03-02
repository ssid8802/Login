package com.LoginSys.LoginSys.Controller;

import com.LoginSys.LoginSys.Model.UsersModel;
import com.LoginSys.LoginSys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController
{

//    @GetMapping("/home")
//    public String home(){
//        return "this is loginn page";
//    }

    @Autowired
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model)
    {
        model.addAttribute("registerrequest",new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("loginrequest",new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel)
    {
        System.out.println("registerRequest" + usersModel);
        UsersModel registerUser =usersService.registerUser(usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return registerUser==null?"error_page":"redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model) {
        System.out.println("loginRequest" + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null)
        {
            model.addAttribute("userLogin",authenticated.getLogin());
            return "personal_page";
        }
        else return "error_page";
    }

}
