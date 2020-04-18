package uk.co.sainsburys.scraper.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static org.junit.jupiter.api.Assertions.*;

public class TotalTest {

  private static final BigDecimal GROSS = TEN;
  private static final BigDecimal VAT = ONE;

  @Test
  public void testTotalHasGross() {
    Total total = getSampleTotal();
    assertEquals(GROSS, total.getGross());
  }

  @Test
  public void testTotalHasVat() {
    Total total = getSampleTotal();
    assertEquals(VAT, total.getVat());
  }

  private Total getSampleTotal() {
    return new Total(GROSS, VAT);
  }

}
