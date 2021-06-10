package com.indracompany.weblogic.ejb.statistics.to;

public enum EnumLinkRel {

	CANONICAL("canonical");

	EnumLinkRel(String string) {
	}

	public boolean equals(String other) {
		return other != null && this.name().equalsIgnoreCase(other);
	}

}
