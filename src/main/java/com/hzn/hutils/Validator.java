package com.hzn.hutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Map;
import java.util.Optional;
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

	public static <T> Optional<Map<String, String>> validate (T t) {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory ()) {
			jakarta.validation.Validator validator = factory.getValidator ();
			Set<ConstraintViolation<T>> violations = validator.validate (t);
			if (!violations.isEmpty ()) {
				Map<String, String> violationMap = violations.stream ()
				                                             .collect (Collectors.toMap (v -> v.getPropertyPath ().toString (), ConstraintViolation::getMessage));
				try {
					ExceptionLog.print ("validate", new RuntimeException (new ObjectMapper ().writeValueAsString (violationMap)), logger);
				} catch (JsonProcessingException e) {
					ExceptionLog.print ("validate", e, logger);
					throw new RuntimeException (e);
				}
				return Optional.of (violationMap);
			}
		}
		return Optional.empty ();
	}
}
