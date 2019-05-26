package com.poddubchak.oauth.Controller;

import com.poddubchak.oauth.Forms.UserForm;
import com.poddubchak.oauth.Model.User;
import com.poddubchak.oauth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/token")
    public String test () {

        return "Token works";
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {

        userService.signUp(userForm);
        return ResponseEntity.ok().build();
    }
}
