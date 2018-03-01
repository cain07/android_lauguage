import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {

	/**
	 * 将file类型的xml转换成对象
	 */
	public static Object convertXmlFileToObject(Class<?> clazz, String xmlPath) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					xmlPath), "GBK");
			xmlObject = unmarshaller.unmarshal(isr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * 将对象根据路径写入指定的xml文件里
	 * 
	 * @param obj
	 * @param path
	 * @return
	 */
	public static void convertToXml(Object obj, String path) {
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
			// 将对象转换成输出流形式的xml
			// 创建输出流
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			marshaller.marshal(obj, fw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> readXML(String XMLPathAndName, T t) {
		long lasting = System.currentTimeMillis();// 效率检测
		List<T> list = new ArrayList<T>();// 创建list集合
		try {
			File f = new File(XMLPathAndName);// 读取文件
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);// dom4j读取
			Element root = doc.getRootElement();// 获得根节点
			Element foo;// 二级节点
			Field[] properties = t.getClass().getDeclaredFields();// 获得实例的属性
			// 实例的get方法
			Method getmeth;
			// 实例的set方法
			Method setmeth;

			for (Iterator i = root
					.elementIterator(t.getClass().getSimpleName()); i.hasNext();) {// 遍历t.getClass().getSimpleName()节点
				foo = (Element) i.next();// 下一个二级节点

				t = (T) t.getClass().newInstance();// 获得对象的新的实例

				for (int j = 0; j < properties.length; j++) {// 遍历所有孙子节点

					// 实例的set方法
					setmeth = t.getClass().getMethod(
							"set"
									+ properties[j].getName().substring(0, 1)
											.toUpperCase()
									+ properties[j].getName().substring(1),
							properties[j].getType());
					// properties[j].getType()为set方法入口参数的参数类型(Class类型)
					setmeth.invoke(t, foo.elementText(properties[j].getName()));// 将对应节点的值存入

				}

				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long lasting2 = System.currentTimeMillis();
		System.out.println("读取XML文件结束,用时" + (lasting2 - lasting) + "ms");
		return list;
	}
}
