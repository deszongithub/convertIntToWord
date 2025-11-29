package org.desz.converters;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.desz.converters.HundredthConverter.buildHundredWord;
import static org.desz.language.ProvLang.DE;

import java.util.List;

import org.desz.exceptions.AppConversionException;
import org.desz.language.WordSupplier;
import org.desz.language.ProvLangValues.DeUnit;
import org.desz.results.Word;
import org.desz.results.Word.WordBuilder;

public final class WordCreator {

	/**
	 * add or remove 'und' for DE word.
	 * 
	 * @param s
	 * @param addAnd
	 * @return
	 */
	private String processDeHun(String s, boolean addAnd) {

		var l = asList(s.split(SPACE));

		switch (l.size()) {

		case 2 -> // add (if > 20) or remove (< 20) 'und'.

			s = addAnd ? l.get(1) + DeUnit.AND.getVal() + l.get(0) : l.get(0) + l.get(1).substring(3);

		case 3 -> s = l.get(0) + l.get(2) + l.get(1);

		}
		return s;
	}

	/**
	 * 
	 */
	/**
	 * Recursively build word.
	 * 
	 * @param fmtNumber    formatted String of Long.
	 * @param wordBuilder  the WordBuilder.
	 * @param wordSupplier the WordSupplier
	 * @return the Word.
	 */
	public Word formatedNumberToWord(List<String> fmtNumber, WordBuilder wordBuilder, WordSupplier wordSupplier) {
		var sz = fmtNumber.size();

		var firstElem = fmtNumber.getFirst();
		// firstElem converted to mapped word
		var hundredth = buildHundredWord.apply(firstElem, wordSupplier).orElse(EMPTY);

		if (!hundredth.equals(wordSupplier.forNumber(0).toLowerCase())) {

			hundredth = wordSupplier.getId().equals(DE.name())
					? processDeHun(hundredth, Integer.parseInt(firstElem) % 100 > 20)
					: hundredth;
			// add units to num
			switch (sz) {

			case 7 -> wordBuilder.quint(hundredth + SPACE + wordSupplier.getQuintn());
			case 6 -> wordBuilder.quadr(hundredth + SPACE + wordSupplier.getQuadrn());
			case 5 -> wordBuilder.trill(hundredth + SPACE + wordSupplier.getTrilln());
			case 4 -> wordBuilder.bill(hundredth + SPACE + wordSupplier.getBilln());
			case 3 -> wordBuilder.mill(hundredth + SPACE + wordSupplier.getMilln());
			case 2 -> wordBuilder.thou(hundredth + SPACE + wordSupplier.getThoud());
			case 1 -> wordBuilder.hund(hundredth);
			default -> throw new AppConversionException();

			}
		}

		// if sz is 1 return Word or continue recursing with subList
		return sz == 1 ? wordBuilder.build()
				: formatedNumberToWord(fmtNumber.subList(1, fmtNumber.size()), wordBuilder, wordSupplier);

	}

}
