package zhbit.za102;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import zhbit.za102.Utils.PortUtil;
@SpringBootApplication
@ServletComponentScan
@EnableCaching
@MapperScan(basePackages = {"zhbit.za102.dao"})
@EnableScheduling
public class Za102Application {
    static {
        PortUtil.checkPort(3306,"mysql 服务端",true);
        PortUtil.checkPort(6379,"Redis 服务端",true);
        //PortUtil.checkPort(9300,"ElasticSearch 服务端",true);
        //PortUtil.checkPort(5601,"Kibana 工具", true);
    }
    public static void main(String[] args) {
        SpringApplication.run(Za102Application.class, args);
    }

}
