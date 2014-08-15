package cross.run.englishweekly.verifytool.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cross.run.englishweekly.verifytool.bean.BaseRegex;

public class XlsxAnalysis {

	private List<BaseRegex> xlsxs;
	
	private XSSFWorkbook reader;
	
	private String path;
	
	public XlsxAnalysis(String path){
		this.path = path+File.separator+"ResourceInfo.xlsx";
	}
	
	public boolean init(){
		try {
			xlsxs = new ArrayList<BaseRegex>();
			reader = new  XSSFWorkbook(new FileInputStream(path));
			XSSFSheet sheet = reader.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 0; r < rows; r++) {
				XSSFRow row = sheet.getRow(r);
				if (row == null) {
					continue;
				}

				int cells = row.getPhysicalNumberOfCells();
				System.out.println("\nROW " + row.getRowNum() + " has " + cells
						+ " cell(s).");
				for (int c = 0; c < cells; c++) {
					XSSFCell cell = row.getCell(c);
					String value = null;

					switch (cell.getCellType()) {

						case HSSFCell.CELL_TYPE_FORMULA:
							value = "FORMULA value=" + cell.getCellFormula();
							break;

						case HSSFCell.CELL_TYPE_NUMERIC:
							value = "NUMERIC value=" + cell.getNumericCellValue();
							break;

						case HSSFCell.CELL_TYPE_STRING:
							value = "STRING value=" + cell.getStringCellValue();
							break;

						default:
					}
					System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
							+ value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
}
