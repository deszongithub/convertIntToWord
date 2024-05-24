/**
 * 
 */
package org.desz.numbertoword.converters;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Optional;

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

		// parse number to int.
		var n = Integer.parseInt(number);

		// check for available mapping.

		var availableMapping = wordMap.wordForNum(n);
		if (!availableMapping.isBlank()) {
			return of(availableMapping.toLowerCase());
		}

		var hun = (wordMap.wordForNum(Math.divideExact(n, 100)) + wordMap.getHund()).toLowerCase();

		int nmod = Math.floorMod(n, 100);
		if (nmod == 0) {
			// ie 100..900
			return of(hun);
		}

		hun = (n < 100) ? EMPTY : hun + SPACE + wordMap.getAnd();

		// build number with non-zero decimal.

		var sb = new StringBuilder().append(hun);

		var rem = wordMap.wordForNum(nmod);
		if (!rem.isBlank()) {

			return of(sb.append(rem.toLowerCase()).toString());
		}

		int k = nmod;// e.g., nmod = 23
		nmod %= 10;// ..nmod = 3
		k -= nmod; // .. k = 20

		sb.append(wordMap.wordForNum(k).toLowerCase()).append(SPACE).append(wordMap.wordForNum(nmod).toLowerCase());

		return of(sb.toString());
	}

}
