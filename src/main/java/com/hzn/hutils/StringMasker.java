package com.hzn.hutils;

import java.util.stream.IntStream;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 31.
 */
public class StringMasker {

  /**
   * 문자열 마스킹 처리
   *
   * @param content 원본 문자열
   * @param ranges  마스킹 범위 배열
   * @return 마스킹 된 문자열
   */
  public static String mask(String content, int[]... ranges) {
    if (ranges.length == 0 || EmptyChecker.isEmpty(content)) {
      return content;
    }

    StringBuilder maskedContent = new StringBuilder(content);
    for (int[] range : ranges) {
      if (range.length != 2 || range[0] < 0 || range[1] > content.length()
          || range[0] >= range[1]) {
        continue; // 잘못된 범위는 무시한다.
      }
      // 마스킹할 부분을 '*'로 대체
      IntStream.range(range[0], range[1]).forEach(i -> maskedContent.setCharAt(i, '*'));
    }

    return maskedContent.toString();
  }
}
