package org.desz.results;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Word {
	String quint;
	String quadr;
	String trill;
	String bill;
	String mill;
	String thou;
	String hund;

}
