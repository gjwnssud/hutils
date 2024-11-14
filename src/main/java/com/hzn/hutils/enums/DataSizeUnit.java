package com.hzn.hutils.enums;

import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p></p>
 *
 * @author hzn
 * @date 2024. 11. 15.
 */
public enum DataSizeUnit {
	B (BigInteger.valueOf (1L))
	, KB (BigInteger.valueOf (1024L))
	, MB (BigInteger.valueOf (1024L * 1024))
	, GB (BigInteger.valueOf (1024L * 1024 * 1024))
	, TB (BigInteger.valueOf (1024L * 1024 * 1024 * 1024))
	, PB (BigInteger.valueOf (1024L * 1024 * 1024 * 1024 * 1024))
	, EB (BigInteger.valueOf (1024L * 1024 * 1024 * 1024 * 1024 * 1024))
	, ZB (new BigInteger ("1152921504606846976"))
	;

	private final BigInteger byteSize;

	DataSizeUnit (BigInteger byteSize) {
		this.byteSize = byteSize;
	}

	public BigInteger getByteSize () {
		return byteSize;
	}
}
