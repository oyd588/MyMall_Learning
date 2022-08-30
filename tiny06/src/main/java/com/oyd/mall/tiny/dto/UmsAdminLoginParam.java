package com.oyd.mall.tiny.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdminLoginParam {

    private String username;
    private String password;
}
