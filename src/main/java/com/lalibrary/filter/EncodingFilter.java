package com.lalibrary.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/*
 * @WebFilter("/*.do") : 언제 이 필터를 수행 할 것인지에 대한 URL pattern
 */
@WebFilter("*.go")
public class EncodingFilter extends HttpFilter implements Filter {
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청사항이 들어오면 무조건 들어옴
		request.setCharacterEncoding("utf-8");

		// pass the request along the filter chain
		chain.doFilter(request, response); //절대 건들지 말 것.
	}
}
