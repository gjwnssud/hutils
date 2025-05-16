package com.hzn.hutils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 10. 4.
 */
public class EmptyChecker {

  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    } else if (obj instanceof Optional<?> optional) {
      return optional.isEmpty();
    } else if (obj instanceof CharSequence charSequence) {
      return charSequence.isEmpty();
    } else if (obj.getClass().isArray()) {
      return Array.getLength(obj) == 0;
    } else if (obj instanceof Collection<?> collection) {
      return collection.isEmpty();
    } else if (obj instanceof Map<?, ?> map) {
      return map.isEmpty();
    } else if (obj instanceof File file) {
      return !file.exists();
    } else if (obj instanceof Integer i) {
      return i == 0;
    } else if (obj instanceof Double d) {
      return d == 0.0;
    } else {
      return false;
    }
  }
}
