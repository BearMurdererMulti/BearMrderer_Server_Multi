package com.server.bearmurderermulti.domain.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String account;

    private String password;

}