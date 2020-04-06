package cn.edu.seu.agdatab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.seu.agdatab.*.mapper")
public class AgdatabApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgdatabApplication.class, args);
    }
}
