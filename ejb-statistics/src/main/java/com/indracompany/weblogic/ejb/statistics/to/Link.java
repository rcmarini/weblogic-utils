package com.indracompany.weblogic.ejb.statistics.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "link")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {

	@JsonProperty("rel")
	private String rel;
	
	@JsonProperty("href")
	private String href;

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
