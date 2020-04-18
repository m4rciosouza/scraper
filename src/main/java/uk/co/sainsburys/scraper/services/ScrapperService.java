package uk.co.sainsburys.scraper.services;

import uk.co.sainsburys.scraper.models.ProductResponse;

import java.io.IOException;

public interface ScrapperService {

  ProductResponse execute() throws IOException;

}
