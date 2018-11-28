package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class SiteLogin {

	private String user;
	private String pw;
	private WebDriver driver;

	public SiteLogin(String user, String pw) {
		this.user = user;
		this.pw = pw;
	}

	public void runElearning() {
		try {
			knowOS();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.navigate().to("https://e-learning.iscte-iul.pt");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			driver.findElement(By.cssSelector("#user_id")).sendKeys(user);
			driver.findElement(By.cssSelector("#password")).sendKeys(pw);
			driver.findElement(By.cssSelector("#entry-login")).click();

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		} catch (UnreachableBrowserException e) {
			quit();
		}
	}

	public void runFenix() {
		try {
			knowOS();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.navigate().to("https://fenix.iscte-iul.pt/loginPage.jsp");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			driver.findElement(By.cssSelector("#username")).sendKeys(user);
			driver.findElement(By.cssSelector("#password")).sendKeys(pw);
			driver.findElement(By.name("ok")).click();

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		} catch (UnreachableBrowserException e) {
			quit();
		}
	}

	public void quit() {
		driver.close();
		driver.quit();
	}
	
	public void knowOS() {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_windows.exe");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac os")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_macos");
		} else {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

	public static void main(String[] args) {
		SiteLogin ell = new SiteLogin("ze", "fasfsa");
		ell.runFenix();

	}
}
