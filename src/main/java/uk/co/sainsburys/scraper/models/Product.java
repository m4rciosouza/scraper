package uk.co.sainsburys.scraper.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "title", "kcal_per_100g", "unit_price", "description" })
public class Product {

  private final String title;

  @JsonProperty("kcal_per_100g")
  private final Integer kcalPer100g;

  @JsonProperty("unit_price")
  private final BigDecimal unitPrice;

  private final String description;

}
