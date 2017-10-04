package com.iot1.sql.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class AuthException extends AbstractHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			response.reset();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map<String, String> asd = new HashMap<String, String>();
			asd.put("msg", ex.getMessage());
			asd.put("url", "user/login");
			view.setAttributesMap(asd);
			return new ModelAndView(view);
		} catch (Exception e) {
			logger.error("send back error status and message : " + ex.getMessage(), e);
		}
		return null;
	}

}
