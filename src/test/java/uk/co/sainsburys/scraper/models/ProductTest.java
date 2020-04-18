package uk.co.sainsburys.scraper.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

  private static final String TITLE = "Sample Title";
  private static final int KCAL_PER_100g = 50;
  private static final BigDecimal UNIT_PRICE = TEN;
  private static final String DESCRIPTION = "Sample Description";

  @Test
  public void testProductHasTitle() {
    Product product = getSampleProduct();
    assertEquals(TITLE, product.getTitle());
  }

  @Test
  public void testProductHasKcalPer100g() {
    Product product = getSampleProduct();
    assertEquals(KCAL_PER_100g, product.getKcalPer100g());
  }

  @Test
  public void testProductHasNullKcalPer100g() {
    Product product = getSampleProductWithoutKcal();
    assertNull(product.getKcalPer100g());
  }

  @Test
  public void testProductHasUnitPrice() {
    Product product = getSampleProduct();
    assertEquals(UNIT_PRICE, product.getUnitPrice());
  }

  @Test
  public void testProductHasDescription() {
    Product product = getSampleProduct();
    assertEquals(DESCRIPTION, product.getDescription());
  }

  private Product getSampleProduct() {
    return new Product(TITLE, KCAL_PER_100g, UNIT_PRICE, DESCRIPTION);
  }

  private Product getSampleProductWithoutKcal() {
    return new Product(TITLE, null, UNIT_PRICE, DESCRIPTION);
  }

}
