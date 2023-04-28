package com.homework.homework17;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean login(String login, String password, String confirmPassword) {
        try {
            if (login.length() > 20) {
                throw new WrongLoginException("Логин слишком длинный");
            }
            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Пароли не совпадают");
            }
            if (!login.matches("[a-zA-Z0-9_]+") || !password.matches("[a-zA-Z0-9_]+")) {
                throw new IllegalArgumentException("Логин и пароль должны содержать только латинские буквы, цифры и знак подчеркивания");
            }
            if (password.length() >= 20) {
                throw new WrongPasswordException("Пароль слишком длинный");
            }

            return true;
        } catch (WrongLoginException | WrongPasswordException | IllegalArgumentException ex) {
            throw ex;
        }
    }

}