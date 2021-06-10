package com.indracompany.weblogic.ejb.statistics.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EJBPoolRuntimeInfo {

	private Integer pooledBeansCurrentCount = 0;

	private Integer destroyedTotalCount = 0;

	private Integer accessTotalCount = 0;

	private Integer beansInUseCurrentCount = 0;

	private Integer missTotalCount = 0;

	private Integer timeoutTotalCount = 0;

	private Integer waiterCurrentCount = 0;

	public Integer getPooledBeansCurrentCount() {
		return pooledBeansCurrentCount;
	}

	public void setPooledBeansCurrentCount(Integer pooledBeansCurrentCount) {
		this.pooledBeansCurrentCount = pooledBeansCurrentCount;
	}

	public Integer getDestroyedTotalCount() {
		return destroyedTotalCount;
	}

	public void setDestroyedTotalCount(Integer destroyedTotalCount) {
		this.destroyedTotalCount = destroyedTotalCount;
	}

	public Integer getAccessTotalCount() {
		return accessTotalCount;
	}

	public void setAccessTotalCount(Integer accessTotalCount) {
		this.accessTotalCount = accessTotalCount;
	}

	public Integer getBeansInUseCurrentCount() {
		return beansInUseCurrentCount;
	}

	public void setBeansInUseCurrentCount(Integer beansInUseCurrentCount) {
		this.beansInUseCurrentCount = beansInUseCurrentCount;
	}

	public Integer getMissTotalCount() {
		return missTotalCount;
	}

	public void setMissTotalCount(Integer missTotalCount) {
		this.missTotalCount = missTotalCount;
	}

	public Integer getTimeoutTotalCount() {
		return timeoutTotalCount;
	}

	public void setTimeoutTotalCount(Integer timeoutTotalCount) {
		this.timeoutTotalCount = timeoutTotalCount;
	}

	public Integer getWaiterCurrentCount() {
		return waiterCurrentCount;
	}

	public void setWaiterCurrentCount(Integer waiterCurrentCount) {
		this.waiterCurrentCount = waiterCurrentCount;
	}

	public String toCsv() {
		return pooledBeansCurrentCount + ";" + destroyedTotalCount + ";" + accessTotalCount + ";"
				+ beansInUseCurrentCount + ";" + missTotalCount + ";" + timeoutTotalCount + ";" + waiterCurrentCount
				+ ";";
	}

	public String getCsvHeader() {
		return "pooledBeansCurrentCount;destroyedTotalCount;accessTotalCount;"
				+ "beansInUseCurrentCount;missTotalCount;timeoutTotalCount;waiterCurrentCount;";
	}

}
