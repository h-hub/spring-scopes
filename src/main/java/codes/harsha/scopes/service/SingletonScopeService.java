package codes.harsha.scopes.service;

import org.springframework.stereotype.Service;

@Service
public class SingletonScopeService {

    public String getInstanceId(){
        return this.toString();
    }

}
