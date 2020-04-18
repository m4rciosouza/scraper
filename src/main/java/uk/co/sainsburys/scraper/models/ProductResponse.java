package uk.co.sainsburys.scraper.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductResponse {

  private final List<Product> results;
  private final Total total;

}
