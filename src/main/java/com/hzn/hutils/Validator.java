package com.hzn.hutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.HashSet;
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

	public static void validate (Object obj) throws Exception {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory ()) {
			jakarta.validation.Validator validator = factory.getValidator ();
			Set<ConstraintViolation<Object>> violations = new HashSet<> ();
			recursiveValidate (obj, violations, validator);
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

	private static void recursiveValidate (Object obj, Set<ConstraintViolation<Object>> violations, jakarta.validation.Validator validator)
			throws IllegalAccessException {
		for (Field f : obj.getClass ().getDeclaredFields ()) {
			f.setAccessible (true);
			if (f.isAnnotationPresent (Valid.class)) {
				Object nestedObject = f.get (obj);
				if (nestedObject != null) {
					violations.addAll (validator.validate (nestedObject));
					recursiveValidate (nestedObject, violations, validator);
				}
			}
		}
	}
}
