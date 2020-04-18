package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.sainsburys.scraper.services.ProductDetailsService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static uk.co.sainsburys.scraper.utils.ResourceUtils.loadHtmlAsString;

@SpringBootTest
public class ProductDetailsServiceImplTest {

  private static final Integer CALORIES = 33;
  private static final String DESCRIPTION = "by Sainsbury's strawberries";

  @Autowired
  ProductDetailsService service;

  @Test
  public void testParseCalories() throws IOException {
    String html = loadHtmlAsString("product-details-calories.html");
    Document document = Jsoup.parse(html);
    Integer calories = service.parseCalories(document);
    assertEquals(CALORIES, calories);
  }

  @Test
  public void testParseCaloriesWhenNotPresent() throws IOException {
    String html = loadHtmlAsString("product-details-no-calories.html");
    Document document = Jsoup.parse(html);
    Integer calories = service.parseCalories(document);
    assertNull(calories);
  }

  @Test
  public void testParseSingleLineDescription() throws IOException {
    String html = loadHtmlAsString("product-details-description.html");
    Document document = Jsoup.parse(html);
    String description = service.parseDescription(document);
    assertEquals(DESCRIPTION, description);
  }

  @Test
  public void testParseMultiLineDescription() throws IOException {
    String html = loadHtmlAsString("product-details-description-multi.html");
    Document document = Jsoup.parse(html);
    String description = service.parseDescription(document);
    assertEquals(DESCRIPTION, description);
  }

}
