/**
 * 
 */
package org.desz.numbertoword.results;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;
import static org.desz.numbertoword.language.ProvLangValues.DePair.ONE;

import java.util.function.BiFunction;

/**
 * @author des
 *
 */
public class DeDecorator {

	private final Word word;

	public DeDecorator(Word word) {
		requireNonNull(word);
		this.word = word;
	}

	private String pluralise(String unitWord) {

		if (ONE.getWord().equals(asList(unitWord.split(SPACE)).getFirst())) {
			return unitWord;
		}
		return unitWord.endsWith("e") ? unitWord.concat("n") : unitWord.concat("en");

	}

	public static final BiFunction<Word, Integer, Word> deFormat = (wrd, i) -> {
		var deWord = new DeDecorator(wrd).pluraliseUnitRule();
		deWord = new DeDecorator(deWord).pluraliseHundredthRule(i);
		return nonNull(deWord.getThou()) ? new DeDecorator(deWord).combineThouHundRule() : deWord;

	};

	Word pluraliseUnitRule() {

		var builder = word.toBuilder();
		builder = nonNull(word.getQuint()) ?

				builder.quint(normalizeSpace(pluralise(word.getQuint()))) : builder;

		builder = nonNull(word.getQuadr()) ? builder.quadr(normalizeSpace(pluralise(word.getQuadr()))) : builder;

		builder = nonNull(word.getTrill()) ? builder.trill(normalizeSpace(pluralise(word.getTrill()))) : builder;

		builder = nonNull(word.getBill()) ?

				builder.bill(normalizeSpace(pluralise(word.getBill()))) : builder;

		builder = nonNull(word.getMill()) ?

				builder.mill(normalizeSpace(pluralise(word.getMill()))) : builder;

		builder = nonNull(word.getThou()) ? builder.thou(word.getThou()) : builder;

		builder = nonNull(word.getHund()) ? builder.hund(word.getHund()) : builder;

		return builder.build();

	}

	Word pluraliseHundredthRule(int val) {
		var builder = word.toBuilder();

		return Math.floorMod(val, 100) == 1 ? builder.hund(word.getHund() + "s").build()
				: builder.hund(word.getHund()).build();

	}

	Word combineThouHundRule() {
		var builder = word.toBuilder();

		var sb = new StringBuilder();
		sb = nonNull(word.getThou()) ? sb.append(word.getThou().replaceAll(SPACE, EMPTY)) : sb;

		sb = nonNull(word.getHund()) ? sb.append(word.getHund().replaceAll(SPACE, EMPTY)) : sb;

		return builder.thou(sb.toString().toLowerCase()).hund(EMPTY).build();

	}

}