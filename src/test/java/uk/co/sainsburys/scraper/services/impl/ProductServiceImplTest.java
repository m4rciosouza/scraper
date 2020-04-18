package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.co.sainsburys.scraper.services.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.co.sainsburys.scraper.utils.ResourceUtils.loadHtmlAsString;

@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceImplTest {

  private static final String TITLE = "Sainsbury's Strawberries 400g";
  private static final String URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
  private static final BigDecimal UNIT_PRICE = new BigDecimal("1.75");
  public static final int TOTAL_ENTRIES = 17;
  private static Document document;

  @Autowired
  public ProductService service;

  @BeforeAll
  public static void setup() throws IOException {
    document = Jsoup.parse(loadHtmlAsString("product-list.html"));
  }

  @Test
  public void testParseTitle() {
    List<String> titles = service.parseTitles(document);
    assertEquals(TOTAL_ENTRIES, titles.size());
    assertEquals(TITLE, titles.get(0));
  }

  @Test
  public void testParseDetailsUrls() {
    List<String> urls = service.parseDetailsUrls(document);
    assertEquals(TOTAL_ENTRIES, urls.size());
    assertEquals(URL, urls.get(0));
  }

  @Test
  public void testParseUnitPrices() {
    List<BigDecimal> unitPrices = service.parseUnitPrices(document);
    assertEquals(TOTAL_ENTRIES, unitPrices.size());
    assertEquals(UNIT_PRICE, unitPrices.get(0));
  }

}
