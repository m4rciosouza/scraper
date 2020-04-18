package uk.co.sainsburys.scraper.services.impl;

import org.springframework.stereotype.Service;
import uk.co.sainsburys.scraper.models.Product;
import uk.co.sainsburys.scraper.models.Total;
import uk.co.sainsburys.scraper.services.TotalService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.math.RoundingMode.*;

@Service
public class TotalServiceImpl implements TotalService {

  public static final BigDecimal VAT_PERCENTAGE = new BigDecimal("20");
  public static final BigDecimal HUNDRED = new BigDecimal("100");

  @Override
  public Total calculate(List<Product> products) {
    Optional<BigDecimal> gross = products.stream()
      .map(Product::getUnitPrice)
      .reduce(BigDecimal::add);
    if (!gross.isPresent()) {
      return null;
    }
    BigDecimal vat = gross.get()
      .divide(VAT_PERCENTAGE.add(HUNDRED), 10, HALF_UP)
      .multiply(VAT_PERCENTAGE)
      .setScale(2, HALF_UP);
    return new Total(gross.get(), vat);
  }

}
