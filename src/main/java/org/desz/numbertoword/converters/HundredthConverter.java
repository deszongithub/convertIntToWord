/**
 * 
 */
package org.desz.numbertoword.converters;

import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import org.desz.numbertoword.language.NumberWordMapping;

/**
 * @author des
 *
 */
public final class HundredthConverter {

	private HundredthConverter() {

	}

	static BiFunction<String, NumberWordMapping, Optional<String>> funBuildWord = (input, map) -> {
		return Objects.isNull(input) || input.isBlank() ? Optional.empty() : wordForHundredth(input, map);
	};

	/**
	 * Matches 'specification' for FuncHundConverter.wordForNumber.
	 * 
	 * @param number
	 * @param intWordMapping
	 * @return empty Optional if input = 0 or word translation.
	 */
	private static Optional<String> wordForHundredth(String input, NumberWordMapping map) {

		// parse number to int.
		var n = Integer.parseInt(input);

		// check for available mapping.

		var availableMapping = map.wordForNum(n);
		if (!availableMapping.isBlank()) {
			return of(availableMapping.toLowerCase());
		}

		var hun = (map.wordForNum(Math.divideExact(n, 100)) + map.getHund()).toLowerCase();

		var nmod = Math.floorMod(n, 100);
		if (nmod == 0) {
			// ie 100..900
			return of(hun);
		}

		// build number with non-zero decimal.

		var sb = new StringBuilder().append((n < 100) ? EMPTY : hun + SPACE + map.getAnd());

		var rem = map.wordForNum(nmod);
		if (!rem.isBlank()) {

			return of(sb.append(rem.toLowerCase()).toString());
		}

		var k = nmod;// e.g., nmod = 23
		nmod %= 10;// ..nmod = 3
		k -= nmod; // .. k = 20

		sb.append(map.wordForNum(k).toLowerCase()).append(SPACE).append(map.wordForNum(nmod).toLowerCase());

		return of(sb.toString());
	}

}
