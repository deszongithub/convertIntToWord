/**
 * 
 */
package org.desz.numbertoword.converters;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.desz.numbertoword.language.NumberWordMapping;

/**
 * @author des
 *
 */
public class HundredthConverter {

	/**
	 * Matches 'specification' for FuncHundConverter.wordForNumber.
	 * 
	 * @param number
	 * @param intWordMapping
	 * @return empty Optional if input = 0 or word translation.
	 */
	public Optional<String> wordForHundredth(String input, NumberWordMapping map) {
		var number = requireNonNull(input);
		var wordMap = requireNonNull(map);
		if (number.length() > 3) {
			return empty();
		}
		// parse number to int.
		var n = Integer.parseInt(number);

		// check for available mapping.

		var availableMapping = wordMap.wordForNum(n);
		if (!availableMapping.isBlank()) {
			return of(availableMapping.toLowerCase());
		}

		var hun = (wordMap.wordForNum(n / 100) + wordMap.getHund()).toLowerCase();

		if (n % 100 == 0)// ie 100..900
			return of(hun);

		hun = (n < 100) ? EMPTY : hun + SPACE + wordMap.getAnd();

		// build number with non-zero decimal.

		int nmod = n % 100;
		var rem = wordMap.wordForNum(nmod);
		if (!StringUtils.isEmpty(rem)) {

			return of(hun + rem.toLowerCase());// e.g., n = 110, 120,..990.
		}

		int k = nmod;// e.g., nmod = 23
		nmod %= 10;// ..nmod = 3
		k -= nmod; // .. k = 20

		return of(hun + wordMap.wordForNum(k).toLowerCase() + SPACE
				+ wordMap.wordForNum(nmod).toLowerCase());
	}

}
