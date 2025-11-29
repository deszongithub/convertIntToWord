package org.desz.decorators;

import static org.apache.commons.lang3.StringUtils.normalizeSpace;
import static org.desz.factories.ProvLangFactory.getInstance;
import static org.desz.language.ProvLangValues.DePair.ONE;
import static org.desz.language.ProvLangValues.DePair.TWO;
import static org.desz.language.Punct.SPC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.desz.decorators.DeDecorator;
import org.desz.language.WordSupplier;
import org.desz.language.ProvLang;
import org.desz.results.Word;
import org.junit.jupiter.api.Test;

 class TestDeDecorator {

	final WordSupplier deWordMapping = getInstance().wordSupplierForProvLang(ProvLang.DE);

	@Test
	 void test_pluralise_unit_rule_ein() {

		var input = normalizeSpace(ONE.getWord() + SPC.val() + deWordMapping.getQuintn());
		assertEquals(input, new DeDecorator(Word.builder().quint(input).build()).pluraliseUnitRule().getQuint());
	}

	@Test
	 void pluraliseUnit() {
		var input = normalizeSpace(TWO.getWord() + SPC.val() + deWordMapping.getQuintn());
		var res = new DeDecorator(Word.builder().quint(input).build()).pluraliseUnitRule();
		assertEquals("zwei trillionen", res.getQuint().toLowerCase());

	}

	@Test
	 void pluraliseOneRule() {

		assertEquals(ONE.getWord() + "s",
				new DeDecorator(Word.builder().hund("ein").build()).pluraliseHundredthRule(1).getHund(),
				"expected eins");

		var input = normalizeSpace(ONE.getWord() + SPC.val() + deWordMapping.getMilln());
		assertEquals(input, new DeDecorator(Word.builder().mill(input).build()).pluraliseUnitRule().getMill());

	}

	@Test
	 void constructor() {
		assertThrows(NullPointerException.class, () -> {
			new DeDecorator(null);
		});

	}

	@Test
	 void combineThouHundRule() {

		assertEquals("neunhundertneunundneunzigtausendneunhundertneunundneunzig", new DeDecorator(
				Word.builder().thou("neunhundertneunundneunzigtausend").hund("neunhundertneunundneunzig").build())
				.combineThouHundRule().getThou());

	}

}
