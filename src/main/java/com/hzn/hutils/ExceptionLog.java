package com.hzn.hutils;

import com.hzn.hutils.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class ExceptionLog {

  private static final Logger log = LoggerFactory.getLogger(ExceptionLog.class);

  public static String getMessage(Throwable t) {
    return EmptyChecker.isEmpty(t.getMessage()) ? HttpStatus.INTERNAL_SERVER_ERROR.getMessage()
        : t.getMessage();
  }

  public static void print(Throwable t) {
    print(t, null);
  }

  public static void print(Throwable t, Logger customLogger) {
    Logger logger = customLogger != null ? customLogger : log;

    // 현재 스레드의 스택트레이스에서 호출자 추출
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

    StackTraceElement caller = null;
    for (StackTraceElement element : stackTrace) {
      // ExceptionLog 클래스와 java.lang.Thread 클래스는 제외
      if (!element.getClassName().equals(ExceptionLog.class.getName()) && !element.getClassName()
          .startsWith("java.lang.Thread")) {
        caller = element;
        break;
      }
    }

    // fallback: 예외 내부의 첫 번째 스택 프레임
    if (caller == null && t.getStackTrace().length > 0) {
      caller = t.getStackTrace()[0];
    }

    if (caller != null) {
      logger.error("[ClassName = {}]", caller.getClassName());
      logger.error("[MethodName = {}]", caller.getMethodName());
      logger.error("[LineNumber = {}]", caller.getLineNumber());
    }

    logger.error("[Message = {}]", t.getMessage());
  }
}
