package com.hzn.hutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>jakarta validation-api 유틸</p>
 *
 * @author hzn
 * @date 2024. 10. 24.
 */
public final class Validator {
	private static final Logger logger = LoggerFactory.getLogger (Validator.class);

	private Validator () {
	}

	public static <T> void validate (T t) throws Exception {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory ()) {
			jakarta.validation.Validator validator = factory.getValidator ();
			Set<ConstraintViolation<T>> violations = validator.validate (t);
			if (!violations.isEmpty ()) {
				Map<String, String> violationMap = violations.stream ()
				                                             .collect (Collectors.toMap (v -> v.getPropertyPath ().toString (), ConstraintViolation::getMessage,
				                                                                         (existingValue, newValue) -> newValue));
				String violationsString = new ObjectMapper ().writeValueAsString (violationMap);
				Exception e = new Exception (violationsString);
				ExceptionLog.print ("validate", e, logger);
				throw e;
			}
		}
	}
}
