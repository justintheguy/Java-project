package com.project.forcedepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class ForceDepartmentApplication {
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ForceDepartmentApplication.class, args);
	}
}
