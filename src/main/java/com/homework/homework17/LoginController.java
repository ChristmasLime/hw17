package com.homework.homework17;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping ("/login")
    public String login(@RequestParam String login, @RequestParam String password, @RequestParam String confirmPassword) {
        try {
            boolean result = loginService.login(login, password, confirmPassword);
            if (result) {
                return "Вы успешно зарегистрировались";
            } else {
                return "Ошибка при регистрации";
            }
        } catch (WrongLoginException ex) {
            System.err.println("Ошибка при регистрации: " + ex.getMessage());
            return "Ошибка при регистрации. " + ex.getMessage();
        } catch (WrongPasswordException ex) {
            System.err.println("Ошибка при регистрации: " + ex.getMessage());
            return "Ошибка при регистрации. Проверьте пароль";
        } catch (IllegalArgumentException ex) {
            System.err.println("Ошибка при регистрации: " + ex.getMessage());
            return "Ошибка при регистрации. Проверьте логин и пароль";
        } catch (Exception ex) {
            System.err.println("Ошибка при регистрации: " + ex.getMessage());
            return "Ошибка при регистрации";
        }
    }

}