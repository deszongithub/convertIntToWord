package org.desz.converters;

import java.util.List;

import org.desz.language.WordSupplier;
import org.desz.results.Word.WordBuilder;

@FunctionalInterface
public interface FuncWordMaker<T> {

	T formatedNumberToWord(List<String> numbers, WordBuilder wordBuilder, WordSupplier wordMapping);

}
