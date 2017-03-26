package xyz.monkeycoding.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by czn on 2017/3/26.
 */
@Controller
public class DefaultControll {

    @RequestMapping("/")
    @ResponseBody
    public String check() {
        return "SUCCESS";
    }
}
