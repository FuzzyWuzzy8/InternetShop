package pl.air.internetShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import pl.air.internetShop.init.DataLoader;

@SpringBootApplication
public class PApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(PApplication.class, args);
    }

    @Autowired
    private DataLoader dataLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoader.insertData();
    }


}
