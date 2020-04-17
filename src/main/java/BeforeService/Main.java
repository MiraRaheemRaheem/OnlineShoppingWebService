package BeforeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"services"})
public class Main
{
    public static void main(String[] args)
    {
        System.setProperty("server.port", "8081");
        SpringApplication.run(Main.class,args);

    }
}

