package agency.config;

import agency.entity.Policy;
import agency.entity.listeners.CustomerListener;
import agency.entity.listeners.PolicyListener;
import agency.entity.listeners.ProductListener;
import agency.entity.listeners.UserListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    @Bean
    public UserListener userListener() {
        return new UserListener();
    }

    @Bean
    public CustomerListener customerListener() {
        return new CustomerListener();
    }

    @Bean
    public ProductListener productListener() {
        return new ProductListener();
    }

    @Bean
    public PolicyListener policyListener() {
        return new PolicyListener();
    }
}
