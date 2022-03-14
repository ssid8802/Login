package com.LoginSys.LoginSys.service;

import com.LoginSys.LoginSys.Model.UsersModel;
import com.LoginSys.LoginSys.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public  class UsersService
{

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }
    public UsersModel registerUser(Integer id,String login,String password,String email)
    {

        if(login == null || password == null)
            return null;
        else{
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
//            String s=password;
//            Integer key = s.length();
//
//            String pass = usersModel.getPassword();
//            char[] pass_arr = pass.toCharArray();
//            //     System.out.println(pass);
//            for(int i=0;i<key;++i)
//                pass_arr[i] += key;
//
//            pass = String.valueOf(pass_arr);
//            usersModel.setPassword(password);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            usersModel.setId(id);



            return usersRepository.save(usersModel);

        }


    }

    public UsersModel updateUser(Integer id,String login,String password,String email) {
        UsersModel usersModel = usersRepository.findByLoginAndPassword(login, password).orElse(null);
        if (usersModel != null)
        {
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
       //     usersModel.setId(id);
            return usersRepository.save(usersModel);
        }

        System.out.println("Hello World");

        //usersRepository.update(usersModel);
        return usersModel;
    }


    public UsersModel authenticate(String login, String password)
    {
        UsersModel usersModel = usersRepository.findByLoginAndPassword(login, password).orElse(null);
        return usersModel;
    }


}
