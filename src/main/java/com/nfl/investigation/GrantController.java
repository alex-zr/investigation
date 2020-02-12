package com.nfl.investigation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrantController {
    @GetMapping(path = "/hello")
    @PreAuthorize("hasAnyAuthority('USER', 'MANAGER')")
    public String hello() {
        return "Hello world!";
    }
}
