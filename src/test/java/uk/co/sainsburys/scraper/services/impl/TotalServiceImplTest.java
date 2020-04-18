package uk.co.sainsburys.scraper.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import uk.co.sainsburys.scraper.models.Product;
import uk.co.sainsburys.scraper.models.Total;
import uk.co.sainsburys.scraper.services.TotalService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class TotalServiceImplTest {

  private static final BigDecimal GROSS_1 = new BigDecimal("1.75");
  private static final BigDecimal GROSS_2 = new BigDecimal("1.35");
  private static final BigDecimal GROSS_3 = new BigDecimal("2.45");
  private static final BigDecimal GROSS_RESULT_SUM = new BigDecimal("5.55");
  private static final BigDecimal VAT_RESULT_1 = new BigDecimal("0.29");
  private static final BigDecimal VAT_RESULT_2 = new BigDecimal("0.93");

  @Autowired
  private TotalService totalService;

  @Test
  public void testCalculateSingleProduct() {
    List<Product> products = singletonList(new Product("1", 1, GROSS_1, "1"));
    Total total = totalService.calculate(products);
    assertEquals(GROSS_1, total.getGross());
    assertEquals(VAT_RESULT_1, total.getVat());
  }

  @Test
  public void testCalculateMultipleProducts() {
    List<Product> products = Arrays.asList(
      new Product("1", 1, GROSS_1, "1"),
      new Product("2", 1, GROSS_2, "2"),
      new Product("3", 1, GROSS_3, "3")
    );
    Total total = totalService.calculate(products);
    assertEquals(GROSS_RESULT_SUM, total.getGross());
    assertEquals(VAT_RESULT_2, total.getVat());
  }

}
