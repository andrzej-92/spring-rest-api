package agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(value= "agency/security")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}