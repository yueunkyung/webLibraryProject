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
 * Servlet Filter implementation class TimeCheckFilter
 */
//@WebFilter("*.do")
public class TimeCheckFilter extends HttpFilter implements Filter {
       
    public TimeCheckFilter() {
        super();
        System.out.println("TimeCheckFilter 생성자");
    }

	public void destroy() {
        System.out.println("TimeCheckFilter 소멸자");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		long startTime = System.nanoTime();
		// pass the request along the filter chain
		chain.doFilter(request, response);
		long endTime = System.nanoTime();

		System.out.println("[걸린 시간]"+ (endTime - startTime));
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}