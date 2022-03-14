package com.LoginSys.LoginSys.Controller;

import com.LoginSys.LoginSys.Model.UsersModel;
import com.LoginSys.LoginSys.service.PhoneverificationService;
import com.LoginSys.LoginSys.service.UsersService;
import com.LoginSys.LoginSys.service.VerificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UsersController
{

//    @GetMapping("/home")
//    public String home(){
//        return "this is loginn page";
//    }

    @Autowired
    private UsersService usersService;

    @Autowired
    private PhoneverificationService phonesmsservice;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model)
    {
      //  System.out.print("sid");
        model.addAttribute("registerrequest",new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("loginrequest",new UsersModel());
        return "login_page";
    }

    @GetMapping("/update")
    public String updateProfile(Model model)
    {
        model.addAttribute("updaterequest",new UsersModel());
        return "update_page";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute UsersModel usersModel)
    {
        System.out.println("updateRequest" + usersModel);
        UsersModel updateUser =usersService.updateUser(usersModel.getId(),usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return updateUser==null?"error_page":"Upd_successful";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel)
    {
        System.out.println("registerRequest" + usersModel);
        UsersModel registerUser =usersService.registerUser(usersModel.getId(),usersModel.getLogin(),usersModel.getPassword(),usersModel.getEmail());
        return registerUser==null?"error_page":"Successful";
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


    @PostMapping("/sendotp")
    public ResponseEntity<String> sendotp(@RequestParam("phone") String phone)
    {
      //  System.out.println(phone);
        VerificationResult result=phonesmsservice.startVerification(phone);
        if(((VerificationResult) result).isValid())
        {
            return new ResponseEntity<>("Otp Sent..",HttpStatus.OK);
        }
        System.out.println("Hi Siddharth");
        return new ResponseEntity<>("Otp failed to sent..",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/verifyotp")
    public ResponseEntity<String> sendotp(@RequestParam("phone") String phone, @RequestParam("otp") String otp)
    {
        VerificationResult result=phonesmsservice.checkverification(phone,otp);
        if(result.isValid())
        {
            return new ResponseEntity<>("Your number is Verified",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something wrong/ Otp incorrect",HttpStatus.BAD_REQUEST);
    }


}
