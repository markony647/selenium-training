package ua.marchenko.d_getting_elements_attributes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CountriesAndZonesSortingTests {

    WebDriver driver;
    WebDriverWait wait;

    By menuSidebar = By.id("box-apps-menu");


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get("http://localhost/litecart/admin");
        login();
    }

    @Test
    public void testCountriesSorting() {
        int countryNameColumnNumber = 4;
        int zoneColumnNumber = 5;
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> rows = getTableRows(By.cssSelector("table.dataTable"));
        List<String> countries = new ArrayList<String>();
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cellsValues = rows.get(i).findElements(By.tagName("td"));
            countries.add(cellsValues.get(countryNameColumnNumber).getText());
        }
        assertInAlpha(countries);

        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cellsValues = getTableRows(By.cssSelector("table.dataTable")).get(i).findElements(By.tagName("td"));
            int codeZone = Integer.parseInt(cellsValues.get(zoneColumnNumber).getText());
            if (codeZone > 0) {
                cellsValues.get(countryNameColumnNumber).findElement(By.cssSelector("a")).click();
                Assert.assertTrue(isElementPresent(By.cssSelector("input[type=radio]")));
                List<WebElement> rowsInZonesTables = getTableRows(By.cssSelector("table.dataTable"));
                List<String> zones = new ArrayList<String>();
                int zonesColumnNumber = 2;
                for (int j = 0; j < rowsInZonesTables.size(); j++) {
                    List<WebElement> cellsValuesZonesTable = rowsInZonesTables.get(i).findElements(By.tagName("td"));
                    zones.add(cellsValuesZonesTable.get(zonesColumnNumber).getText());
                }
                assertInAlpha(zones);
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            }
        }
    }

    @Test
    public void testZonesSorting() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        By editControl = By.cssSelector("table.dataTable a[title=Edit]");
        int numOfCountries = driver.findElements(editControl).size();
        for (int i = 0; i < numOfCountries; i++) {
            getNtsElement(editControl, i).click();
            List<WebElement> geoZones = getAllGeoZones();
            List<String> zones = new ArrayList<>();
            for (WebElement zone : geoZones) {
                zones.add(zone.getText());
            }
           assertInAlpha(zones);
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }

    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(isElementPresent(menuSidebar));
    }

    private void assertInAlpha(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            Assert.assertTrue(list.get(i).compareTo(list.get(i + 1)) <= 0);
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> driver.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public WebElement getNtsElement(By selector, int index) {
        return driver.findElements(selector).get(index);
    }

    public List<WebElement> getAllGeoZones() {
        List<WebElement> countriesAndZonesDropdowns = driver.findElements(By.cssSelector("table.dataTable select option[selected=selected]"));
        List<WebElement> geoZonesDropDowns  = new ArrayList<WebElement>();
        for (int i = 0; i < countriesAndZonesDropdowns.size(); i++) {
            if (i % 2 != 0) {
                geoZonesDropDowns.add(countriesAndZonesDropdowns.get(i));
            }
        }
        return geoZonesDropDowns;
    }

    public List<WebElement> getTableRows(By tableToProcess) {
        WebElement table = driver.findElement(tableToProcess);
        return table.findElements(By.cssSelector("tr.row"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
