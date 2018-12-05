package application.appWeb;

import infra.EventMongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppWebApplication.class, args);
    }
}
