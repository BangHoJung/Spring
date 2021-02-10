import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMain {

	public static void main(String[] args) {

		ZipSecureFile.setMinInflateRatio(0);
		try {
			FileInputStream fis = new FileInputStream("excel.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0); 
//			System.out.println("전체 행 개수 : " + sheet.getPhysicalNumberOfRows());
//			System.out.println("해당 행의 컬럼수 : " + sheet.getRow(0).getPhysicalNumberOfCells());
			
			String[] search = new String("서울시 신촌동").split(" ");
			ArrayList<String> args1 = new ArrayList<String>();
			String[] addArgs = new String[5];
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				int j=0,k=0;
				for(j=0;j<sheet.getRow(i).getPhysicalNumberOfCells();j++) {
					if(sheet.getRow(i).getCell(j) != null && sheet.getRow(i).getCell(j).toString().contains(search[k].substring(0, search[k].length()-1))) {
						k++;
						if(search.length == k) {
							break;
						}
					}
				}
				if(search.length == k) {
//					System.out.println(sheet.getRow(i).getCell(2) +" "+sheet.getRow(i).getCell(3)+" / nx:" + sheet.getRow(i).getCell(5) +" , ny:" + sheet.getRow(i).getCell(6));
					for(int x=2;x<=6;x++) {
						System.out.print(sheet.getRow(i).getCell(x) + "\t");
						addArgs[x-2] = sheet.getRow(i).getCell(x).toString();
					}
					break;
				}
			}
			System.out.println("finish");
			WeatherTestMain.main(addArgs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
