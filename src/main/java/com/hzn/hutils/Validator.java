package com.hzn.hutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzn.hutils.enums.MimeType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>검증 유틸</p>
 *
 * @author hzn
 * @date 2024. 10. 24.
 */
public final class Validator {
	private static final Logger logger = LoggerFactory.getLogger (Validator.class);

	private Validator () {
	}

	/**
	 * 제약 조건 검증
	 *
	 * @param t 검증이 필요한 객체
	 * @throws Exception if an any error occurs
	 */
	public static <T> void validateConstraints (T t) throws Exception {
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

	public static boolean validateMimetype (File file, MimeType... mimeTypes) throws IOException {
		return validateMimetype (List.of (file), Arrays.stream (mimeTypes).map (MimeType::getType).toList ().toArray (new String[mimeTypes.length]));
	}

	public static boolean validateMimetype (File file, String... mimeTypes) throws IOException {
		return validateMimetype (List.of (file), mimeTypes);
	}

	public static boolean validateMimetype (List<File> fileList, MimeType... mimeTypes) throws IOException {
		return validateMimetype (fileList, Arrays.stream (mimeTypes).map (MimeType::getType).toList ().toArray (new String[mimeTypes.length]));
	}

	/**
	 * 파일 mime type 검증
	 *
	 * @param fileList 검증할 파일 목록
	 * @param mimeTypes {@link MimeType}
	 * @return true | false
	 * @throws IOException if an I/ O error occurs
	 */
	public static boolean validateMimetype (List<File> fileList, String... mimeTypes) throws IOException {
		if (EmptyChecker.isEmpty (fileList) || EmptyChecker.isEmpty (mimeTypes)) {
			return false;
		}
		int matchedCount = 0;
		for (File file : fileList) {
			if (file == null) {
				continue;
			}
			String contentType = Files.probeContentType (Path.of (file.getAbsolutePath ()));
			if (Arrays.stream (mimeTypes).anyMatch (mt -> contentType.equals (mt) || contentType.contains (mt) || contentType.startsWith (mt))) {
				matchedCount++;
			}
		}
		return fileList.size () == matchedCount;
	}
}
