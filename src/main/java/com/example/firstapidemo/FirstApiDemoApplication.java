package com.example.firstapidemo;

import com.example.firstapidemo.dao.StudentDao;
import com.example.firstapidemo.dto.StudentDto;
import com.example.firstapidemo.entity.Student;
import com.example.firstapidemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class FirstApiDemoApplication {

	private final StudentDao studentDao;

	@Bean
	@Transactional @Profile("data")
	public ApplicationRunner runner(){
		return runner -> {
			List.of(
					new Student("John","Doe","john@gmail,com","ISM"),
					new Student("John","William","wiilian@gmail.com","KMD"),
					new Student("Wyne","Ronney","ronney@gamil.com","FBT")
			).forEach(studentDao::save);
		};

	}

	public static void main(String[] args) {
		SpringApplication.run(FirstApiDemoApplication.class, args);
	}

}
