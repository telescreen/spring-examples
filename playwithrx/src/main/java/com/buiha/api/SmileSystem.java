package com.buiha.api;

import com.buiha.models.User;
import com.buiha.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/")
public class SmileSystem {

    private static final Logger logger = LoggerFactory.getLogger(SmileSystem.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Principal getUserInfo(Principal principal) {
        return principal;
    }
}
