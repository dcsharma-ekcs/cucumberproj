package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

import static junit.framework.TestCase.fail;

public class UtilFunctions {

    WebDriver driver = null;

    public UtilFunctions(WebDriver driver){
        this.driver = driver;
    }

    public void waitForXpath(By element) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(element)) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

    }

    public void waitForXpathElement(String strXpath) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath(strXpath))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }

    }



    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void clickStickyArrow() throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            Thread.sleep(1000);
            try {
                if (isElementPresent(By.xpath("//div[@class='sticky-arrow showOn']"))) break;
            } catch (Exception e) {
            }

        }
        driver.findElement(By.xpath("//div[@class='sticky-arrow showOn']")).click();
    }

    public void waitAndClickXpath(String strXpath) throws InterruptedException {

        for (int second = 0; ; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.xpath(strXpath))) break;
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        driver.findElement(By.xpath(strXpath)).click();

    }

    public void clickOutside() {
        Actions action = new Actions(driver);
        action.moveByOffset(0, 0).click().build().perform();

    }

    public void switchToChildWindow(){
        String parent = driver.getWindowHandle();
        //System.out.println("parent:   "+parent);
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        System.out.println("count :   "+s.size());
        while(I1.hasNext())
        {
            String child_window=I1.next();
            driver.switchTo().window(child_window);
        }
    }

    public void switchToParentWindow(){
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        System.out.println("count :   "+s.size());
        String parent_window=I1.next();
        driver.switchTo().window(parent_window);
        driver.switchTo().defaultContent();
    }
}
