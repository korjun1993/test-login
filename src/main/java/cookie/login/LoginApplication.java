package cookie.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cookie.login.jwt.config.JwtConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	JwtConfigurationProperties.class
})
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
