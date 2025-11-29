/**
 * 
 */
package org.desz.converters;

import static java.util.Objects.isNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Optional;
import java.util.function.BiFunction;

import org.desz.language.WordSupplier;

/**
 * @author des
 *
 */
public final class HundredthConverter {

	private HundredthConverter() {

	}

	static BiFunction<String, WordSupplier, Optional<String>> buildHundredWord = (input,
			wordSupplier) -> isNull(input) || input.isBlank() ? empty() : of(wordForHundredth(input, wordSupplier));

	/**
	 * @param input the number
	 * @param WordSupplier
	 * @return word for hundred.
	 */
	private static String wordForHundredth(String input, WordSupplier wordSupplier) {

		// parse number to int.
		var inputNumber = Integer.parseInt(input);

		// check for inputNumber mapping.

		var mappedNumber = wordSupplier.forNumber(inputNumber);
		if (!mappedNumber.isBlank()) {
			return mappedNumber.toLowerCase();
		}

		var hun = (wordSupplier.forNumber(Math.divideExact(inputNumber, 100)) + wordSupplier.getHund()).toLowerCase();

		var nmod = Math.floorMod(inputNumber, 100);
		if (nmod == 0) {
			// ie 100..900
			return hun;
		}

		// build number with non-zero decimal.

		var sb = new StringBuilder().append((inputNumber < 100) ? EMPTY : hun + SPACE + wordSupplier.getAnd());

		var rem = wordSupplier.forNumber(nmod);
		if (!rem.isBlank()) {

			return sb.append(rem.toLowerCase()).toString();
		}

		var k = nmod;// e.g., nmod = 23
		nmod = Math.floorMod(nmod, 10);// nmod = 3
		k -= nmod; // k = 20

		sb.append(wordSupplier.forNumber(k).toLowerCase()).append(SPACE).append(wordSupplier.forNumber(nmod).toLowerCase());

		return sb.toString();
	}

}
