package org.tc;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Crawl {

	final static String baseXpath = "//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[1]/div[2]";

	public static void main(String... args) throws IOException {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.globaltennisnetwork.com/tennis-courts/courts/state/5-california");

		getAllLinks(driver);
		driver.quit();
	}

	private static void getAllLinks(WebDriver driver) {
		List<WebElement> tables = driver
				.findElements(By.className("adminlist"));
		if (tables.size() != 0) {
			List<WebElement> innerLinks = driver
					.findElements(By.className("adminlist")).get(0)
					.findElements(By.tagName("a"));

			// System.out.println(innerLinks.size());
			for (int j = 0; j < innerLinks.size(); j++) {

				WebElement innerLink = driver
						.findElements(By.className("adminlist")).get(0)
						.findElements(By.tagName("a")).get(j);
				if (innerLink.findElement(By.xpath("..")).getTagName()
						.equalsIgnoreCase("td")) {
					// System.out.println(innerLink.getAttribute("href"));
					// System.out.println(innerLink.getText());
					innerLink.click();
					getAllLinks(driver);
				}
			}
		}

		List<WebElement> address = driver.findElements(By
				.className("adminContent"));
		if (address.size() > 0) {
			Address addressD = getAddress(address.get(0));
			WebElement courtDetailsElet = driver
					.findElement(By
							.xpath("//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[1]"));
			CourtDetails courtDetails = getCourtDetails(courtDetailsElet);
			WebElement mapElet = driver.findElement(By
					.linkText("Get directions"));
			String map = mapElet.getAttribute("href");
			System.out.println(addressD + map);
			MongoDb.writeToDb(courtDetails ,addressD, map);
		}

		// System.out.println("going back");
		driver.navigate().back();

	}

	private static CourtDetails getCourtDetails(WebElement courtDetailsElet) {
		String locationType = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]");
		String totalCourts = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[2]/td[2]");
		String indoorCourts = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[3]/td[2]");
		String fees = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[4]/td[2]");
		String courtType = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[5]/td[2]");
		String lights = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[6]/td[2]");
		String amenities = getData(
				courtDetailsElet,
				"//*[@id=\"mainbody\"]/div/div/div/div/div/div/table/tbody/tr/td[2]/div[1]/div/table/tbody/tr/td[1]/div[2]/div[2]/table/tbody/tr[7]/td[2]");

		CourtDetails courtDetails = new CourtDetails(locationType, totalCourts, indoorCourts, fees, courtType, lights, amenities);
		
		return courtDetails;
	}

	private static Address getAddress(WebElement addressD) {
		String name = getData(addressD, "//*[@itemprop=\"name\"]");
		String streetAddress = getData(addressD,
				"//*[@itemprop=\"streetAddress\"]");
		String addressLocality = getData(addressD,
				"//*[@itemprop=\"addressLocality\"]");
		String addressRegion = getData(addressD,
				"//*[@itemprop=\"addressRegion\"]");
		String postalCode = getData(addressD, "//*[@itemprop=\"name\"]");
		String nationality = getData(addressD, "//*[@itemprop=\"nationality\"]");

		Address address = new Address(name, streetAddress, addressLocality,
				addressRegion, postalCode, nationality);
		return address;
	}

	private static String getData(WebElement addressD, String string) {

		List<WebElement> elet = addressD.findElements(By.xpath(string));
		if (elet.size() > 0)
			return elet.get(0).getText();
		else {
			System.out.println("wrong addr------------------->" + addressD);
			return "";
		}
	}

}