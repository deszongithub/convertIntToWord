/**
 * 
 */
package org.desz.language;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Map;

import lombok.Builder;
import lombok.Value;

/**
 * @author des
 * 
 *         Single instance per language, NL..FR.
 *
 */

@Builder
@Value
public class WordSupplier {
	String id;
	String quintn;
	String quadrn;
	String trilln;
	String billn;
	String milln;
	String thoud;
	String hund;
	String and;

	private Map<String, String> map;

	/**
	 * @param num the Integer.
	 * @return word for num or empty String.
	 */
	public String forNumber(int num) {
		var key = String.valueOf(num);
		return map.containsKey(key) ? map.get(key) : EMPTY;
	}

}
