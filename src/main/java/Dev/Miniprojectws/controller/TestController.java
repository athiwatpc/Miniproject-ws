package Dev.Miniprojectws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class TestController {

    @PostMapping("/test")
    public String test (){
        return "success";
    }

    @PostMapping("/testNotToken")
    public String testNotToken (){
        return "success";
    }
}
