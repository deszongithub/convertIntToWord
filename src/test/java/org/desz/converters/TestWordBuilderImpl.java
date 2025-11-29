package org.desz.converters;

import static java.util.Arrays.asList;
import static org.desz.factories.ProvLangFactory.getInstance;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.desz.converters.FuncWordMaker;
import org.desz.converters.WordCreator;
import org.desz.language.WordSupplier;
import org.desz.language.ProvLang;
import org.desz.results.Word;
import org.junit.jupiter.api.Test;

public class TestWordBuilderImpl {
	static final FuncWordMaker<Word> wordBuilder = (j, k, l) -> new WordCreator().formatedNumberToWord(j, k, l);
	static final WordSupplier intWordMapping = getInstance().wordSupplierForProvLang(ProvLang.DE);

	@Test
	public void test_thou() {
		assertNotNull(wordBuilder.formatedNumberToWord(asList(new String[] { "123", "456" }), Word.builder(), intWordMapping));
	}

	@Test
	public void test_mill() {
		assertNotNull(
				wordBuilder.formatedNumberToWord(asList(new String[] { "123", "456", "999" }), Word.builder(), intWordMapping));
	}

	@Test
	public void test_quint() {
		assertNotNull(wordBuilder.formatedNumberToWord(asList(new String[] { "9", "223", "372", "36", "854", "775", "807" }),
				Word.builder(), intWordMapping));
	}

}
