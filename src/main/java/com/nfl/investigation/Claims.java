package com.nfl.investigation;

import lombok.ToString;

import java.util.List;

@ToString
public class Claims {
    private String consoleName;
    private List<String> roles;

    public Claims(String consoleName, List<String> roles) {
        this.consoleName = consoleName;
        this.roles = roles;
    }

}
