package com.in28min.springboot.myFirstWebApp.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    // say-hello => hello what are u doing
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(String name) {
        return "Hello what are u doing";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHTML(String name) {

        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> MY first HTML Page </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("Hello what are u doing BODY");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }



}
