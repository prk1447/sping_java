package com.iot1.sql.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.iot1.sql.user.dto.UserInfo;

@Service
@Aspect
public class AuthAdvice {

	protected static final Logger log = Logger.getLogger(AuthAdvice.class);
    @Pointcut(
            "within(@org.springframework.stereotype.Controller *) && " +
            "@annotation(requestMapping) && " +
            "execution(* *(..))"
        )
    public void checkAuthController(RequestMapping requestMapping) {}

	@Around("checkAuthController(requestMapping)")
	public Object trace(ProceedingJoinPoint joinPoint, RequestMapping requestMapping) throws Throwable {
		HttpSession hs = (HttpSession) RequestContextHolder
                .currentRequestAttributes()
                .resolveReference(RequestAttributes.REFERENCE_SESSION);
		UserInfo ui = (UserInfo)hs.getAttribute("user");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		log.info(request.getHeader("AJAX"));
        log.info(requestMapping.headers());
		if(ui==null && !requestMapping.value()[0].equals("/user/login") && !requestMapping.value()[0].equals("/")){
			if(request.getHeader("AJAX")!=null && request.getHeader("AJAX").equals("true")){
				throw new RuntimeException("auth check failed");
			}else{
				return "user/login";
			}
		}
		Signature signature = joinPoint.getSignature();
		log.info("  " + requestMapping);
        log.info("  " + requestMapping.method()[0]);
		log.info("PerformanceTraceAdvice : " + signature.toShortString() + " Start");
		long start = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			log.info("PerformanceTraceAdvice : " + signature.toShortString() + " RunTime : "
					+ (System.currentTimeMillis() - start) + "ms");
		}
	}
}
