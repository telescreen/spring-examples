package com.buiha.forms;

import com.buiha.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm userForm = (UserForm)target;

        if (userForm.getUsername().length() < 3 || userForm.getUsername().length() > 15) {
            errors.rejectValue("username", "field.size.userForm.username");
        }

        if (userForm.getPassword().length() < 8) {
            errors.rejectValue("password", "field.size.userForm.password");
        }
    }
}
