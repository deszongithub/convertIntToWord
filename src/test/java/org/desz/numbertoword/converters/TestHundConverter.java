package org.desz.numbertoword.converters;

import static org.desz.numbertoword.converters.HundredthConverter.funBuildWord;
import static org.desz.numbertoword.factory.ProvLangFactory.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.desz.numbertoword.language.NumberWordMapping;
import org.desz.numbertoword.language.ProvLang;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestHundConverter {

	NumberWordMapping mapping = getInstance().getMapForProvLang(ProvLang.UK);
	NumberWordMapping mappingDe = getInstance().getMapForProvLang(ProvLang.DE);

	@Test
	@DisplayName("123")
	void t1() throws Exception {

		assertEquals("one hundred and twenty three", funBuildWord.apply("123", mapping).orElseThrow());

	}

	@Test
	@DisplayName("120")
	void t2() throws Exception {

		assertEquals("one hundred and twenty", funBuildWord.apply("120", mapping).orElseThrow());

	}

	@Test
	@DisplayName("31")
	void t3() throws Exception {

		assertEquals("thirty one", funBuildWord.apply("31", mapping).orElseThrow());

	}

	@Test
	@DisplayName("200")
	void t4() throws Exception {

		assertNotEquals("zwei hundert", funBuildWord.apply("200", mappingDe).orElseThrow());

	}

	@Test
	@DisplayName("0")
	void t5() throws Exception {

		assertEquals("null", funBuildWord.apply("0", mappingDe).orElseThrow());

	}

	@Test
	@DisplayName("287")
	void t6() throws Exception {

		assertNotEquals("zweihundertsiebenundachtzig", funBuildWord.apply("287", mappingDe).orElseThrow());

	}

	@Test
	@DisplayName("Unparseable input")
	void t7() throws Exception {

		assertThrows(NumberFormatException.class, () -> funBuildWord.apply("NFE", mapping));
	}

}
