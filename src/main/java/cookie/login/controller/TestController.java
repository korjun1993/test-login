package cookie.login.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cookie.login.jwt.JwtTokenDecoder;
import cookie.login.jwt.JwtTokenFactory;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TestController {

	private final JwtTokenFactory jwtTokenFactory;
	private final JwtTokenDecoder jwtTokenDecoder;

	/**
	 * 세션을 이용한 로그인
	 */
	@GetMapping("/session/login")
	public String sessionLogin(HttpSession httpSession, @RequestParam String memberId) {
		httpSession.setAttribute("loginId", memberId);
		return "로그인 성공";
	}

	@GetMapping("/session/home")
	public String sessionHome(HttpSession httpSession) {
		if (httpSession.getAttribute("loginId") != null) {
			return "환영합니다 회원님";
		}
		return "로그인해주세요";
	}

	/**
	 * 토큰을 이용한 로그인
	 */
	@GetMapping("/jwt/login")
	public String jwtLogin(HttpServletResponse response, @RequestParam String memberId) {
		String token = jwtTokenFactory.generate(memberId, System.currentTimeMillis());
		Cookie cookie = new Cookie("jwtToken", token);
		response.addCookie(cookie);
		return "로그인 성공";
	}

	@GetMapping("/jwt/home")
	public String jwtHome(@CookieValue(value = "jwtToken", required = true) String jwtToken) {
		if (jwtTokenDecoder.verify(jwtToken)) {
			return "환영합니다 회원님";
		}
		return "로그인해주세요";
	}
}
