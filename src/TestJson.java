import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lauguage.bean.Internation;

public class TestJson {

	public static void main(String[] args) {
		readXMLSheet2();

	}

	private static void readXMLSheet(){
		
        String json = "{\"username\":\"小民\",\"password\":\"xiaomin@sina.com\"}";  
        
        /** 
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。 
         */  
        ObjectMapper mapper = new ObjectMapper();  
        User user;
		try {
			user = mapper.readValue(new File("config.json"), User.class);
			System.out.println(user);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
	}
	
	private static void readXMLSheet2(){
		
		 ObjectMapper mapper = new ObjectMapper();  
	        Internation user;
			try {
				user = mapper.readValue(new File("config.json"), Internation.class);
				System.out.println(user);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
       
	}
	
	
	


}
