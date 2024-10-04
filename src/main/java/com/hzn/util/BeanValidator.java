package com.hzn.util;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class BeanValidator {

	public static Map<String, String> getErrorMap (Errors errors) {
		return errors.getFieldErrors ().stream ().collect (Collectors.toMap (FieldError::getField, v -> v.getDefaultMessage () == null ? "" : v.getDefaultMessage ()));
	}
}
