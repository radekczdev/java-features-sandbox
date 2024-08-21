package com.czajor.recruit;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PriceChangeRunner {
  public static void main(String[] args) {
    PriceChangeService priceChangeService = new PriceChangeService();
    final var start = ZonedDateTime.of(2024, 6,1,0,0,0,0, ZoneId.systemDefault());
    final var end = ZonedDateTime.of(2024, 8,25,0,0,0,0, ZoneId.systemDefault());
    var lowestPrice = priceChangeService.getLowestPriceForPeriod(start,end);
    assert lowestPrice == 25;
  }
}
