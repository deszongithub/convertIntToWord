package org.desz.converters;

import static org.desz.language.ProvLang.DE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.desz.converters.ConversionDelegate;
import org.desz.exceptions.AppConversionException;
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
				delegate.toWord(MAX_LONG, DE));
	}

	@Test
	void testMaxInt() throws AppConversionException {

		assertEquals(
				"zwei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
				delegate.toWord(MAX_INT, DE));
	}

	@Test
	void test3147483647() throws AppConversionException {

		assertEquals(
				"drei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
				delegate.toWord(3147483647L, DE));
	}

	@Test
	void test23873636() throws AppConversionException {

		assertEquals("dreiundzwanzig Millionen achthundertdreiundsiebzigtausendsechshundertsechsunddreißig",
				delegate.toWord(23873636L, DE));
	}

	@Test
	void test1000() throws AppConversionException {

		assertEquals("eintausend", delegate.toWord(1000L, DE));
	}

	@Test
	void test1020() throws AppConversionException {

		assertEquals("eintausendzwanzig", delegate.toWord(1020L, DE));
	}

	@Test
	void test10000() throws AppConversionException {

		assertEquals("zehntausend", delegate.toWord(10000L, DE));
	}

	@Test
	void test100001() throws AppConversionException {

		assertEquals("einhunderttausendeins", delegate.toWord(100001L, DE));
	}

	@Test
	void test1000001() throws AppConversionException {

		assertEquals("ein Million eins", delegate.toWord(1000001L, DE));
	}

	@Test
	void test100000007() throws AppConversionException {

		assertEquals("einhundert Millionen sieben", delegate.toWord(100000007L, DE));
	}

	@Test
	void test100000031() throws AppConversionException {

		assertEquals("einhundert Millionen einunddreißig", delegate.toWord(100000031L, DE));
	}

	@Test
	void test2387() throws AppConversionException {

		assertEquals("zweitausenddreihundertsiebenundachtzig", delegate.toWord(2387L, DE));
	}

	@Test
	void test238() throws AppConversionException {

		assertEquals("zweihundertachtunddreißig", delegate.toWord(238L, DE));
	}

	@Test
	void test19() throws AppConversionException {

		assertEquals("neunzehn", delegate.toWord(19L, DE));
	}

	@Test
	void test0() throws AppConversionException {

		assertEquals("null", delegate.toWord(0L, DE));
	}

}
