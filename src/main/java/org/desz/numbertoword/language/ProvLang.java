package org.desz.numbertoword.language;

/**
 * ProvLang for language.
 * 
 */

public enum ProvLang {
	EMPTY("EMPTY", "Select..."), UK("UK", "UK-English"), FR("FR",
			"Français"), DE("DE", "Deutsch"), NL("NL", "Nederlandse");

	private String code;
	private String description;

	/**
	 * Constructor
	 * 
	 * @param code
	 * @param description
	 * 
	 */
	private ProvLang(String code, String description) {
		this.code = code;
		this.description = description;

	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}