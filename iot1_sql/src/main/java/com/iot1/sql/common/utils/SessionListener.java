package com.iot1.sql.common.utils;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {

	protected static final Logger log = Logger.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent event) {
		log.info("Session ID => "+event.getSession().getId() + ", start time =>" + (new Date()).toString());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		log.info("Session ID => "+event.getSession().getId() + ", end time =>" + (new Date()).toString());
	}

}
