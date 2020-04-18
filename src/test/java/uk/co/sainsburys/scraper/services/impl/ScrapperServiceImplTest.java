package uk.co.sainsburys.scraper.services.impl;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import uk.co.sainsburys.scraper.models.Product;
import uk.co.sainsburys.scraper.models.ProductResponse;
import uk.co.sainsburys.scraper.models.Total;
import uk.co.sainsburys.scraper.services.ProductDetailsService;
import uk.co.sainsburys.scraper.services.ProductService;
import uk.co.sainsburys.scraper.services.ScrapperService;
import uk.co.sainsburys.scraper.services.TotalService;
import uk.co.sainsburys.scraper.utils.JsoupService;

import java.io.IOException;
import java.math.BigDecimal;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class ScrapperServiceImplTest {

  private static final BigDecimal GROSS = new BigDecimal("1.75");
  private static final BigDecimal VAT = new BigDecimal("0.35");
  private static final String TITLE = "Sainsbury's Strawberries 400g";
  private static final BigDecimal UNIT_PRICE = new BigDecimal("1.75");
  private static final Integer CALORIES = 33;
  private static final String DESCRIPTION = "by Sainsbury's strawberries";

  @Autowired
  private ScrapperService service;

  @MockBean
  private JsoupService jsoupService;

  @MockBean
  private ProductService productService;

  @MockBean
  private TotalService totalService;

  @MockBean
  private ProductDetailsService productDetailsService;

  @BeforeEach
  public void setup() throws IOException {
    when(jsoupService.getDocument(anyString())).thenReturn(new Document(""));
    when(productService.parseTitles(any())).thenReturn(singletonList(TITLE));
    when(productService.parseUnitPrices(any(Document.class))).thenReturn(singletonList(UNIT_PRICE));
    when(productService.parseDetailsUrls(any(Document.class))).thenReturn(singletonList(""));
    when(productDetailsService.parseCalories(any(Document.class))).thenReturn(CALORIES);
    when(productDetailsService.parseDescription(any(Document.class))).thenReturn(DESCRIPTION);
    when(totalService.calculate(anyList())).thenReturn(new Total(GROSS, VAT));
  }

  @Test
  public void testProductsResponseValues() throws IOException {
    ProductResponse response = service.execute();
    Product product = response.getResults().get(0);
    assertEquals(1, response.getResults().size());
    assertEquals(TITLE, product.getTitle());
    assertEquals(CALORIES, product.getKcalPer100g());
    assertEquals(UNIT_PRICE, product.getUnitPrice());
    assertEquals(DESCRIPTION, product.getDescription());
  }

  @Test
  public void testGrossAndVatResponseValues() throws IOException {
    ProductResponse response = service.execute();
    assertEquals(GROSS, response.getTotal().getGross());
    assertEquals(VAT, response.getTotal().getVat());
  }

}
