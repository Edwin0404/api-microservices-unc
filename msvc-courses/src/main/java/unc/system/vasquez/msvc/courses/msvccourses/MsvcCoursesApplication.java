package unc.system.vasquez.msvc.courses.msvccourses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsvcCoursesApplication.class, args);
    }

}
