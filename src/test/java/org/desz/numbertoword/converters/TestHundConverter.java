package org.desz.numbertoword.converters;

import static org.desz.numbertoword.converters.HundredthConverter.funBuildWord;
import static org.desz.numbertoword.factory.ProvLangFactory.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.desz.numbertoword.language.NumberWordMapping;
import org.desz.numbertoword.language.ProvLang;
import org.junit.jupiter.api.Test;

public class TestHundConverter {

	NumberWordMapping mapping = getInstance().getMapForProvLang(ProvLang.UK);
	NumberWordMapping mappingDe = getInstance().getMapForProvLang(ProvLang.DE);

	@Test
	void test_123() throws Exception {

		assertEquals("one hundred and twenty three", funBuildWord.apply("123", mapping).orElseThrow());

	}

	@Test
	void test_120() throws Exception {

		assertEquals("one hundred and twenty", funBuildWord.apply("120", mapping).orElseThrow());

	}

	@Test
	void test_31() throws Exception {

		assertEquals("thirty one", funBuildWord.apply("31", mapping).orElseThrow());

	}

	@Test
	void test_200() throws Exception {

		assertNotEquals("zwei hundert", funBuildWord.apply("200", mappingDe).orElseThrow());

	}

	@Test
	void test_0() throws Exception {

		assertEquals("null", funBuildWord.apply("0", mappingDe).orElseThrow());

	}

	@Test
	void test_287() throws Exception {

		assertNotEquals("zweihundertsiebenundachtzig", funBuildWord.apply("287", mappingDe).orElseThrow());

	}

}
