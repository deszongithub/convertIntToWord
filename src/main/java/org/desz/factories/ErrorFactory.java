package org.desz.factories;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.desz.exceptions.AppConversionException;
import org.desz.language.ProvLang;
import org.desz.language.ProvLangValues.DeError;
import org.desz.language.ProvLangValues.FrError;
import org.desz.language.ProvLangValues.NlError;
import org.desz.language.ProvLangValues.UkError;

public class ErrorFactory {

	private static Map<ProvLang, Map<String, String>> ERROR_CACHE = new ConcurrentHashMap<ProvLang, Map<String, String>>();

	private static String enCacheErrors(ProvLang provLang, String key) {

		switch (provLang) {
			case UK -> ERROR_CACHE.put(ProvLang.UK, of(UkError.values())
					.collect(toMap(UkError::name, UkError::getError)));

			case FR -> ERROR_CACHE.put(ProvLang.FR, of(FrError.values())
					.collect(toMap(FrError::name, FrError::getError)));

			case DE -> ERROR_CACHE.put(ProvLang.DE, of(DeError.values())
					.collect(toMap(DeError::name, DeError::getError)));

			case NL -> ERROR_CACHE.put(ProvLang.NL, of(NlError.values())
					.collect(toMap(NlError::name, NlError::getError)));

			default -> throw new AppConversionException();

		}
		return ERROR_CACHE.get(provLang).get(key);
	}

	/**
	 * 
	 * @param provLang
	 *            the ProvLang.
	 * @param key
	 *            the key for the error.
	 * @return the error mapped to (provLang) key.
	 */
	public static String getErrorForProvLang(ProvLang provLang, String key) {

		return !ERROR_CACHE.containsKey(provLang)
				? enCacheErrors(provLang, key)
				: ERROR_CACHE.get(provLang).get(key);

	}

}
