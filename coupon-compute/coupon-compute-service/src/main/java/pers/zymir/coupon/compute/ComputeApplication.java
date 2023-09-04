package pers.zymir.coupon.compute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pers.zymir.coupon")
public class ComputeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComputeApplication.class);
    }
}
