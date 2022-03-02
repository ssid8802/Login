package com.LoginSys.LoginSys.service;

import com.LoginSys.LoginSys.Model.UsersModel;
import com.LoginSys.LoginSys.repository.UsersRepository;

public class UsersService
{

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }
    public UsersModel registeruser(String username,String password,String email)
    {

        if(username == null || password == null)
            return null;
        else{
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(username);
            usersModel.setPassword(password);
            usersModel.setEmail(email);

            return usersRepository.save(usersModel);

        }


    }
    public UsersModel authenticate(String username, String password)
    {
        return usersRepository.findbyLoginandPassword(username,password).orElse(null);
    }
}
