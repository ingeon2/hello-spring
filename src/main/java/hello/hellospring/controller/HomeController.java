package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //처음페이지를 의미 (localhost8080/  이게 처음 페이지잖아.)
                     //컨테이너에 컨트롤러 들어가니까, 우선순위에 의해서 이전에 만든 index.html은 후순위로 밀려나 처음페이지에 뜨질 못하지.
    public String home(){
        return "home"; //@컨트롤러 에서부터 읽으면, 첫화면(/)에 home.html(템플릿에잇슴) 리턴 해라! 라는 의미
    }
}
