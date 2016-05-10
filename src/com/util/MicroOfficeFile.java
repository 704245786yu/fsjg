package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

/*
 * 需要注意：sheet.getLastRowNum()和getLastCellNum();有可能返回的值与我们预期的不一样，
 * 当单元格设置了格式后，填入值，又删除，但此时有些格式信息没有跟着删除，
 * 这时getLastRowNum返回的值就会大于我们预期的值。
 * 只有单元格没设置格式时才能正确获取值，所以这两个方法其实没什么用。
 * */

public class MicroOfficeFile {

	/**
	 * @author 支煜
	 * */
	public Workbook readExcel(MultipartFile multipartFile) {
//		multipartFile.getName();	//得到的是<input type="file">的name属性值。
//		System.out.println(multipartFile.getOriginalFilename() +" uploaded! ");	//获取文件名
		Workbook wb = null;	//用Workbook是读取Excel2003、2007的通用方法。
		try {
			InputStream ins = multipartFile.getInputStream();
			wb = WorkbookFactory.create(ins);
		} catch (IOException e) {
			System.out.println("获取文档数据流出错。");
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			System.out.println("创建Excel文件出错。");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
			System.out.println("这不是一个Excel文件");
		}
		return wb;
	}

	/**
	 * 取Excel所有数据，包含header
	 * @return List<String[]>
	 */
	public List<String[]> getAllData(Workbook wb, int sheetIndex) {
		List<String[]> dataList = new ArrayList<String[]>();
		int columnNum = 0;
		Sheet sheet = wb.getSheetAt(sheetIndex);
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
		}
		if (columnNum > 0) {
			for (Row row : sheet) {
				String[] singleRow = new String[columnNum];
				int n = 0;
				for (int i = 0; i < columnNum; i++) {
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n] = Boolean.toString( cell.getBooleanCellValue() );
						break;
					// 数值
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {	//判断是否是日期
							Date date = cell.getDateCellValue();
							singleRow[n] = DateTransform.Date2String(date, "yyyy-MM-dd HH:mm:ss");
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp = cell.getStringCellValue();
							// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
							if (temp.indexOf(".") > -1) {
								singleRow[n] = String.valueOf( new Double(temp) ).trim();
							} else {
								singleRow[n] = temp.trim();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n] = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n] = cell.getStringCellValue();
						if (singleRow[n] != null) {
							singleRow[n] = singleRow[n].replaceAll("#N/A", "").trim();
						}
						break;
					default:
						singleRow[n] = "";
						break;
					}
					n++;
				}
				if ("".equals(singleRow[0])) {
					continue;
				}// 如果第一行为空，跳过
				dataList.add(singleRow);
			}
		}
		return dataList;
	}

	/*
	 * private void resolveExcel2003(InputStream ins, int beginRowNum, int
	 * colNums) { HSSFWorkbook hwb = null; try { hwb = new HSSFWorkbook(ins); }
	 * catch (IOException e) { System.out.println("创建Excel2003对象出错。");
	 * e.printStackTrace(); }
	 * 
	 * HSSFSheet sheet = hwb.getSheetAt(0); for (int i = beginRowNum; i <=
	 * sheet.getLastRowNum(); i++) { HSSFRow row = sheet.getRow(i);//Row是从0开始的
	 * HSSFCell cell = null; for(int j=0; j<colNums; j++) { cell =
	 * row.getCell(j); if (cell != null) {
	 * System.out.println(cell.getStringCellValue()); return; }else{
	 * System.out.println("此单元格为NULL"); } } } }
	 * 
	 * private void resolveExcel2007(InputStream ins, int beginRowNum, int
	 * colNums) { XSSFWorkbook xwb = null; try { xwb = new XSSFWorkbook(ins); }
	 * catch (IOException e) { System.out.println("创建Excel2007对象出错。");
	 * e.printStackTrace(); }
	 * 
	 * XSSFSheet sheet = xwb.getSheetAt(0); for (int i = beginRowNum; i <=
	 * sheet.getLastRowNum(); i++) { XSSFRow row = sheet.getRow(i);//Row是从0开始的
	 * XSSFCell cell = null; for(int j=0; j<colNums; j++) { cell =
	 * row.getCell(j); if (cell != null) {
	 * System.out.println(cell.getStringCellValue()); return; }else{
	 * System.out.println("此单元格为NULL"); } } } }
	 */
}
