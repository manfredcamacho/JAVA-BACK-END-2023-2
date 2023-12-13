package ar.com.codoacodo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        List<String> allowedOrigins = List.of("http://127.0.0.1:5500", "http://localhost:5500");
        String allowHeaders = "Content-Type";
        String origin = ((HttpServletRequest) request).getHeader("origin");

        HttpServletResponse res = ((HttpServletResponse) response);
        if(origin != null && allowedOrigins.contains(origin)){
            res.addHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }

        filterChain.doFilter(request, response);
    }
}
