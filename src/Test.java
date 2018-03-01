import java.util.ArrayList;
import java.util.List;

import com.lauguage.bean.Sheet;
import com.lauguage.bean.SheetFile;

public class Test {

	public static void main(String[] args) {
		readXMLSheet();
		 //Users users = (Users) XMLUtil.convertXmlFileToObject(Users.class, "lauguage_config.xml");
		
	}

	private static void readXMLSheet(){
		DOM4JTest<SheetFile> d = new DOM4JTest<SheetFile>();
		SheetFile sheetFile = new SheetFile();

		List<SheetFile> list = d.readXML("lauguage_config2.xml", sheetFile);
		System.out.println("XML文件读取结果"+list.toString());
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(usename.getSheet().get(0).getFilename());
		}
	}
	
	private static void readXMLSheet2(){
		DOM4JTest<Sheet> d = new DOM4JTest<Sheet>();
		Sheet sheetFile = new Sheet();

		List<Sheet> list = d.readXML("lauguage_config.xml", sheetFile);
		System.out.println("XML文件读取结果"+list.toString());
		for (int i = 0; i < list.size(); i++) {
			Sheet usename = (Sheet) list.get(i);
			//System.out.println(usename.getSheet().get(0).getFilename());
		}
	}
	
	private void readXMLUser(){
		DOM4JTest<User> d = new DOM4JTest<User>();
		User user = new User();

		List<User> list = d.readXML("lauguage_config.xml", user);
		System.out.println("XML文件读取结果");
		for (int i = 0; i < list.size(); i++) {
			User usename = (User) list.get(i);
			System.out.println(usename.getPassword());
		}
	}
	
	private void writeXmlDocument() {
		DOM4JTest<User> d = new DOM4JTest<User>();
		User user = new User();
		User user1 = new User("姓名1", "18");
		User user2 = new User("姓名2", "19");
		User user3 = new User("石头", "20");

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);

		d.writeXmlDocument(user, users, "GBK", "/Users/mac/Downloads/users.xml");
	}

}
