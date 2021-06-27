package hi.spring.hispring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    // API 방식
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring" 이라는 데이터를 보냄. 뷰로 보여주지 않음!
    }

    // 데이터만 보낼 수 있는 특성 덕분에 API를 사용하면 좋음
    @GetMapping("hello-api")
    @ResponseBody // 스프링 컨테이너에서 컨트롤러에 해당하는 html이 없으면 정적 html파일로 브라우저에 넘기는ㅔ 원래 동작 방식 인데, @ResponseBody를 사용하면 그냥 바로 데이터가 HttpMessageConverter로 넘겨진다
    // 근데 이때 객체가 넘어오면 JsonConverter(객체를 json으로 바꿔주는 대표적인 라이브럴 : MappingJackson2HttpMessageConverter)를 사용하고, String이면 StringConverter를 사용한다
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
