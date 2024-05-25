package org.desz.numbertoword.results;

import static org.desz.numbertoword.language.ProvLang.DE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.desz.numbertoword.converters.ConversionDelegate;
import org.desz.numbertoword.exceptions.AppConversionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestConversionDelegateDe {

	static final Long MAX_LONG = 9223372036854775807L;
	static final Long MAX_INT = 2147483647L;
	ConversionDelegate delegate;

	@BeforeEach
	public void init() {
		delegate = new ConversionDelegate();
	}

	@Test
	void test9223372036854775807() throws AppConversionException {

		assertEquals(
				"neun Trillionen zweihundertdreiundzwanzig Billiarden dreihundertzweiundsiebzig Billionen sechsunddreißig Milliarden achthundertvierundfünfzig Millionen siebenhundertfünfundsiebzigtausendachthundertsieben",
				delegate.convertIntToWord(MAX_LONG, DE));
	}

	@Test
	void testMaxInt() throws AppConversionException {

		assertEquals(
				"zwei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
				delegate.convertIntToWord(MAX_INT, DE));
	}

	@Test
	void test3147483647() throws AppConversionException {

		assertEquals(
				"drei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
				delegate.convertIntToWord(3147483647L, DE));
	}

	@Test
	void test23873636() throws AppConversionException {

		assertEquals("dreiundzwanzig Millionen achthundertdreiundsiebzigtausendsechshundertsechsunddreißig",
				delegate.convertIntToWord(23873636L, DE));
	}

	@Test
	void test1000() throws AppConversionException {

		assertEquals("eintausend", delegate.convertIntToWord(1000L, DE));
	}

	@Test
	void test1020() throws AppConversionException {

		assertEquals("eintausendzwanzig", delegate.convertIntToWord(1020L, DE));
	}

	@Test
	void test10000() throws AppConversionException {

		assertEquals("zehntausend", delegate.convertIntToWord(10000L, DE));
	}

	@Test
	void test100001() throws AppConversionException {

		assertEquals("einhunderttausendeins", delegate.convertIntToWord(100001L, DE));
	}

	@Test
	void test1000001() throws AppConversionException {

		assertEquals("ein Million eins", delegate.convertIntToWord(1000001L, DE));
	}

	@Test
	void test100000007() throws AppConversionException {

		assertEquals("einhundert Millionen sieben", delegate.convertIntToWord(100000007L, DE));
	}

	@Test
	void test100000031() throws AppConversionException {

		assertEquals("einhundert Millionen einunddreißig", delegate.convertIntToWord(100000031L, DE));
	}

	@Test
	void test2387() throws AppConversionException {

		assertEquals("zweitausenddreihundertsiebenundachtzig", delegate.convertIntToWord(2387L, DE));
	}

	@Test
	void test238() throws AppConversionException {

		assertEquals("zweihundertachtunddreißig", delegate.convertIntToWord(238L, DE));
	}

	@Test
	void test19() throws AppConversionException {

		assertEquals("neunzehn", delegate.convertIntToWord(19L, DE));
	}

	@Test
	void test0() throws AppConversionException {

		assertEquals("null", delegate.convertIntToWord(0L, DE));
	}

}
