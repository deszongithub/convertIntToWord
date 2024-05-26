package org.desz.numbertoword.converters;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.desz.numbertoword.converters.HundredthConverter.funBuildWord;
import static org.desz.numbertoword.language.ProvLang.DE;

import java.util.List;

import org.desz.numbertoword.exceptions.AppConversionException;
import org.desz.numbertoword.language.NumberWordMapping;
import org.desz.numbertoword.language.ProvLangValues.DeUnit;
import org.desz.numbertoword.results.Word;
import org.desz.numbertoword.results.Word.WordBuilder;

public class WordMaker {

	// add or remove 'und' for DE word.
	private String processDeHun(String s, boolean addAnd) {

		var l = asList(s.split(SPACE));

		switch (l.size()) {

		case 2:// add (if > 20) or remove (< 20) 'und'.
			return addAnd ? l.get(1) + DeUnit.AND.getVal() + l.get(0) : l.get(0) + l.get(1).substring(3);

		case 3:
			return l.get(0) + l.get(2) + l.get(1);
		default:
			return s;

		}
	}

	/**
	 * tail recursion using numbers sublist and converting each element adding to
	 * WordBuilder on each recursion.
	 */
	public Word buildWord(List<String> fmtNumber, WordBuilder wordBuilder, NumberWordMapping wordMapping) {
		var sz = fmtNumber.size();

		// get zero element index and convert
		var firstElem = fmtNumber.getFirst();
		var num = funBuildWord.apply(firstElem, wordMapping).orElse(EMPTY);

		var zero = wordMapping.wordForNum(0);
		if (!num.isBlank() && !zero.toLowerCase().equals(num)) {

			num = wordMapping.getId().equals(DE.name()) ? processDeHun(num, Integer.parseInt(firstElem) % 100 > 20)
					: num;

			switch (sz) {

			case 7 -> wordBuilder.quint(num + SPACE + wordMapping.getQuintn());
			case 6 -> wordBuilder.quadr(num + SPACE + wordMapping.getQuadrn());
			case 5 -> wordBuilder.trill(num + SPACE + wordMapping.getTrilln());
			case 4 -> wordBuilder.bill(num + SPACE + wordMapping.getBilln());
			case 3 -> wordBuilder.mill(num + SPACE + wordMapping.getMilln());
			case 2 -> wordBuilder.thou(num + SPACE + wordMapping.getThoud());
			case 1 -> wordBuilder.hund(num);
			default -> throw new AppConversionException();

			}
		}

		return sz == 1 ? wordBuilder.build()
				: buildWord(fmtNumber.subList(1, fmtNumber.size()), wordBuilder, wordMapping);

	}

}
