
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lauguage.bean.Internation;
import com.lauguage.bean.SheetFile;

/**
 * Created by mac on 18/1/22.
 */

public class ResourcesBuilder {
    public static final String DEFAULT_LANGUAGE_FLAG = "values";

    private static List<SheetFile> list;
    
    private static Internation internation;
    /**
     * 各个国家的语言
     */
    public static final String[] LANGUAGE = {
            DEFAULT_LANGUAGE_FLAG,
            "en-rGB", "de-rDE", "fr-rFR", "es-rES"
    };

    // 读取需要生成strings.xml的sheet
    public static final String[] STRINGS_SHEETS = {
            "MusicPlayer",
            "VideoPlayer"
    };

    // 读取需要生成arrays.xml的sheet
    public static final String[] ARRAYS_SHEETS = {
            "MusicPlayer",  // 读取MusicPlayer_arrays sheet中的数据
            //"VideoPlayer"
    };

    /**
     * 资源文件生成的临时目录
     */
    public static String I18N_TEMP_DIR = "";

    /**
     * 语言文件夹前缀
     */
    public static final String RESOURCES_DIR_PREFIX = "values-";

    /**
     * 资源文件名
     */
    public static final String STRING_RESOURCES_FILE_NAME = "strings.xml";
    public static final String ARRAY_RESOURCES_FILE_NAME = "arrays.xml";

