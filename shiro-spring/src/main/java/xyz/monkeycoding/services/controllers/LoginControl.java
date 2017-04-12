package xyz.monkeycoding.services.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CZN on 2017/4/12.
 */
@Controller
public class LoginControl {
    private static final Logger logger = LoggerFactory.getLogger(LoginControl.class);

    @RequestMapping("/login")
    @ResponseBody
    public String login() {

        String username = "admin";
        String password = "admin";

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);

            System.out.println("Login Success:" + token.getPrincipal());
            return "success";
        } catch (Exception ex) {
            logger.error("login exception: ", ex);
        }
        return "false";
    }
}
