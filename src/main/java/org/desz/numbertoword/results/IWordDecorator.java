package org.desz.numbertoword.results;

public interface IWordDecorator {

	Word pluraliseHundredthRule(int val);

	Word pluraliseUnitRule();

	Word combineThouHundRule();

}
