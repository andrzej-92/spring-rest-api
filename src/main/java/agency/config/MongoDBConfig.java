package agency.config;

import agency.entity.listeners.UserListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    @Bean
    public UserListener userListener() {
        return new UserListener();
    }
}
