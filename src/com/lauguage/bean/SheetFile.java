package com.lauguage.bean;

public class SheetFile {
		
		private String lauguage;
	
		private String filename;
		private String stringkey;
		private String stringvalue;
		public String getLauguage() {
			return lauguage;
		}
		public void setLauguage(String lauguage) {
			this.lauguage = lauguage;
		}
		
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public String getStringkey() {
			return stringkey;
		}
		public void setStringkey(String stringkey) {
			this.stringkey = stringkey;
		}
		public String getStringvalue() {
			return stringvalue;
		}
		public void setStringvalue(String stringvalue) {
			this.stringvalue = stringvalue;
		}
		@Override
		public String toString() {
			return "SheetFile [lauguage=" + lauguage + ", filename=" + filename
					+ ", stringkey=" + stringkey + ", stringvalue="
					+ stringvalue + "]";
		}
		
		
		
		
		
}
