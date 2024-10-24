package com.hzn.hutils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>jakarta validation-api 유틸</p>
 *
 * @author hzn
 * @date 2024. 10. 24.
 */
public class Validator {

	public static Optional<Map<String, String>> validate (Class<?> clazz) {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory ()) {
			jakarta.validation.Validator validator = factory.getValidator ();
			Set<ConstraintViolation<Class<?>>> violations = validator.validate (clazz);
			if (!violations.isEmpty ()) {
				return Optional.of (violations.stream ().collect (Collectors.toMap (v -> v.getPropertyPath ().toString (), ConstraintViolation::getMessage)));
			}
		}
		return Optional.empty ();
	}
}
