package com.example.beanvalidationdemo.service;

import com.example.beanvalidationdemo.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
// 数据验证还可以用在任意的组件上，而不是控制器级别的输入。
public class PersonService {

    public void validatePerson(@Valid Person person) {
        // do something
    }

    @Validated(AddPersonGroup.class)
    public void validatePersonGroupForAdd(@Valid Person person) {
        // do something
    }

    @Validated(DeletePersonGroup.class)
    public void validatePersonGroupForDelete(@Valid Person person) {
        // do something
    }

}
