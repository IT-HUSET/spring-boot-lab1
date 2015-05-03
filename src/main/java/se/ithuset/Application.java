package se.ithuset;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class Application  {

    @Autowired
    private String helloMessage;

    public static void main( String[] args ) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Application bean = context.getBean(Application.class);
        System.out.println(bean.helloMessage);
    }

    @Bean
    public String helloMessage() {
        return "Hello IT-HUSET!";
    }

}
