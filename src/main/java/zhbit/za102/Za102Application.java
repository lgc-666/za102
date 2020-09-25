package zhbit.za102;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@MapperScan(basePackages = {"zhbit.za102.dao"})
@EnableScheduling
public class Za102Application {

    public static void main(String[] args) {
        SpringApplication.run(Za102Application.class, args);
    }

}
