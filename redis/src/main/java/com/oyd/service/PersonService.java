package com.oyd.service;

import com.oyd.pojo.Person;

public interface PersonService {

    public Object getPerson(Integer id);

    int setPerson(Person person);
}
