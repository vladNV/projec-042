package com.example.app.controller;

import com.example.app.model.Identity;
import com.example.app.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private IdentityService identityService;

    @GetMapping(Routes.ROOT)
    public String getting() {
        return getLoginPage();
    }

    @GetMapping(Routes.LOGIN)
    public String getLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return UI.LOGIN;
        }
        return"redirect:" + Routes.MANAGEMENT;
    }

    @GetMapping(Routes.WHO_I_AM)
    @ResponseBody
    public ResponseEntity<Identity> getIdentity() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = Optional.ofNullable(user).map(User::getUsername).orElse(null);
        if (login == null) {
            return Result.response(HttpStatus.UNAUTHORIZED);
        }
        Identity identity = identityService.getAllInformation(login);
        return Result.response(identity, HttpStatus.OK);
    }



}
