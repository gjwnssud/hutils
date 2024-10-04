package com.hzn.util;

import com.hzn.awsopensearch.util.http.HttpStatus;
import java.util.Arrays;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class ExceptionLog {
	private static final Logger log = LoggerFactory.getLogger (ExceptionLog.class);

	public static String getMessage (Throwable t) {
		return ObjectUtil.isEmpty (t.getMessage ()) ? HttpStatus.INTERNAL_SERVER_ERROR.getMessage () : t.getMessage ();
	}

	public static void print (Throwable t) {
		print (t, null);
	}

	public static void print (Throwable t, Logger llogger) {
		Logger logger = llogger == null ? log : llogger;
		StackTraceElement[] stackTraceElements = t.getStackTrace ();
		StackTraceElement stackTraceElement = stackTraceElements[0];
		logger.error ("[ClassName = {}]", stackTraceElement.getClassName ());
		logger.error ("[MethodName = {}]", stackTraceElement.getMethodName ());
		logger.error ("[LineNumber = {}]", stackTraceElement.getLineNumber ());
		logger.error ("[Message = {}]", t.getMessage ());
	}

	public static void print (String methodName, Throwable t) {
		print (methodName, t, null);
	}

	public static void print (String methodName, Throwable t, Logger llogger) {
		Logger logger = llogger == null ? log : llogger;
		Optional<StackTraceElement> stackTraceElementOptional = Arrays.stream (t.getStackTrace ()).filter (s -> s.getMethodName ().equals (methodName)).findFirst ();
		StackTraceElement stackTraceElement = stackTraceElementOptional.orElseGet (() -> t.getStackTrace ()[0]);
		logger.error ("[ClassName = {}]", stackTraceElement.getClassName ());
		logger.error ("[MethodName = {}]", stackTraceElement.getMethodName ());
		logger.error ("[LineNumber = {}]", stackTraceElement.getLineNumber ());
		logger.error ("[Message = {}]", t.getMessage ());
	}

}
