package org.desz.numbertoword.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.desz.numbertoword.converters.ConversionDelegate;
import org.desz.numbertoword.exceptions.AppConversionException;
import org.desz.numbertoword.language.ProvLang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestConversionDelegateUk {
	private static final String MAX_INT = "two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven";

	private static final String MAX_LONG = "nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion thirty six billion eight hundred and fifty four million seven hundred and seventy five thousand eight hundred and seven";

	ConversionDelegate delegate;

	@BeforeEach
	public void init() {
		delegate = new ConversionDelegate();

	}

	@Test
	void testNull() throws AppConversionException {
		assertThrows(NullPointerException.class, () -> {
			delegate.convertIntToWord(null, null);
		});

	}

	@Test
	void test12123113() throws AppConversionException {

		assertEquals("twelve million one hundred and twenty three thousand one hundred and thirteen",
				delegate.convertIntToWord(12123113L, ProvLang.UK));
	}

	@Test
	void testZero() throws AppConversionException {
		String s = delegate.convertIntToWord(0L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("zero", s);
	}

	@Test
	void test1() throws AppConversionException {
		String s = delegate.convertIntToWord(1L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("one", s);
	}

	@Test
	void testMax() throws AppConversionException {
		String s = delegate.convertIntToWord(2147483647L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals(MAX_INT, s);
	}

	@Test
	void testMaxLong() throws AppConversionException {

		assertEquals(MAX_LONG, delegate.convertIntToWord(Long.MAX_VALUE, ProvLang.UK));
	}

	@Test
	final void test15() throws AppConversionException {
		String s = delegate.convertIntToWord(15L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("fifteen", s);
	}

	@Test
	void test23() throws AppConversionException {
		String s = delegate.convertIntToWord(23L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("twenty three", s);
	}

	@Test
	final void test100() throws AppConversionException {
		String s = delegate.convertIntToWord(100L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("one hundred", s);
	}

	@Test
	void test101() throws AppConversionException {
		String s = delegate.convertIntToWord(101L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("one hundred and one", s);
	}

	@Test
	void test123() throws AppConversionException {
		String s = delegate.convertIntToWord(123L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("one hundred and twenty three", s);
	}

	@Test
	void test123456() throws AppConversionException {
		String s = delegate.convertIntToWord(123456L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("one hundred and twenty three thousand four hundred and fifty six", s);
	}

	@Test
	void test10000() throws AppConversionException {
		String s = delegate.convertIntToWord(10000L, ProvLang.UK);
		assertNotNull("null UNexpected", s);
		assertEquals("ten thousand", s);
	}

	@Test
	void test10099() throws AppConversionException {
		String s = delegate.convertIntToWord(10099L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("ten thousand ninety nine", s);
	}

	@Test
	void test10090() throws AppConversionException {
		String s = delegate.convertIntToWord(10090L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("ten thousand ninety", s);
	}

	@Test
	void test10001() throws AppConversionException {
		String s = delegate.convertIntToWord(10001L, ProvLang.UK);
		assertNotNull("null unexpected", s);
		assertEquals("ten thousand one", s);
	}

	@Test
	void test10000000() throws AppConversionException {
		String s = delegate.convertIntToWord(10000000L, ProvLang.UK);
		assertNotNull("null unexpected", s);

		assertEquals("ten million", s);
	}

	@Test
	void test10000001() throws AppConversionException {
		String s = delegate.convertIntToWord(10000001L, ProvLang.UK);
		assertNotNull("null unexpected", s);

		assertEquals("ten million one", s);
	}

	@Test
	void test100000() throws AppConversionException {
		String s = delegate.convertIntToWord(100000L, ProvLang.UK);
		assertNotNull("null unexpected", s);

		assertEquals("one hundred thousand", s);
	}

	@Test
	void test100000000() throws AppConversionException {
		String s = delegate.convertIntToWord(100000000L, ProvLang.UK);
		assertNotNull("null unexpected", s);

		assertEquals("one hundred million", s);
	}

	@Test
	void test1000000000() throws AppConversionException {

		assertEquals("one billion", delegate.convertIntToWord(1000000000L, ProvLang.UK));
	}

	@Test
	void test1000000001() throws AppConversionException {

		assertEquals("one billion one", delegate.convertIntToWord(1000000001L, ProvLang.UK));
	}

	@Test
	void test1000000100() throws AppConversionException {

		assertEquals("one billion one hundred", delegate.convertIntToWord(1000000100L, ProvLang.UK));
	}

}
