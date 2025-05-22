package com.example.scheduledevelop.filter;

import com.example.scheduledevelop.exception.dto.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

/**
 * 로그인이 되어있는지 확인하는 필터입니다.
 */
public class LoginFilter implements Filter {
    //필터 적용을 제외할 목록입니다.
    private static final String[] WHITE_LIST = {"/users/create", "/users/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if(!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            //세션이 존재하지 않거나 세션키가 존재하지 않으면 오류 메시지를 출력합니다.
            if(session == null || session.getAttribute("sessionKey") == null) {
                ObjectMapper objectMapper = new ObjectMapper();
                ErrorResponseDto errorResponseDto = new ErrorResponseDto("401 UNAUTHORIZED", "로그인이 필요합니다.");

                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponseDto));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    //요청한 URL이 필터 적용 제외 대상인지 확인합니다.
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
