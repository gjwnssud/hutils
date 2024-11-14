package com.hzn.hutils;

import com.hzn.hutils.enums.DataSizeUnit;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>파일 사이즈 검증</p>
 *
 * @author hzn
 * @date 2024. 11. 15.
 */
public class FileSizeValidator {
	private static final Logger logger = LoggerFactory.getLogger (FileSizeValidator.class);

	private FileSizeValidator () {
	}

	public static boolean validate (long maxSize, DataSizeUnit unit, MultipartFile... multipartFile) throws IOException {
		return validate (MultipartFileConverter.toFileMap (multipartFile), maxSize, unit);
	}

	public static boolean validate (Map<File, Boolean> fileMap, long maxSize, DataSizeUnit unit) {
		if (EmptyChecker.isEmpty (fileMap) || maxSize <= 0 || unit == null) {
			return false;
		}
		int invalidCount = 0;
		for (Entry<File, Boolean> entry : fileMap.entrySet ()) {
			File file = entry.getKey ();
			boolean isDelete = entry.getValue ();
			try {
				BigInteger fileSizeLimit = unit.getByteSize ().multiply (BigInteger.valueOf (maxSize));
				if (BigInteger.valueOf (file.length ()).compareTo (fileSizeLimit) > 0) {
					invalidCount++;
				}
			} finally {
				if (isDelete) {
					file.delete ();
				}
			}
		}
		return invalidCount == 0;
	}

	public static boolean validate (File file, long maxSize, DataSizeUnit unit) {
		return validate (file, maxSize, unit, false);
	}

	public static boolean validate (File file, long maxSize, DataSizeUnit unit, boolean isDelete) {
		if (EmptyChecker.isEmpty (file) || maxSize <= 0 || unit == null) {
			return false;
		}
		try {
			BigInteger fileSizeLimit = unit.getByteSize ().multiply (BigInteger.valueOf (maxSize));
			return BigInteger.valueOf (file.length ()).compareTo (fileSizeLimit) <= 0;
		} finally {
			if (isDelete) {
				file.delete ();
			}
		}
	}
}
