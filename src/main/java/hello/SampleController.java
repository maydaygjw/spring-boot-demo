package hello;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by Junwen on 6/16/15.
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

    @RequestMapping("/validation")
    @ResponseBody
    String withValidation(@Valid Input input, BindingResult bindResult) {
        if(bindResult.hasErrors()) {
            bindResult.getAllErrors().stream().forEach(objectError -> System.out.println(objectError.toString()));
        }
        return "Success";
    }

    @Data
    @ToString
    private static class Input {
        int inputId;
        @NotBlank
        String inputValue;
    }
}
