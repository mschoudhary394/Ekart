package com.infy.ekart.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//Add the missing annotation
public class LoggingAspect {

	private static Log logger = LogFactory.getLog(LoggingAspect.class);

	// annotation to handle service layer exception
	public void logExceptionFromService(Exception exception) {
		logger.error(exception.getMessage(), exception);
	}

}
