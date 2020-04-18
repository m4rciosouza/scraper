package uk.co.sainsburys.scraper.services;

import uk.co.sainsburys.scraper.models.Product;
import uk.co.sainsburys.scraper.models.Total;

import java.util.List;

public interface TotalService {

  Total calculate(List<Product> products);

}
