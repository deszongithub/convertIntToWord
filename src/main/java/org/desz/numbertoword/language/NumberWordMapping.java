/**
 * 
 */
package org.desz.numbertoword.language;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Value;

/**
 * @author des
 * 
 *         Instance per language, NL..FR. ProvLangFactory maintains cache.
 *
 */

@Builder
@Value
public class NumberWordMapping {
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
	public String wordForNum(int num) {
		var key = String.valueOf(num);
		return map.containsKey(key) ? map.get(key) : StringUtils.EMPTY;
	}

}
