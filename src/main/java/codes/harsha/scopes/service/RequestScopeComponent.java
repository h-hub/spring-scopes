package codes.harsha.scopes.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeComponent {

    private String username;

    public RequestScopeComponent(){
        this.username = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getUsername() {
        return username;
    }

    public String getInstanceId(){
        return this.toString();
    }
}
