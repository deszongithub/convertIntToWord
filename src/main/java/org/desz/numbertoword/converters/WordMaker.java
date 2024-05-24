package org.desz.numbertoword.converters;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.desz.numbertoword.language.ProvLang.DE;

import java.util.List;

import org.desz.numbertoword.language.NumberWordMapping;
import org.desz.numbertoword.results.Word;
import org.desz.numbertoword.results.Word.WordBuilder;

public class WordMaker {

	private final FuncHundConverter hunConverter;

	public WordMaker() {
		hunConverter = (j, k) -> new HundredthConverter().wordForHundredth(j, k);
	}

	// add or remove 'und' for DE word.
	private String processDeHun(String s, String and, boolean addAnd) {

		var l = asList(s.split(SPACE));

		switch (l.size()) {

		case 2:// add (if > 20) or remove (< 20) 'und'.
			return addAnd ? l.get(1) + and + l.get(0) : l.get(0) + l.get(1).substring(3);

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
	public Word buildWord(List<String> fmtNumber, WordBuilder wordBuilder, NumberWordMapping intWordMapping) {
		var sz = fmtNumber.size();

		// get zero element index and convert
		var firstElem = fmtNumber.getFirst();
		var num = hunConverter.wordForNumber(firstElem, intWordMapping).orElse(EMPTY);

		var zero = intWordMapping.wordForNum(0);
		if (!num.equals(EMPTY) && !zero.toLowerCase().equals(num)) {

			num = intWordMapping.getId().equals(DE.name())
					? processDeHun(num, intWordMapping.getAnd(), Integer.parseInt(firstElem) % 100 > 20)
					: num;

			switch (sz) {

			case 7:
				wordBuilder.quint(num + SPACE + intWordMapping.getQuintn());
				break;
			case 6:
				wordBuilder.quadr(num + SPACE + intWordMapping.getQuadrn());
				break;
			case 5:
				wordBuilder.trill(num + SPACE + intWordMapping.getTrilln());
				break;
			case 4:
				wordBuilder.bill(num + SPACE + intWordMapping.getBilln());
				break;
			case 3:
				wordBuilder.mill(num + SPACE + intWordMapping.getMilln());
				break;
			case 2:
				wordBuilder.thou(num + SPACE + intWordMapping.getThoud());
				break;

			case 1:
				wordBuilder.hund(num);
				break;
			default:
				break;

			}
		}
		
		return sz == 1 ? wordBuilder.build()
				: buildWord(fmtNumber.subList(1, fmtNumber.size()), wordBuilder, intWordMapping);

	}

}
