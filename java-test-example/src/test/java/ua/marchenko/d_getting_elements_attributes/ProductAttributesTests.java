package ua.marchenko.d_getting_elements_attributes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class ProductAttributesTests {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void testProductPropertiesFromCampaignsBlock() {
        By regularPrice = By.className("regular-price");
        By campaignPrice = By.className("campaign-price");
        By campaignsProducts = By.cssSelector("#box-campaigns a.link");
        WebElement product = driver.findElements(campaignsProducts).get(0);
        String mainPageTitle = product.findElement(By.className("name")).getText();
        WebElement mainPageRegularPriceElem = product.findElement(regularPrice);
        float mainPageRegularPrice = getPrice(mainPageRegularPriceElem.getText());
        boolean isPriceCrossedOutOnMainPage = mainPageRegularPriceElem.getTagName().equals("s");
        ArrayList<Integer> mainPageRegularColor = convertToRgbaList(mainPageRegularPriceElem.getCssValue("color"));
        float mainPageRegularPriceSizePx = getSizeInPx(mainPageRegularPriceElem.getCssValue("font-size"));

        WebElement mainPageCampaignPriceElem = product.findElement(campaignPrice);
        float mainPageCampaignPrice = getPrice(mainPageCampaignPriceElem.getText());
        ArrayList<Integer>  mainPageCampaignColor = convertToRgbaList(mainPageCampaignPriceElem.getCssValue("color"));
        float mainPageCampaignPriceSizePx = getSizeInPx(mainPageCampaignPriceElem.getCssValue("font-size"));

        int R_RegularMain = mainPageRegularColor.get(0);
        int G_RegularMain = mainPageRegularColor.get(1);
        int B_RegularMain = mainPageRegularColor.get(2);
        int G_campaignMain = mainPageCampaignColor.get(1);
        int B_campaignMain = mainPageCampaignColor.get(2);
        product.click();
        String productPageTitle = driver.findElement(By.cssSelector("h1.title")).getText();
        WebElement regularPriceOnProductPageElement = driver.findElement(regularPrice);
        WebElement campaignPriceElementOnProductPage = driver.findElement(campaignPrice);
        float productPageCampaignPrice = getPrice(campaignPriceElementOnProductPage.getText());
        float productPageRegularPrice = getPrice(regularPriceOnProductPageElement.getText());
        boolean isRegularPriceCrossedOutOnProductPage = regularPriceOnProductPageElement.getTagName().equals("s");
        float regularPriceProductPagePx = getSizeInPx(regularPriceOnProductPageElement.getCssValue("font-size"));
        float campaignPriceProductPagePx = getSizeInPx(campaignPriceElementOnProductPage.getCssValue("font-size"));
        ArrayList<Integer> productPageCampaignColor = convertToRgbaList(campaignPriceElementOnProductPage.getCssValue("color"));
        ArrayList<Integer> productPageRegularColor = convertToRgbaList(regularPriceOnProductPageElement.getCssValue("color"));
        int R_RegularProduct = productPageRegularColor.get(0);
        int G_RegularProduct = productPageRegularColor.get(1);
        int B_RegularProduct = productPageRegularColor.get(2);
        int G_campaignProduct = productPageCampaignColor.get(1);
        int B_campaignProduct = productPageCampaignColor.get(2);

        Assert.assertTrue((R_RegularMain == G_RegularMain) && (G_RegularMain == B_RegularMain));
        Assert.assertTrue(isPriceCrossedOutOnMainPage);
        Assert.assertTrue((G_campaignMain == 0) && (B_campaignMain == 0));
        Assert.assertTrue(mainPageRegularPriceSizePx < mainPageCampaignPriceSizePx);
        Assert.assertTrue(productPageTitle.equals(mainPageTitle));
        Assert.assertTrue(mainPageCampaignPrice == productPageCampaignPrice);
        Assert.assertTrue(mainPageRegularPrice == productPageRegularPrice);
        Assert.assertTrue(isRegularPriceCrossedOutOnProductPage);
        Assert.assertTrue(campaignPriceProductPagePx > regularPriceProductPagePx);
        Assert.assertTrue((R_RegularProduct == G_RegularProduct) && (G_RegularProduct == B_RegularProduct));
        Assert.assertTrue((G_campaignProduct == 0) && (B_campaignProduct == 0));
    }

    public float getSizeInPx(String s) {
        return Float.parseFloat(s.replaceAll("px", ""));
    }

    public float getPrice(String price) {
        return Float.parseFloat(price.replaceAll("[\\$]", ""));
    }

    public ArrayList<Integer> convertToRgbaList(String color) {
        ArrayList<Integer> rgba = new ArrayList<>();
        String arr [] = color
                .replaceAll("[(]", "")
                .replaceAll("[)]", "")
                .replaceAll("[\\s]", "")
                .replaceAll("rgba", "").split(",");
        for (String c : arr) {
            rgba.add(Integer.parseInt(c));
        }
        return rgba;
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
