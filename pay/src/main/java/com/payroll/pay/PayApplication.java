package com.payroll.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class PayApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PayApplication.class, args);
		Environment env = context.getEnvironment();
		log.info("Active Profile: {}", env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "default");
	}
}
