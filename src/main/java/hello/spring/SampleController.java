package hello.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Junwen on 6/16/15.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan
public class SampleController {

    @Autowired
    public MyService service;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello!" + service.serve();
        //return "Hello!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
