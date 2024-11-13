package com.hzn.hutils.enums;


/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 13.
 */
public enum MimeType {
	// Text types
	TEXT_PLAIN ("text/plain"),
	TEXT_HTML ("text/html"),
	TEXT_HTML_UTF8 ("text/html; charset=UTF-8"),
	TEXT_CSS ("text/css"),
	TEXT_CSV ("text/csv"),
	TEXT_JAVASCRIPT ("text/javascript"),

	// Image types
	IMAGE ("image"),
	IMAGE_PNG ("image/png"),
	IMAGE_JPEG ("image/jpeg"),
	IMAGE_GIF ("image/gif"),
	IMAGE_SVG ("image/svg+xml"),
	IMAGE_BMP ("image/bmp"),
	IMAGE_WEBP ("image/webp"),

	// Application types
	APPLICATION_JSON ("application/json"),
	APPLICATION_XML ("application/xml"),
	APPLICATION_PDF ("application/pdf"),
	APPLICATION_ZIP ("application/zip"),
	APPLICATION_JAVASCRIPT ("application/javascript"),
	APPLICATION_OCTET_STREAM ("application/octet-stream"),
	APPLICATION_SQL ("application/sql"),
	APPLICATION_PHP ("application/x-httpd-php"),
	APPLICATION_XHTML ("application/xhtml+xml"),
	APPLICATION_X_WWW_FORM_URLENCODED ("application/x-www-form-urlencoded"),
	APPLICATION_MSWORD ("application/msword"),
	APPLICATION_EXCEL ("application/vnd.ms-excel"),
	APPLICATION_POWERPOINT ("application/vnd.ms-powerpoint"),
	APPLICATION_OPENXML_WORD ("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	APPLICATION_OPENXML_EXCEL ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	APPLICATION_OPENXML_PRESENTATION ("application/vnd.openxmlformats-officedocument.presentationml.presentation"),

	// Audio types
	AUDIO_MPEG ("audio/mpeg"),
	AUDIO_OGG ("audio/ogg"),
	AUDIO_WAV ("audio/wav"),
	AUDIO_WEBM ("audio/webm"),

	// Video types
	VIDEO_MP4 ("video/mp4"),
	VIDEO_MPEG ("video/mpeg"),
	VIDEO_OGG ("video/ogg"),
	VIDEO_WEBM ("video/webm"),

	// Font types
	FONT_WOFF ("font/woff"),
	FONT_WOFF2 ("font/woff2"),
	FONT_TTF ("font/ttf"),
	FONT_OTF ("font/otf");

	private final String type;

	MimeType (String type) {
		this.type = type;
	}

	public String getType () {
		return this.type;
	}
}
