import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.By;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
//driver ı alması için extends basetest dememiz gerekiyor
public class StepImplementation extends BaseTest{
@Step("<keyword> Css li elemente tıkla")
    public void clickToElementForCss (String keyword){
    driver.findElement(By.cssSelector(keyword)).click();

}
//stepin içine <> diyerek string veya int değeleri verebiliyoruz.
@Step("3 sn kadar bekle")
    public void waitForSecond() throws InterruptedException {
    Thread.sleep(3000);
}
    @Step("<keyword> Css li elemente <text> değerini yaz")
    public void sendKeysToElementForCss (String keyword,String text) {
        driver.findElement(By.cssSelector(keyword)).sendKeys(text);
    }
    @Step("<keyword> id li elemente tıkla")
    public void clickToElementForid (String keyword){
        driver.findElement(By.id(keyword)).click();

    }

    }
