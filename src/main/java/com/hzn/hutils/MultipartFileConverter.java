package com.hzn.hutils;

import static com.hzn.hutils.filter.MultipartRequestFilter.MULTIPART_THREAD_LOCAL;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 15.
 */
public class MultipartFileConverter {

    private static final Logger logger = LoggerFactory.getLogger(MultipartFileConverter.class);

    private MultipartFileConverter() {
    }

    public static File toFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null) {
            return null;
        }
        Map<MultipartFile, File> multipartFileMap = MULTIPART_THREAD_LOCAL.get();
        if (multipartFileMap == null) {
            multipartFileMap = new HashMap<>();
            MULTIPART_THREAD_LOCAL.set(multipartFileMap);
        }
        File file = multipartFileMap.get(multipartFile);
        if (file == null) {
            try {
                // 임시 파일 참조시
                file = multipartFile.getResource().getFile();
            } catch (IOException e) {
                // 메모리 참조시 임시 파일 생성
                file = File.createTempFile("upload", null);
                multipartFile.transferTo(file);
            }
            multipartFileMap.put(multipartFile, file);
        }
        return file;
    }

    public static Map<File, Boolean> toFileMap(MultipartFile... multipartFile) throws IOException {
        if (EmptyChecker.isEmpty(multipartFile)) {
            return Map.of();
        }
        Map<File, Boolean> fileMap = new HashMap<>();
        for (MultipartFile mf : multipartFile) {
            if (mf.isEmpty()) {
                continue;
            }
            Map<MultipartFile, File> multipartFileMap = MULTIPART_THREAD_LOCAL.get();
            if (multipartFileMap == null) {
                multipartFileMap = new HashMap<>();
                MULTIPART_THREAD_LOCAL.set(multipartFileMap);
            }
            File file = multipartFileMap.get(mf);
            if (file == null) {
                try {
                    // 임시 파일 참조시
                    file = mf.getResource().getFile();
                } catch (IOException e) {
                    // 메모리 참조시 임시 파일 생성
                    file = File.createTempFile("upload", null);
                    mf.transferTo(file);
                }
                multipartFileMap.put(mf, file);
            }
            fileMap.put(file, false);
        }
        return fileMap;
    }
}
