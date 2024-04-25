package com.example.lab12.Controller;

import com.example.lab12.Api.ApiResponse;
import com.example.lab12.Model.User;
import com.example.lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(user);

    }


    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Welcome back!");

    }


    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("Done successfully logout!");

    }

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(){//admin

        return ResponseEntity.status(200).body(authService.getAllUsers());
    }


//    @PostMapping("/add-user")
//    public ResponseEntity addUser(@RequestBody @Valid User user){
//        authService.addUser(user);
//        return ResponseEntity.status(200).body(new ApiResponse("User Added "));
//    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @RequestBody @Valid User newUser){
        authService.updateUser(user.getUsername(), newUser);
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User user, @PathVariable String username){
        authService.deleteUser(username);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }


//    @DeleteMapping("/delete/{username1}/{username2}")//admin
//    public ResponseEntity deleteUser(@PathVariable String username1,@PathVariable String username2){
//        authService.deleteUser(username1,username2);
//        return ResponseEntity.status(200).body(new ApiResponse("User deleted!"));
//    }
//


}
