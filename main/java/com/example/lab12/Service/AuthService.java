package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public void register(User user){
        user.setRole("CUSTOMER");
        String hashPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

    public List<User> getAllUsers() {// admin
        if(authRepository.findAll().isEmpty()){
            throw new ApiException("List Empty");
        }

       else return authRepository.findAll();
    }

//    public void addUser(User user) {
//        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//        authRepository.save(user);
//
//    }

    public void updateUser(String username, User user) {

        User u=authRepository.findUserByUsername(username);
        if (u == null) {
            throw new ApiException("Not found user");
        }
        //u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        authRepository.save(u);

    }

    public void deleteUser(String username) {//admin
        User u=authRepository.findUserByUsername(username);
        if (u == null) {
            throw new ApiException("Not found user");
        }
        authRepository.delete(u);

    }






}
