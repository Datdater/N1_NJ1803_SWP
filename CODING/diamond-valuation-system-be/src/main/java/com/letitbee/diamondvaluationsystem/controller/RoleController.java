package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.enums.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

    @GetMapping
    public Map<String, String> getRoles() {

        Map<String, String> roles = new HashMap<>();
        for (Role role : Role.values()) {
            roles.put(role.name(), role.name());
        }
        return roles;
    }
}
