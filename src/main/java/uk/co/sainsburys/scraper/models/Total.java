package uk.co.sainsburys.scraper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Total {

  private final BigDecimal gross;
  private final BigDecimal vat;

}
