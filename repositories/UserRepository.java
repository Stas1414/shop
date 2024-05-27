package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUsersByUsername(String userName);

    default List<User> findUsersByRoleList(String role){
        List<User> all = findAll();
        List<User> result=new ArrayList<>();
        for(User user : all){
            if(user.getRoleList().get(0).getRole().equals(role)){
                result.add(user);
            }
        }
        return result;
    }
}
