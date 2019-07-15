package org.modrarus.govservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GovserviceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(GovserviceApplication.class, args);
	}

}
