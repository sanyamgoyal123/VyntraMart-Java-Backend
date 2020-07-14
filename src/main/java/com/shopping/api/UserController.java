package com.shopping.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.shopping.entity.User;
import com.shopping.security.JWT.JwtProvider;
import com.shopping.service.UserService;
import com.shopping.vo.request.LoginForm;
import com.shopping.vo.response.JwtResponse;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        // throws Exception if authentication failed

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            System.out.println(authentication+"  "+loginForm);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generate(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println(userDetails);
            User user = userService.findOne(userDetails.getUsername());
            System.out.println("Data --> "+user);
            if(userDetails.getUsername().equals(user.getEmail()))
            {
            	 System.out.println("User Data --> "+user +"  "+jwt);
            	 Map<String,String> list=new HashMap<String,String>();
            	 list.put("token",jwt);
            	 list.put("account",user.getEmail());
            	 list.put("name",user.getName());
            	 list.put("role", user.getRole());
            	//return  ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getName(), user.getRole()));
            return new ResponseEntity<>(list,HttpStatus.OK);
            }
            } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<User> update(@RequestBody User user, Principal principal) {

        try {
            if (!principal.getName().equals(user.getEmail())) throw new IllegalArgumentException();
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<User> getProfile(@PathVariable("email") String email, Principal principal) {
        if (principal.getName().equals(email)) {
            return ResponseEntity.ok(userService.findOne(email));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
