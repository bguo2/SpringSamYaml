package com.example.springyamlpropsam;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    public String name;
    public String email;
    private Date creationDate;

}
