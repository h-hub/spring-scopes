package codes.harsha.scopes.controller;

import codes.harsha.scopes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {

    private SessionScopeComponent sessionScopeComponent;

    private PrototypeScopeComponent prototypeScopeComponent;

    private SingletonScopeService singletonScopeService;

    private SingletonScopeComponent singletonScopeComponent;

    private RequestScopeComponent requestScopeComponent;

    public HomeController(SessionScopeComponent sessionScopeComponent, PrototypeScopeComponent prototypeScopeComponent,
                          SingletonScopeService singletonScopeService, SingletonScopeComponent singletonScopeComponent,
                          RequestScopeComponent requestScopeComponent) {
        this.sessionScopeComponent = sessionScopeComponent;
        this.prototypeScopeComponent = prototypeScopeComponent;
        this.singletonScopeService = singletonScopeService;
        this.singletonScopeComponent = singletonScopeComponent;
        this.requestScopeComponent = requestScopeComponent;
    }

    @GetMapping("/secure")
    String index() {
        return "home/homeSignedIn";
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="no name") String name, Model model) {
        sessionScopeComponent.addName(name);
        model.addAttribute("name", name);
        model.addAttribute("names", sessionScopeComponent.getNames());
        model.addAttribute("id", sessionScopeComponent.getInstanceId());
        return "greeting";
    }

    @GetMapping("/secure/prototype")
    public String prototype(Model model) {
        model.addAttribute("id", prototypeScopeComponent.getInstanceId());
        return "prototype";
    }

    @GetMapping("/secure/singleton")
    public String singletonService(Model model) {
        model.addAttribute("serviceid", singletonScopeService.getInstanceId());
        model.addAttribute("componentid", singletonScopeComponent.getInstanceId());
        return "singleton";
    }

    @GetMapping("/secure/request")
    public String requestService(Model model) {
        model.addAttribute("username", requestScopeComponent.getUsername());
        model.addAttribute("id", requestScopeComponent.getInstanceId());
        return "request";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

}
