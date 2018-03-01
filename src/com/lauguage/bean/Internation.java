package com.lauguage.bean;


import java.util.List;

public class Internation {

	private List<Sheet> sheets;
	
	private String fileinput;
	
	private String fileoutput;
	
	

	public String getFileoutput() {
		return fileoutput;
	}

	public void setFileoutput(String fileoutput) {
		this.fileoutput = fileoutput;
	}

	public List<Sheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	public String getFileinput() {
		return fileinput;
	}

	public void setFileinput(String fileinput) {
		this.fileinput = fileinput;
	}

	@Override
	public String toString() {
		return "Internation [sheets=" + sheets + ", fileinput=" + fileinput
				+ "]";
	}
	
	
}
