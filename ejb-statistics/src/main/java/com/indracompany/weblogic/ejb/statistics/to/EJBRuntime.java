package com.indracompany.weblogic.ejb.statistics.to;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "")
public class EJBRuntime {

	@JsonIgnore
	private String moduleName;

	@JsonProperty("items")
	private List<EJBRuntimeItem> items;

	@JsonProperty("links")
	private List<Link> links;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		if (moduleName != null) {
			this.moduleName = moduleName.replace(".jar", "");
		}
	}

	public List<EJBRuntimeItem> getItems() {
		return items;
	}

	public void setItems(List<EJBRuntimeItem> items) {
		this.items = items;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public String getCsvHeader() {
		return "moduleName;" + (new EJBRuntimeItem()).getCsvHeader() + "\n";
	}

	public String toCsv() {
		String retVal = moduleName + ";";
		StringBuilder strBuilder = new StringBuilder();
		Date date = Calendar.getInstance().getTime();
		if (items != null && !items.isEmpty()) {
//			strBuilder.append("moduleName;" + items.get(0).getCsvHeader() + "\n");
			items.stream().forEach(ejbRuntimeItem -> strBuilder
					.append(date + ";" + moduleName + ";" + ejbRuntimeItem.toCsv() + "\n"));
			return strBuilder.toString();
		}
		return retVal;
	}

}
