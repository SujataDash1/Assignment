package handsonAssignment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class assignment1 {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		forEachLink(driver);
		streamLink(driver);
		parallelStreamLink(driver);
		lambdaExpressionLink(driver);

		driver.quit();
	}

	// Using For Each Loop
	public static void forEachLink(WebDriver driver) {
		System.out.println("*************For Each Loop*************");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			System.out.println("Links are : " + url);
		}
	}

	// Using Stream
	public static void streamLink(WebDriver driver) {
		System.out.println("*************Stream*************");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		List<String> url = links.stream().map(link -> link.getAttribute("href")).collect(Collectors.toList());
		System.out.println("Links are : " + url);
	}

	// Using Parallel Stream
	public static void parallelStreamLink(WebDriver driver) {
		System.out.println("*************Parallel Stream*************");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.parallelStream().forEach(link -> System.out
				.println(link.getAttribute("href") + " :- Thread Name :- " + Thread.currentThread().getName()));
	}

	// Using Lambda Expression
	public static void lambdaExpressionLink(WebDriver driver) {
		System.out.println("*************Lambda Expression Stream*************");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.forEach(link -> System.out.println(link.getAttribute("href")));
	}
}
