package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAccessLoggingAspect {

    private final HttpServletRequest request;

    @Before("execution(* org.example.expert.domain.user.controller.UserAdminController..ChangeUserRole(..))")
    public void logAfterChangeUserRole(JoinPoint joinPoint) {
        //사용자 id 가져오기
        String userId = request.getAttribute("userId")!= null
            ? String.valueOf(request.getAttribute("userId"))
            :"unknown";
        //요청 정보 가져오기
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Admin Access Log - User ID: {}, Request Time: {}, Request URL: {}, Method: {}",
                userId, requestTime, requestUrl, joinPoint.getSignature().getName());
    }
}
