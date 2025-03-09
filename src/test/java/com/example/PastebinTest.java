import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class PastebinTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\geras\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe"); // Шлях до браузера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testPastebin() {
        driver.get("https://pastebin.com/");
        System.out.println("Pastebin opened successfully!");

        // Введення тексту у поле "New Paste"
        try {
            WebElement textArea = driver.findElement(By.id("postform-text"));
            textArea.sendKeys("Hello from WebDriver");
            System.out.println("Введено 'Hello from WebDriver'");
        } catch (Exception e) {
            System.out.println("Не вдалося знайти поле вводу тексту");
        }

        // Вибір "10 Minutes" у полі "Paste Expiration"
        try {
            WebElement expirationDropdown = driver.findElement(By.id("postform-expiration"));

            // Використання Selenium Actions для відкриття списку
            Actions actions = new Actions(driver);
            actions.moveToElement(expirationDropdown).click().perform();
            Thread.sleep(500); // Коротка пауза для стабільності

            // Вибір потрібного варіанта
            WebElement tenMinutesOption = driver.findElement(By.xpath("//option[text()='10 Minutes']"));
            tenMinutesOption.click();

            System.out.println("Вибрано '10 Minutes'");
        } catch (Exception e) {
            System.out.println(" Paste Expiration dropdown НЕ знайдено або не працює!");
            e.printStackTrace();
        }

        // Введення заголовка "helloweb"
        try {
            WebElement titleInput = driver.findElement(By.id("postform-name"));
            titleInput.sendKeys("helloweb");
            System.out.println("Введено заголовок 'helloweb'");
        } catch (Exception e) {
            System.out.println("Не вдалося знайти поле заголовка");
        }

        // Натискання на кнопку "Create New Paste"
        try {
            WebElement createButton = driver.findElement(By.xpath("//button[text()='Create New Paste']"));
            createButton.click();
            System.out.println(" Натиснуто 'Create New Paste'");
        } catch (Exception e) {
            System.out.println("Кнопку 'Create New Paste' не знайдено");
        }

        // Додаємо трохи часу, щоб побачити результат перед закриттям браузера
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Закриваємо браузер
        driver.quit();
    }
}
