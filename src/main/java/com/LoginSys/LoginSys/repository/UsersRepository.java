package com.LoginSys.LoginSys.repository;

import com.LoginSys.LoginSys.Model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel,Integer>
{
  //  List<UsersModel> findAll();

    @Query("select u from UsersModel u where u.login = ?1 and u.password = ?2")
    Optional<UsersModel> findByLoginAndPassword(String login, String password);
//    {
//        return null;
//    }
}
