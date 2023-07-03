package cookie.login.jwt;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import cookie.login.jwt.config.JwtConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenDecoder {

	private final JwtConfigurationProperties jwtConfig;

	public boolean verify(String jwtToken) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtConfig.secret())).build();
			verifier.verify(jwtToken);
			return true;
		} catch (JWTVerificationException e) {
			log.error("not verified JWT token");
		}
		return false;
	}
}
