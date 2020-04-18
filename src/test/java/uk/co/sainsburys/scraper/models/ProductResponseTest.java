package uk.co.sainsburys.scraper.models;

import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

public class ProductResponseTest {

  @Test
  public void testProductResponseHasTotal() {
    ProductResponse productResponse = getSampleProductResponse();
    assertNotNull(productResponse.getTotal());
  }

  @Test
  public void testProductResponseHasProducts() {
    ProductResponse productResponse = getSampleProductResponse();
    assertNotNull(productResponse.getResults());
  }

  private ProductResponse getSampleProductResponse() {
    Product product = new Product("Title", 50, ONE, "Description");
    return new ProductResponse(singletonList(product), new Total(TEN, ONE));
  }

}
