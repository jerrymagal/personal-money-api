package br.com.personal.money.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken accessToken, MethodParameter parameter, MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> httpMessageConverter, ServerHttpRequest httpRequest, ServerHttpResponse httpResponse) {
		
		HttpServletRequest request = ((ServletServerHttpRequest) httpRequest).getServletRequest();
		HttpServletResponse response = ((ServletServerHttpResponse) httpResponse).getServletResponse();
		
		String refreshToken = accessToken.getRefreshToken().getValue();
		
		adicionarRefreshTokenCookie(refreshToken, request, response);
		removerRefreshTokenBody((DefaultOAuth2AccessToken) accessToken);
		
		return accessToken;
	}

	private void adicionarRefreshTokenCookie(String refreshToken, HttpServletRequest request,
			HttpServletResponse response) {
		
		Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
		
		refreshCookie.setHttpOnly(true);
		refreshCookie.setSecure(false); // TODO: mudar para true em producao
		refreshCookie.setPath(request.getContextPath() + "/oauth/token");
		refreshCookie.setMaxAge(2592000);
	
		response.addCookie(refreshCookie);
	}

	private void removerRefreshTokenBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
		return methodParameter.getMethod().getName().equals("postAccessToken");
	}

}
