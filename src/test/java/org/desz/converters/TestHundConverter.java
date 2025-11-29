package org.desz.converters;

import static org.desz.converters.HundredthConverter.buildHundredWord;
import static org.desz.factories.ProvLangFactory.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.desz.language.WordSupplier;
import org.desz.language.ProvLang;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestHundConverter {

	WordSupplier mapping = getInstance().wordSupplierForProvLang(ProvLang.UK);
	WordSupplier mappingDe = getInstance().wordSupplierForProvLang(ProvLang.DE);

	@Test
	@DisplayName("123")
	void t1() throws Exception {

		assertEquals("one hundred and twenty three", buildHundredWord.apply("123", mapping).orElseThrow());

	}

	@Test
	@DisplayName("120")
	void t2() throws Exception {

		assertEquals("one hundred and twenty", buildHundredWord.apply("120", mapping).orElseThrow());

	}

	@Test
	@DisplayName("31")
	void t3() throws Exception {

		assertEquals("thirty one", buildHundredWord.apply("31", mapping).orElseThrow());

	}

	@Test
	@DisplayName("200")
	void t4() throws Exception {

		assertNotEquals("zwei hundert", buildHundredWord.apply("200", mappingDe).orElseThrow());

	}

	@Test
	@DisplayName("0")
	void t5() throws Exception {

		assertEquals("null", buildHundredWord.apply("0", mappingDe).orElseThrow());

	}

	@Test
	@DisplayName("287")
	void t6() throws Exception {

		assertNotEquals("zweihundertsiebenundachtzig", buildHundredWord.apply("287", mappingDe).orElseThrow());

	}

	@Test
	@DisplayName("Unparseable input")
	void t7() throws Exception {

		assertThrows(NumberFormatException.class, () -> buildHundredWord.apply("NFE", mapping));
	}

}
