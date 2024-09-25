package com.collection.ActionResult.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "col_mst_action1")
public class ActionEntity {
	@Id
	private String szactioncode;
	private String szactionname;
	private String cactioncategory;

	public String getSzactioncode() {
		return szactioncode;
	}

	public void setSzactioncode(String szactioncode) {
		this.szactioncode = szactioncode;
	}

	public String getSzactionname() {
		return szactionname;
	}

	public void setSzactionname(String szactionname) {
		this.szactionname = szactionname;
	}

	public String getCactioncategory() {
		return cactioncategory;
	}

	public void setCactioncategory(String cactioncategory) {
		this.cactioncategory = cactioncategory;
	}

}
