package com.hzn.hutils.enums;


/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 13.
 */
public enum MimeType {
  // Text types
  TEXT_PLAIN("text/plain", null, "txt"),
  TEXT_HTML("text/html", null, "html", "htm"),
  TEXT_HTML_UTF8("text/html; charset=UTF-8", null, "html", "htm"),
  TEXT_CSS("text/css", null, "css"),
  TEXT_CSV("text/csv", null, "csv"),
  TEXT_JAVASCRIPT("text/javascript", null, "js"),

  // Image types
  IMAGE("image", null, "img"),
  IMAGE_PNG("image/png", new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A},
      "png"),
  IMAGE_JPEG("image/jpeg", new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF}, "jpg", "jpeg"),
  IMAGE_GIF("image/gif", new byte[]{0x47, 0x49, 0x46, 0x38}, "gif"),
  IMAGE_SVG("image/svg+xml", null, "svg"),
  IMAGE_BMP("image/bmp", new byte[]{0x42, 0x4D}, "bmp"),
  IMAGE_WEBP("image/webp", new byte[]{0x52, 0x49, 0x46, 0x46, 0x57, 0x45, 0x42, 0x50},
      "webp"), // "RIFF" + "WEBP"

  // Application types
  APPLICATION_JSON("application/json", null, "json"),
  APPLICATION_XML("application/xml", null, "xml"),
  APPLICATION_PDF("application/pdf", new byte[]{0x25, 0x50, 0x44, 0x46}, "pdf"),
  APPLICATION_ZIP("application/zip", new byte[]{0x50, 0x4B, 0x03, 0x04}, "zip"),
  APPLICATION_JAVASCRIPT("application/javascript", null, "js"),
  APPLICATION_OCTET_STREAM("application/octet-stream", null, "bin"),
  APPLICATION_SQL("application/sql", null, "sql"),
  APPLICATION_PHP("application/x-httpd-php", null, "php"),
  APPLICATION_XHTML("application/xhtml+xml", null, "xhtml"),
  APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded", null, "form"),
  APPLICATION_MSWORD("application/msword",
      new byte[]{(byte) 0xD0, (byte) 0xCF, 0x11, (byte) 0xE0, (byte) 0xA1, (byte) 0xB1, 0x1A,
          (byte) 0xE1}, "doc"),
  APPLICATION_EXCEL("application/vnd.ms-excel",
      new byte[]{(byte) 0xD0, (byte) 0xCF, 0x11, (byte) 0xE0, (byte) 0xA1, (byte) 0xB1, 0x1A,
          (byte) 0xE1}, "xls"),
  APPLICATION_POWERPOINT("application/vnd.ms-powerpoint",
      new byte[]{(byte) 0xD0, (byte) 0xCF, 0x11, (byte) 0xE0, (byte) 0xA1, (byte) 0xB1, 0x1A,
          (byte) 0xE1}, "ppt"),
  APPLICATION_OPENXML_WORD(
      "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
      new byte[]{0x50, 0x4B, 0x03, 0x04}, "docx"),
  APPLICATION_OPENXML_EXCEL("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
      new byte[]{0x50, 0x4B, 0x03, 0x04}, "xlsx"),
  APPLICATION_OPENXML_PRESENTATION(
      "application/vnd.openxmlformats-officedocument.presentationml.presentation",
      new byte[]{0x50, 0x4B, 0x03, 0x04}, "pptx"),

  // Audio types
  AUDIO_MPEG("audio/mpeg", new byte[]{(byte) 0xFF, (byte) 0xFB}, "mp3"),
  AUDIO_OGG("audio/ogg", new byte[]{0x4F, 0x67, 0x67, 0x53}, "ogg"),
  AUDIO_WEBM("audio/webm", new byte[]{0x1A, 0x45, (byte) 0xDF, (byte) 0xA3}, "webm"),
  AUDIO_WAV("audio/wav", new byte[]{0x52, 0x49, 0x46, 0x46, 0x57, 0x41, 0x56, 0x45}, "wav"),

  // Video types
  VIDEO_MP4("video/mp4", new byte[]{0x00, 0x00, 0x00, 0x18, 0x66, 0x74, 0x79, 0x70}, "mp4"),
  VIDEO_MPEG("video/mpeg", new byte[]{0x00, 0x00, 0x01, (byte) 0xBA}, "mpeg"),
  VIDEO_OGG("video/ogg", new byte[]{0x4F, 0x67, 0x67, 0x53}, "ogv"),
  VIDEO_WEBM("video/webm", new byte[]{0x1A, 0x45, (byte) 0xDF, (byte) 0xA3}, "webm"),
  VIDEO_AVI("video/avi", new byte[]{0x52, 0x49, 0x46, 0x46, 0x41, 0x56, 0x49, 0x20},
      "avi"), // "RIFF" + "AVI "

  // Font types
  FONT_WOFF("font/woff", new byte[]{0x77, 0x4F, 0x46, 0x46}, "woff"),
  FONT_WOFF2("font/woff2", new byte[]{0x77, 0x4F, 0x46, 0x32}, "woff2"),
  FONT_TTF("font/ttf", new byte[]{0x00, 0x01, 0x00, 0x00}, "ttf"),
  FONT_OTF("font/otf", new byte[]{0x4F, 0x54, 0x54, 0x4F}, "otf");

  private final String type;
  private final byte[] magicNumber;
  private final String[] extensions;

  MimeType(String type, byte[] magicNumber, String... extensions) {
    this.type = type;
    this.magicNumber = magicNumber;
    this.extensions = extensions;
  }

  public String getType() {
    return type;
  }

  public byte[] getMagicNumber() {
    return magicNumber;
  }

  public String[] getExtensions() {
    return extensions;
  }
}
