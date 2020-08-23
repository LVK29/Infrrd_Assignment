package infrrd.lvk.fileCrud;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("infrrd.lvk.fileCrud"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"File CRUD API",
				"Assignment from Infrrd",
				"1.0",
				"",
				new springfox.documentation.service.Contact("Kartikeyan LV", "https://www.linkedin.com/in/kartikeyan-l-v-bb070315b/", "lvkartikeyan@gmail.com"),
				"Backend Engineer",
				"",
				Collections.emptyList()	
				);
	}

}
