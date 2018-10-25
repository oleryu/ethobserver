package xyz.oleryu.wallet.eth.observer;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/7/4.
 */
@Configuration
@Order(0)
 public class CrossOrignFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//            httpResponse.addHeader("Access-Control-Allow-Origin", "*");
//            httpResponse.setHeader("Access-Control-Allow-Headers", "*");
//            httpResponse.setHeader("Access-Control-Allow-Methods", "*");

            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Methods", "*");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Authentication,Origin, X-Requested-With, Content-Type, Accept,token,jwt");
            httpResponse.setHeader("Access-Control-Expose-Headers", "*");

            filterChain.doFilter(servletRequest, httpResponse);
        }
        @Override
        public void destroy() {
        }
    }
