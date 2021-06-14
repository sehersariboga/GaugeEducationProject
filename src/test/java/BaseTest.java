import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static WebDriver driver;
    //seleniumu maven dependencies e ekleyip alt+enter ile import işlemini yapıyorum.
    protected static WebDriverWait webDriverWait;
    public static String browserName = "chrome";
    DesiredCapabilities capabilities = new DesiredCapabilities();

    //burda dikkat edilmesi gereken nokta javayla yazılırken beforedu şimdi beforescenario
    @BeforeScenario
    public void setUp() throws Exception {
        String baseUrl = "https://www.incehesap.com/";
        String selectPlatform = "win";
        String selectBrowser = "chrome";

        if (StringUtils.isEmpty(System.getenv("key"))) {
            if ("win".equalsIgnoreCase(selectPlatform)) {
                if ("chrome".equalsIgnoreCase(selectBrowser)) {
                    ChromeOptions options = new ChromeOptions();
                    capabilities = DesiredCapabilities.chrome();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver.exe");
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                } else if ("firefox".equalsIgnoreCase(selectBrowser)) {
                    FirefoxOptions options = new FirefoxOptions();
                    capabilities = DesiredCapabilities.firefox();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    FirefoxProfile profile = new FirefoxProfile();
                    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                    capabilities.setCapability("marionette", true);
                    System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver.exe");
                    driver = new FirefoxDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                }

            } else if ("mac".equalsIgnoreCase(selectPlatform)) {
                if ("chrome".equalsIgnoreCase(selectBrowser)) {
                    ChromeOptions options = new ChromeOptions();
                    capabilities = DesiredCapabilities.chrome();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver");
                    driver = new ChromeDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                } else if ("firefox".equalsIgnoreCase(selectBrowser)) {
                    FirefoxOptions options = new FirefoxOptions();
                    capabilities = DesiredCapabilities.firefox();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    options.addArguments("--kiosk");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--start-fullscreen");
                    FirefoxProfile profile = new FirefoxProfile();
                    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                    capabilities.setCapability("marionette", true);
                    System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver");
                    driver = new FirefoxDriver(options);
                    driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                }
            }
        } else {
            ChromeOptions options = new ChromeOptions();
            capabilities = DesiredCapabilities.chrome();
            options.setExperimentalOption("w3c", false);
            options.addArguments("disable-translate");
            options.addArguments("--disable-notifications");
            options.addArguments("--start-fullscreen");
            Map<String, Object> prefs = new HashMap<>();
            options.setExperimentalOption("prefs", prefs);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setCapability("key", System.getenv("key"));
            browserName = System.getenv("browser");
            driver = new RemoteWebDriver(new URL("http://hub.testinium.io/wd/hub"), capabilities);
        }
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get(baseUrl);
    }

    @AfterScenario
    public void tearDown() {

     //   driver.quit();


    }
}


