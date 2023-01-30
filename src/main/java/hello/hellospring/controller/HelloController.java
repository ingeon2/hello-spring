package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController { //MVC = 모델, 뷰, 컨트롤러 여기가 컨트롤러, hello.html이 뷰

    @GetMapping("hello") //웹에서 /hello 라고 들어가면 스프링부트가 여기 매서드를 호출해줌 (로컬호스트:8880/hello)
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //resources:templates/ +{ViewName}+ .html (클래스에서 리턴한 값을 스프링부트가 옆의 경로로 찾아줌)

    }

    @GetMapping("hello-mvc") //mvc방식
    public String helloMvc(@RequestParam("name") String name,Model model){ //그냥 /hello-mvc 로 가면 에러뜨는데,
        // 내가 따로 name을 지정하지 않아서다. 그럼 지정하려면 /hello-mvc?name=밸류값  으로 작성하면 된다.
        //여기할당 이 19행의 맨 오른쪽 name으로 할당되고, 23행의 맨 오른쪽 name으로 할당되면서,
        // 템플릿에 ${name}까지 할당되어 웹에 표시.
        // 이게 뭘 의미하냐? 컨트롤러에서 컨트롤 할 수 있다는걸 보여줌.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string") //(API방식인데 문자열 리턴)
    @ResponseBody //뷰 통해서(템플릿 역할)가 아닌 직접 http에 데이터를 넣겠다 라는 뜻
    //얘도 /hello-string?name=밸류값 으로 작성.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //여기의 리턴은 html 리턴값이 아닌 진짜 그냥 화면에 때려박는 리턴값.
    }

    @GetMapping("hello-api") //(API방식인데 Hello 클래스의 인스턴스 hello 객체 리턴)
    @ResponseBody//뷰 통해서(템플릿 역할)가 아닌 직접 http에 데이터를 넣겠다 라는 뜻
    public Hello helloApi(@RequestParam("name") String name){// /hello-api?name=밸류값 으로 작성, 밸류값이 여기의 String name이 되는것
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체는 json 데이터형태로 리턴된다 (스프링 디폴트값)
    }

    static class Hello{
        private String name; //매서드를 통해 접근하려고 프라이빗 변수.

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }






}
