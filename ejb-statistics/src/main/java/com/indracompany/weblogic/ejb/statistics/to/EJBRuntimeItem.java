package com.indracompany.weblogic.ejb.statistics.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "")
public class EJBRuntimeItem {

	@JsonProperty("EJBName")
	private String ejbname;

	@JsonProperty("type")
	private String type;

	@JsonProperty("links")
	private List<Link> links;

	@JsonIgnore
	private EJBPoolRuntimeInfo poolRuntimeInfo;

	public String getEjbName() {
		return ejbname;
	}

	public void setEjbName(String ejbname) {
		this.ejbname = ejbname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public EJBPoolRuntimeInfo getPoolRuntimeInfo() {
		return poolRuntimeInfo;
	}

	public void setPoolRuntimeInfo(EJBPoolRuntimeInfo poolRuntimeInfo) {
		this.poolRuntimeInfo = poolRuntimeInfo;
	}

	public String toCsv() {
		return ejbname + ";" + getCsvPoolRuntimeInfo();
	}

	public String getCsvHeader() {
		return "ejbname;type;" + getCsvHeaderPoolRuntimeInfo();
	}

	private String getCsvHeaderPoolRuntimeInfo() {
		if (poolRuntimeInfo != null) {
			return poolRuntimeInfo.getCsvHeader();
		}
		return "";
	}

	private String getCsvPoolRuntimeInfo() {
		if (poolRuntimeInfo != null) {
			return poolRuntimeInfo.toCsv();
		}
		return "";
	}

}