    public static void main(String[] args) {
    	readXMLSheet2();
    	I18N_TEMP_DIR = internation.getFileoutput();
        try {
            String file = internation.getFileinput();
            // 清除以前生成的文件和目录
            clearDir(new File(I18N_TEMP_DIR));
            // 创建语言文件夹
            createI18nDir();
            // 生成各个模块中各个国家的strings.xml语言资源文件
            builderXssStringResources(new FileInputStream(file));
            // 生成各个模块中各个国家的arrays.xml语言资源文件
            //builderArrayResources(new FileInputStream(file));
            System.out.println("全部生成成功：" + I18N_TEMP_DIR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void readXMLSheet2(){
		
		 ObjectMapper mapper = new ObjectMapper();  
	        
			try {
				//URL url = ResourcesBuilder.class.getClassLoader().getResource("config.json");
				
				String filePath = System.getProperty("user.dir") + "/config.json";
				//String filePath = "config.json";
				System.out.println(filePath);
				File file = new File(filePath);
				internation = mapper.readValue(file, Internation.class);
				System.out.println(internation);
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
    
    
    public static void readxml() {
    	DOM4JTest<SheetFile> d = new DOM4JTest<SheetFile>();
		SheetFile sheetFile = new SheetFile();

	    list = d.readXML("lauguage_config2.xml", sheetFile);
		System.out.println("XML文件读取结果"+list.toString());
    }

    /**
     * 创建语言文件夹
     */
    public static void createI18nDir() {
    	
        //for (int i = 0; i < internation.getSheets().size(); i++) {
            // 创建模块所对应的目录
            File parent = new File(I18N_TEMP_DIR);
            parent.mkdirs();
            // 创建各个国家语言的资源目录
            list  = internation.getSheets().get(0).getSheetfile();
            for (int j = 0; j < list.size(); j++) {
                String language = null;
               
                language = list.get(j).getLauguage();
             
                File file = new File(parent, language);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        //}
    }
    
    /**
     * 生成strings.xml资源文件
     */
    public static void builderXssStringResources(InputStream is) throws Exception {
        XSSFWorkbook book = new XSSFWorkbook(is);
        for (int i = 0; i < internation.getSheets().size(); i++) {
            XSSFSheet sheet = book.getSheetAt(book.getSheetIndex(internation.getSheets().get(i).getSheetname()));
            System.out.println("build strings for " + sheet.getSheetName());
            int rowNum = sheet.getLastRowNum();
            
            list = internation.getSheets().get(i).getSheetfile();
            
            String fileName = internation.getSheets().get(i).getFilename();
            int startRow = Integer.valueOf(internation.getSheets().get(i).getStartrow());
            
            for (int j = 0; j < list.size(); j++) {
                String language = list.get(j).getLauguage();
                ArrayList<StringEntity> stringEntitys = new ArrayList<StringEntity>();
                File dir = null;
               
                dir = new File(I18N_TEMP_DIR  + File.separator + language);
                File file = new File(dir, "string_"+fileName+".xml");
                for (int k = startRow; k <= rowNum; k++) {
                    XSSFRow row = sheet.getRow(k); //row 是 行
                    if (row.getLastCellNum() < 1){
                    	continue;
                    }
                    int index = Integer.valueOf(list.get(j).getStringkey());
                    String resId = row.getCell(index).getStringCellValue().trim();          // resId
                    
                    int lieIndex = Integer.valueOf(list.get(j).getStringvalue());
                    
                    XSSFCell cell = row.getCell(lieIndex); //第几列
                    String value = null;
                    if (cell != null) {
                    	cell.setCellType(Cell.CELL_TYPE_STRING);
                        value = cell.getStringCellValue();          // 某一个国家的语言
                        if (value == null || "".equals(value.trim())) {
                            continue;
                        }
                        StringEntity entity = new StringEntity(resId, value.trim());
                        stringEntitys.add(entity);
                    }
                }
                // 创建资源文件
                builderStringResources(stringEntitys, file);
            }
        }
        is.close();
        System.out.println("------------------strings.xml资源文件生成成功！------------------");
    }

    /**
     * 生成strings.xml资源文件
     */
    public static void builderStringResources(InputStream is) throws Exception {
        HSSFWorkbook book = new HSSFWorkbook(is);
        for (int i = 0; i < STRINGS_SHEETS.length; i++) {
            HSSFSheet sheet = book.getSheetAt(book.getSheetIndex(STRINGS_SHEETS[i]));
            System.out.println("build strings for " + sheet.getSheetName());
            int rowNum = sheet.getLastRowNum();
            for (int j = 0; j < LANGUAGE.length; j++) {
                String language = LANGUAGE[j];
                ArrayList<StringEntity> stringEntitys = new ArrayList<StringEntity>();
                File dir = null;
                if (DEFAULT_LANGUAGE_FLAG.equals(language)) {   // 创建默认语言
                    dir = new File(I18N_TEMP_DIR + File.separator + language);
                } else {
                    dir = new File(I18N_TEMP_DIR  + File.separator + RESOURCES_DIR_PREFIX + language);
                }
                File file = new File(dir, STRING_RESOURCES_FILE_NAME);
                for (int k = 0; k <= rowNum; k++) {
                    HSSFRow row = sheet.getRow(k);
                    if (row.getLastCellNum() < 1)
                        continue;
                    String resId = row.getCell(0).getStringCellValue().trim();          // resId
                    HSSFCell cell = row.getCell(j + 1);
                    String value = null;
                    if (cell != null) {
                        value = cell.getStringCellValue();          // 某一个国家的语言
                        if (value == null || "".equals(value.trim())) {
                            continue;
                        }
                        StringEntity entity = new StringEntity(resId, value.trim());
                        stringEntitys.add(entity);
                    }
                }
                // 创建资源文件
                builderStringResources(stringEntitys, file);
            }
        }
        is.close();
        System.out.println("------------------strings.xml资源文件生成成功！------------------");
    }

    private static void builderStringResources(List<StringEntity> stringEntitys, File file) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("resources");
        for (StringEntity stringEntity : stringEntitys) {
            Element stringElement = root.addElement("string");
            stringElement.addAttribute("name", stringEntity.getResId());
            stringElement.setText(stringEntity.getValue());
        }
        writer.write(document);
        writer.close();
    }

    /**
     * 生成arrays.xml资源文件
     */
    public static void builderArrayResources(InputStream is) throws Exception {
        HSSFWorkbook book = new HSSFWorkbook(is);
        for (int i = 0; i < ARRAYS_SHEETS.length; i++) { // 功能模块
            HSSFSheet sheet = book.getSheetAt(book.getSheetIndex(ARRAYS_SHEETS[i] + "_arrays"));
            System.out.println("build arrays for " + sheet.getSheetName());
            int rowNum = sheet.getNumMergedRegions();   // sheet.getLastRowNum();
            for (int j = 0; j < LANGUAGE.length; j++) {      // 语言
                String language = LANGUAGE[j];
                ArrayList<ArrayEntity> arrayEntities = new ArrayList<ArrayEntity>();
                File dir = null;
                if (DEFAULT_LANGUAGE_FLAG.equals(language)) {   // 创建默认语言
                    dir = new File(I18N_TEMP_DIR + ARRAYS_SHEETS[i] + File.separator + language);
                } else {
                    dir = new File(I18N_TEMP_DIR + ARRAYS_SHEETS[i] + File.separator + RESOURCES_DIR_PREFIX + language);
                }
                File file = new File(dir, ARRAY_RESOURCES_FILE_NAME);
                for (int k = 1; k <= rowNum; k++) {
                    CellRangeAddress range = sheet.getMergedRegion(k - 1);
                    int mergedRows = range.getNumberOfCells();
                    int lastRow = range.getLastRow();
                    int rowIndex = (lastRow - mergedRows) + 1;
                    String resId = sheet.getRow(rowIndex).getCell(0).getStringCellValue().trim();           // resId
                    ArrayEntity entity = new ArrayEntity(resId);
                    ArrayList<String> items = new ArrayList<String>();
                    for (int z = rowIndex; z <= lastRow; z++) {
                        HSSFCell cell = sheet.getRow(z).getCell(j + 1);
                        String value = getValue(cell);

                        if (value == null || "".equals(value.trim())) { // 如果该语言没有对应的翻译,默认使用英语
                            HSSFCell defaultCell = sheet.getRow(z).getCell(1);
                            value = getValue(defaultCell);
                        }

                        if ("temp".equalsIgnoreCase(value.trim())) {
                            continue;
                        }

                        items.add(value);
                    }
                    entity.setItems(items);
                    arrayEntities.add(entity);
                }
                // 创建资源文件
                builderArrayResources(arrayEntities, file);
            }
        }
        System.out.println("------------------arrays.xml资源文件生成成功！------------------");
    }

    /**
     * 获取单元格的值
     *
     * @param cell 单元格
     * @return 单元格对应的值
     */
    private static String getValue(HSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    value = String.valueOf((int) cell.getNumericCellValue()).trim();
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue()).trim();
                    break;
                default:
                    value = cell.getStringCellValue().trim();
                    break;
            }
        }
        return value;
    }

    private static void builderArrayResources(ArrayList<ArrayEntity> arrayEntities, File file) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("resources");
        for (ArrayEntity arrayEntity : arrayEntities) {
            Element arrayElement = root.addElement("string-array");
            arrayElement.addAttribute("name", arrayEntity.getName());
            List<String> items = arrayEntity.getItems();
            for (String item : items) {
                Element itemElement = arrayElement.addElement("item");
                itemElement.setText(item);
            }
        }
        writer.write(document);
        writer.close();
    }

    /**
     * 清除以前生成的文件和目录
     */
    public static void clearDir(File dir) {
        if (!dir.exists()) return;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                clearDir(file);
            } else {
                file.delete();
            }
        }
        dir.delete();
    }
}
