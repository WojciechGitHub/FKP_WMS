package pl.fkpsystem.FKP_WMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.fkpsystem.FKP_WMS.converter.BarcodeConverter;

@SpringBootApplication
public class FkpWmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FkpWmsApplication.class, args);
	}

}

