package org.desz.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Supported languages.
 * 
 */
@RequiredArgsConstructor
public enum ProvLang {
	EMPTY("EMPTY", "Select..."), UK("UK", "UK-English"), FR("FR", "Français"), DE("DE", "Deutsch"),
	NL("NL", "Nederlandse");

	@Getter
	private final String code;
	@Getter
	private final String description;



}