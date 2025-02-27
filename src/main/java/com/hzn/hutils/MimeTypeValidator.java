package com.hzn.hutils;

import com.hzn.hutils.enums.MimeType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 15.
 */
public class MimeTypeValidator {

    private static final Logger logger = LoggerFactory.getLogger(MimeTypeValidator.class);

    private MimeTypeValidator() {
    }

    public static boolean validate(MultipartFile multipartFile, MimeType... mimeTypes)
            throws IOException {
        return validate(MultipartFileConverter.toFileMap(multipartFile),
                Arrays.stream(mimeTypes).map(MimeType::getType).toList()
                        .toArray(new String[mimeTypes.length]));
    }

    public static boolean validate(MultipartFile multipartFile, String... mimeTypes)
            throws IOException {
        return validate(MultipartFileConverter.toFileMap(multipartFile), mimeTypes);
    }

    public static boolean validate(File file, MimeType... mimeTypes) throws IOException {
        return validate(Map.of(file, false),
                Arrays.stream(mimeTypes).map(MimeType::getType).toList()
                        .toArray(new String[mimeTypes.length]));
    }

    public static boolean validate(File file, String... mimeTypes) throws IOException {
        return validate(Map.of(file, false), mimeTypes);
    }

    public static boolean validate(Map<File, Boolean> fileMap, MimeType... mimeTypes)
            throws IOException {
        return validate(fileMap, Arrays.stream(mimeTypes).map(MimeType::getType).toList()
                .toArray(new String[mimeTypes.length]));
    }

    public static boolean validate(Map<File, Boolean> fileMap, String... mimeTypes)
            throws IOException {
        if (EmptyChecker.isEmpty(fileMap) || EmptyChecker.isEmpty(mimeTypes)) {
            return false;
        }
        int matchedCount = 0;
        for (Entry<File, Boolean> entry : fileMap.entrySet()) {
            File file = entry.getKey();
            boolean isDelete = entry.getValue();
            try {
                String contentType = detectMimeType(file).orElse(detectExtension(file).orElse(""));
                if (Arrays.stream(mimeTypes).anyMatch(
                        mt -> contentType.equals(mt) || contentType.contains(mt)
                                || contentType.startsWith(
                                mt))) {
                    matchedCount++;
                }
            } finally {
                if (isDelete) {
                    file.delete();
                }
            }
        }
        return fileMap.size() == matchedCount;
    }

    private static Optional<String> detectMimeType(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file); FileChannel fc = fis.getChannel()) {
            return Arrays.stream(MimeType.values()).filter(mt -> mt.getMagicNumber() != null)
                    .filter(mt -> {
                        try {
                            byte[] magicNumber = mt.getMagicNumber();
                            byte[] buf = new byte[magicNumber.length];
                            fis.read(buf, 0, magicNumber.length);
                            fc.position(0); // Reset stream position for next read
                            return Arrays.equals(buf, magicNumber);
                        } catch (IOException e) {
                            return false;
                        }
                    }).map(MimeType::getType).findFirst();
        }
    }

    private static Optional<String> detectExtension(File file) {
        return Arrays.stream(MimeType.values()).flatMap(mt -> Arrays.stream(mt.getExtensions()))
                .filter(e -> {
                    String fileName = file.getName();
                    String fileExt = fileName.substring(fileName.lastIndexOf('.'));
                    return e.equalsIgnoreCase(fileExt);
                }).findFirst();
    }
}
