package com.penguin.vueboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class VueBootApplication {
    public static void main(String[] args){
        SpringApplication.run(VueBootApplication.class,args);
    }
}
@Controller
class VueBootTest{


    @GetMapping("/toThymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("thymeleaf","thymeleaf测试成功了~");
        return "thymeleaf";
    }

}


