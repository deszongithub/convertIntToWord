package org.desz.converters;

import static java.util.Arrays.asList;
import static java.util.Locale.UK;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;
import static org.desz.decorators.DeDecorator.deFormat;
import static org.desz.factories.ProvLangFactory.getInstance;

import java.text.NumberFormat;

import org.desz.exceptions.AppConversionException;
import org.desz.language.ProvLang;
import org.desz.results.Word;

import lombok.extern.slf4j.Slf4j;

/**
 * @author des Converts integer to corresponding word format in ProvLang
 * 
 */
@Slf4j
class ConversionDelegate {
	private static final NumberFormat nf = NumberFormat.getIntegerInstance(UK);

	private static final String EMPTY_PROVLANG = "Empty ProvLang not permitted.";

	public String getWordForLanguage(String n, String pl) throws AppConversionException {

		return this.toWord(Long.valueOf(n), ProvLang.valueOf(pl));
	}

	/**
	 * 
	 * @param longToMap the long.
	 * @param pl        the ProvLang.
	 * @return the word for n.
	 * @throws AppConversionException
	 */

	String toWord(Long longToMap, ProvLang pl) throws AppConversionException {

		var provLang = requireNonNull(pl);
		if (provLang.equals(ProvLang.EMPTY)) {
			log.error(EMPTY_PROVLANG);
			throw new AppConversionException(EMPTY_PROVLANG);
		}

		var numbers = asList(nf.format(longToMap).split(","));

		var wordMapping = getInstance().wordSupplierForProvLang(provLang);

		var s = wordMapping.forNumber(longToMap.intValue());
		// return s if n < 1000 and wordMapping.
		if (numbers.size() == 1 && !isBlank(s)) {

			return s.toLowerCase();
		}

		var word = new WordCreator().formatedNumberToWord(numbers, Word.builder(), wordMapping);

		// apply rules to 'decorate' DE word.

		return !provLang.equals(ProvLang.DE) ? normalizeSpace(stringifyWord(word))
				: normalizeSpace(stringifyWord(deFormat.apply(word, Integer.valueOf(numbers.getLast()))));

	}

	private String stringifyWord(Word word) {
		var sb = new StringBuilder();
		sb = !isNull(word.getQuint()) ? sb.append(word.getQuint() + SPACE) : sb;
		sb = !isNull(word.getQuadr()) ? sb.append(word.getQuadr() + SPACE) : sb;
		sb = !isNull(word.getTrill()) ? sb.append(word.getTrill() + SPACE) : sb;
		sb = !isNull(word.getBill()) ? sb.append(word.getBill() + SPACE) : sb;
		sb = !isNull(word.getMill()) ? sb.append(word.getMill() + SPACE) : sb;
		sb = !isNull(word.getThou()) ? sb.append(word.getThou() + SPACE) : sb;
		sb = !isNull(word.getHund()) ? sb.append(word.getHund()) : sb;

		return sb.toString();
	}

}
