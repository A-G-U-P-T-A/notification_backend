package com.backend.notificationengine;

import com.backend.notificationengine.services.ConfigLoaderService;
import com.backend.notificationengine.services.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NotificationengineApplication {

	@Autowired
	ConfigLoaderService configLoaderService;

	public static void main(String[] args) {
		SpringApplication.run(NotificationengineApplication.class, args);
	}

	@PostConstruct
	private void init() {
		System.out.println("INIT APPLICATION START");
		configLoaderService.initConfig();
		System.out.println("INIT APPLICATION STOP");
	}

}
