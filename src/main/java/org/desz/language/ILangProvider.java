/**
 * Interface for application Errors, number order units
 * and integer to Word Map
 */
package org.desz.language;

/**
 * @author des
 * 
 */
public interface ILangProvider {

	/**
	 * 
	 * @param provLang
	 * @return ILangProvider for provLang.
	 */
	WordSupplier wordSupplierForProvLang(final ProvLang provLang);

}
