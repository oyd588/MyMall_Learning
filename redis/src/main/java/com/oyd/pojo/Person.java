package com.oyd.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data

public class Person implements Serializable {
    @Id
    private Integer id;
    private String name;
    private Integer age;

}
