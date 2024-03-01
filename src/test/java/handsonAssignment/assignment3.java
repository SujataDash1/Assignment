package handsonAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class assignment3 {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		// Read the data in XLS file and store them in HashMap1
		HashMap<String, String> map1 = new HashMap<String, String>();
		FileInputStream fis = new FileInputStream("C:\\Users\\61069741\\OneDrive - LTIMindtree\\Desktop\\Western Union\\Assignments\\Assignment3.xlsx");
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			String key = sheet.getRow(i).getCell(0).getStringCellValue();
			String value = sheet.getRow(i).getCell(1).getStringCellValue();
			map1.put(key, value);
		}
		System.out.println("Data from Rediff Money page stored in HashMap Using excel file : ");
		for (Map.Entry<String, String> entry : map1.entrySet()) {
			System.out.println("Company Name: " + entry.getKey() + ", Current Price: " + entry.getValue());
		}

		// Read the data Using Selenium WebDriver and store them in HashMap2
		HashMap<String, String> map2 = new HashMap<String, String>();
		WebDriver driver = new ChromeDriver();
		driver.get("https://money.rediff.com/losers/bse/daily/groupall");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		List<WebElement> tableData = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
		for (int i = 0; i < 15; i++) {
			WebElement row = tableData.get(i);
			String companyName = row.findElement(By.xpath("td[1]")).getText();
			String currentPrice = row.findElement(By.xpath("td[4]")).getText();
			map2.put(companyName, currentPrice);
		}
		System.out.println("Data from Rediff Money page stored in HashMap Using Selenium WebDriver : ");
		for (Map.Entry<String, String> entry : map2.entrySet()) {
			System.out.println("Company Name: " + entry.getKey() + ", Current Price: " + entry.getValue());
		}

		// Compare Both HashMaps
		if (compareHashmaps(map1, map2) == true) {
			System.out.println("Both the HashMaps are Equal");
		} else {
			System.out.println("Both the HashMaps are not matching");
		}

		driver.quit();
	}

	public static <Key, Value> boolean compareHashmaps(Map<Key, Value> map1, Map<Key, Value> map2) {
		if (map1.size() != map2.size()) {
			return false;
		}
		for (Map.Entry<Key, Value> entry : map1.entrySet()) {
			Key key = entry.getKey();
			Value value1 = entry.getValue();
			Value value2 = map2.get(key);
			if (value1 == null && value2 == null) {
				continue;
			} else if (value1 == null || !value1.equals(value2)) {
				return false;
			}
		}
		return true;
	}
}