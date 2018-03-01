package com.lauguage.bean;

import java.util.List;


public class Sheet {

	private List<SheetFile> sheetfile;

	private String sheetname;
	
	private String filename;
	
	private String startrow;
	
	

	public String getStartrow() {
		return startrow;
	}

	public void setStartrow(String startrow) {
		this.startrow = startrow;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<SheetFile> getSheetfile() {
		return sheetfile;
	}

	public void setSheetfile(List<SheetFile> sheetfile) {
		this.sheetfile = sheetfile;
	}

	public String getSheetname() {
		return sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	@Override
	public String toString() {
		return "Sheet [sheetfile=" + sheetfile + ", sheetname=" + sheetname
				+ ", filename=" + filename + "]";
	}

	
	
	
	
	
}
