package com.ram.javacoderhint;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ram.javacoderhint.dao.EmployeeRepository;
import com.ram.javacoderhint.model.Employee;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(EmployeeRepository userDao){
        return args -> {
            Employee user1 = new Employee();
            user1.setFirstName("Ramrij");
            user1.setLastName("Bais");
            user1.setSalary(12345);
            user1.setAge(23);
            user1.setUsername("rambrij");
            user1.setPassword("rambrij");
            userDao.save(user1);

            Employee user2 = new Employee();
            user2.setFirstName("Rocky");
            user2.setLastName("Marsion");
            user2.setSalary(4567);
            user2.setAge(34);
            user2.setUsername("rocky");
            user2.setPassword("rocky");
            userDao.save(user2);
        };
    }

}
