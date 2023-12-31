package com.tutorial.keycloakbackend.controler;

import com.tutorial.keycloakbackend.dto.ResponseMessage;
import com.tutorial.keycloakbackend.model.User;
import com.tutorial.keycloakbackend.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserControler {

    @Autowired
    private KeycloakService keycloakService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){

       Object[] obj = keycloakService.createUser(user);
        int status = (int )obj[0];

        ResponseMessage message = (ResponseMessage)obj[1];

        return ResponseEntity.status(status).body(message);


    }
}
