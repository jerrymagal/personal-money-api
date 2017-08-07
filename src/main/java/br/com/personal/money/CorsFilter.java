package br.com.personal.money;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	private String origemPermitida = "http://localhost:8000"; // TODO: Configurar para diferentes ambientes

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
	
		response.setHeader("Acess-Control-Allow-Origin", origemPermitida);
		response.setHeader("Acess-Control-Allow-Credentials", "true");
		
		if("OPTIONS".equals(request.getMethod()) && origemPermitida.equals(request.getHeader("Origin"))) {
			response.setHeader("Acess-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			response.setHeader("Acess-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Acess-Control-Max-Age", "3600");
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {}

}