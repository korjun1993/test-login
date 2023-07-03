package cookie.login.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cookie.login.jwt.config.JwtConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenFactory {

	private final JwtConfigurationProperties jwtConfig;

	public String generate(String memberId, long currentTime) {
		return JWT.create()
			.withClaim("loginId", memberId)
			.withExpiresAt(new Date(currentTime + jwtConfig.validityTime().toMillis()))
			.sign(Algorithm.HMAC512(jwtConfig.secret()));
	}
}

