package org.desz.factories;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.desz.exceptions.AppConversionException;
import org.desz.language.ILangProvider;
import org.desz.language.WordSupplier;
import org.desz.language.ProvLang;
import org.desz.language.ProvLangValues.DePair;
import org.desz.language.ProvLangValues.DeUnit;
import org.desz.language.ProvLangValues.FrPair;
import org.desz.language.ProvLangValues.FrUnit;
import org.desz.language.ProvLangValues.NlPair;
import org.desz.language.ProvLangValues.NlUnit;
import org.desz.language.ProvLangValues.UkPair;
import org.desz.language.ProvLangValues.UkUnit;

/**
 * Factory for numeric and error values associated to supported ProvLang.
 * 
 * @author des
 * 
 */
public final class ProvLangFactory implements ILangProvider {

	private static final Map<ProvLang, WordSupplier> provLangCache = new ConcurrentHashMap<>();

	/**
	 * singleton.
	 */
	private ProvLangFactory() {
	}

	/**
	 * static class not loaded until referenced.
	 * 
	 * @author des
	 *
	 */
	private static class ProvLangHolder {
		private static final ILangProvider provLangStore = new ProvLangFactory();
	}

	/**
	 * 
	 * @return singleton instance.
	 */
	public static ILangProvider getInstance() {
		return ProvLangHolder.provLangStore;
	}

	@Override
	public WordSupplier wordSupplierForProvLang(final ProvLang pl) {
		return provLangCache.containsKey(pl) ? provLangCache.get(pl) : buildWordSupplier(pl);

	}

	private WordSupplier buildWordSupplier(final ProvLang pl) {
		var provLang = requireNonNull(pl);

		var builder = WordSupplier.builder();
	
		switch (provLang) {

		case UK -> {
			builder.id(ProvLang.UK.name());
			builder.quintn(UkUnit.QUINTS.getVal());
			builder.quadrn(UkUnit.QUADS.getVal());
			builder.trilln(UkUnit.TRILLS.getVal());
			builder.billn(UkUnit.BILLS.getVal());
			builder.milln(UkUnit.MILLS.getVal());
			builder.thoud(UkUnit.THOUS.getVal());
			builder.hund(UkUnit.HUNS.getVal());
			builder.and(UkUnit.AND.getVal());
			builder.map(of(UkPair.values()).collect(toMap(UkPair::getNum, UkPair::getWord)));

			provLangCache.put(ProvLang.UK, builder.build());
		}

		case FR -> {

			builder.id(ProvLang.FR.name());
			builder.quintn(FrUnit.QUINTS.getVal());
			builder.quadrn(FrUnit.QUADS.getVal());
			builder.trilln(FrUnit.TRILLS.getVal());
			builder.billn(FrUnit.BILLS.getVal());
			builder.milln(FrUnit.MILLS.getVal());
			builder.thoud(FrUnit.THOUS.getVal());
			builder.hund(FrUnit.HUNS.getVal());
			builder.and(FrUnit.AND.getVal());
			builder.map(of(FrPair.values()).collect(toMap(FrPair::getNum, FrPair::getWord)));
			provLangCache.put(ProvLang.FR, builder.build());
		}

		case DE -> {

			builder.id(ProvLang.DE.name());
			builder.quintn(DeUnit.QUINTS.getVal());
			builder.quadrn(DeUnit.QUADS.getVal());
			builder.trilln(DeUnit.TRILLS.getVal());
			builder.billn(DeUnit.BILLS.getVal());
			builder.milln(DeUnit.MILLS.getVal());
			builder.thoud(DeUnit.THOUS.getVal());
			builder.hund(DeUnit.HUNS.getVal());
			builder.and(DeUnit.AND.getVal());
			builder.map(of(DePair.values()).collect(toMap(DePair::getNum, DePair::getWord)));
			provLangCache.put(ProvLang.DE, builder.build());
		}

		case NL -> {

			builder.id(ProvLang.NL.name());
			builder.quintn(NlUnit.QUINTS.getVal());
			builder.quadrn(NlUnit.QUADS.getVal());
			builder.trilln(NlUnit.TRILLS.getVal());
			builder.billn(NlUnit.BILLS.getVal());
			builder.milln(NlUnit.MILLS.getVal());
			builder.thoud(NlUnit.THOUS.getVal());
			builder.hund(NlUnit.HUNS.getVal());
			builder.and(NlUnit.AND.getVal());
			builder.map(of(NlPair.values()).collect(toMap(NlPair::getNum, NlPair::getWord)));
			provLangCache.put(ProvLang.NL, builder.build());
		}

		default -> throw new AppConversionException();

		}
		return provLangCache.get(provLang);
	}

}
