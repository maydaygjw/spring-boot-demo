package hello.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by junior on 6/17/15.
 */

@Service
public class MyService {

    public String serve() {
        return "world";
    }
}
