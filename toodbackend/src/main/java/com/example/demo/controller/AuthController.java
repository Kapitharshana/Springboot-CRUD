package com.example.demo.controller;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService us;
    private final UserRepository ur;
    private final PasswordEncoder passenc;
    private final JwtUtil jwtutil;


    @PostMapping("/register")
    public ResponseEntity<String> registernuser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=passenc.encode(body.get("password"));

        if (ur.findByEmail(email).isPresent())
        {
           return new ResponseEntity<>("Email aleady exists", HttpStatus.CONFLICT);

        }

        us.craeteuser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("Email successfully resgistered", HttpStatus.CREATED);




    }




    @PostMapping("/login")
    public ResponseEntity <?> loginuser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");

        var userOptional=ur.findByEmail(email);
        if (userOptional.isEmpty())
        {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);

        }

        User user= userOptional.get();
        if(!passenc.matches(password, user.getPassword())){
            return new ResponseEntity<>("Email not found,password incorrect", HttpStatus.UNAUTHORIZED);

        }
        //if the stored hashed password and user_password are matched then server will create a token for user
        String token=jwtutil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));


    }

}
