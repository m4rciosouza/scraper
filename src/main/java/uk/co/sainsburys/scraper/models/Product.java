package uk.co.sainsburys.scraper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {

  private final String title;
  private final Integer kcalPer100g;
  private final BigDecimal unitPrice;
  private final String description;

}
