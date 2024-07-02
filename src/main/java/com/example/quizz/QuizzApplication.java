package com.example.quizz;

import com.example.quizz.entity.*;
import com.example.quizz.repository.CategoryRepository;
import com.example.quizz.repository.RoleRepository;
import com.example.quizz.repository.TopicRepository;
import com.example.quizz.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class QuizzApplication {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(QuizzApplication.class, args);
    }
    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            if(roleRepository.count()==0){
                roleRepository.save(new Role(ERole.ROLE_USER));
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
            }
            if(userRepository.count()==0){
                User user=new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.getRoles().add(roleRepository.findById(ERole.ROLE_ADMIN.name()).get());
                userRepository.save(user);
            }
            if(categoryRepository.count()==0){
                Category category1=new Category();
                category1.setName("Lập Trình");
                Category category2=new Category();
                category2.setName("Toán học");
                Category category3=new Category();
                category3.setName("Khác");
                categoryRepository.save(category3);
                categoryRepository.save(category2);
                categoryRepository.save(category1);
            }

        };
    }

}
