import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ParseStringResources {

	public static void main(String[] args) {  
        try {  
            //getStringsIds();  
            //getArraysIds(); 
        	Users users = new Users();   
            User u = new User();   
            u.setUsername("admin");  
            u.setPassword("123");   
            List<User> lus = new ArrayList<User>();   
            lus.add(u);   
            //users.setUserList(lus);  
            System.out.print(users.toString());
            try {  
                XMLUtil.convertToXml(users, "/Users/mac/Downloads/users.xml");  
            } catch (Exception e1) {  
                e1.printStackTrace();  
            }  

            
             //Users users = (Users) XMLUtil.convertXmlFileToObject(Users.class, "string_MusicPlayer.xml");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void getStringsIds() throws Exception {  
        System.out.println("-------------------MusicPlayer----------------------------");  
        //ParseStringResources.parseStringsResourcesKey(new FileInputStream("string_MusicPlayer.xml"));      // 解析id  
        ParseStringResources.parseStringsResourcesValue(new FileInputStream("string_MusicPlayer.xml"));    // 解析value  
          
        System.out.println("-------------------VideoPlayer----------------------------");  
        //ParseStringResources.parseStringsResourcesKey(new FileInputStream("video_strings.xml"));  
        //ParseStringResources.parseStringsResourcesValue(new FileInputStream("video_strings.xml"));  
    }  
      
    public static void getArraysIds() throws Exception {  
        InputStream is = null;  
        is = new FileInputStream("musicplayer_arrays.xml");  
        ParseStringResources.parseArraysResources(is);  
    }  
      
    /** 
     * 解析strings.xml中的key 
     */  
    public static void parseStringsResourcesKey(InputStream is) throws Exception {  
        SAXReader saxReader = new SAXReader();  
        Document document = saxReader.read(is);  
        Element rootElement = document.getRootElement();  
        List<Element> elements = rootElement.elements();  
        for (Element element : elements) {  
            String resid = element.attribute("name").getValue();  
            System.out.println(resid);  
        }  
        System.out.println("-----------------------------------key解析完成---------------------------------");  
    }  
      
    /** 
     * 解析strings.xml中的value 
     */  
    public static void parseStringsResourcesValue(InputStream is) throws Exception {  
        SAXReader saxReader = new SAXReader();  
        Document document = saxReader.read(is);  
        Element rootElement = document.getRootElement();  
        List<Element> elements = rootElement.elements();  
        for (Element element : elements) {  
            String text = element.getTextTrim();  
            System.out.println(text);  
        }  
        System.out.println("-----------------------------------value解析完成---------------------------------");  
    }  
      
    /** 
     * 解析arrays.xml文件 
     */  
    public static void parseArraysResources(InputStream is) throws Exception {  
        SAXReader saxReader = new SAXReader();  
        Document document = saxReader.read(is);  
        Element rootElement = document.getRootElement();  
        List<Element> elements = rootElement.elements();  
        for (Element element : elements) {  
            Attribute attribute = element.attribute("name");  
            if (attribute == null) continue;  
            String resid = attribute.getValue();  
            System.out.println(resid);  
            List<Element> items = element.elements();  
            for (Element item : items) {  
                String text = item.getTextTrim();  
                System.out.println("      " + text);  
            }  
        }  
    } 
    
    
}
