package ru.bkmz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bkmz.demo.entity.User;
import ru.bkmz.demo.repo.UserDetailsRepo;

@RestController
@RequestMapping("/api/login")
public class LoginController {


    private final UserDetailsRepo userDetailsRepo;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Autowired
    public LoginController(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @GetMapping("")
    public String getClientId() {
        return this.clientId;
    }

    @GetMapping("client")
    public User getClient(@AuthenticationPrincipal User user) {

        return  userDetailsRepo.findOneById(user.getId());
    }

}
