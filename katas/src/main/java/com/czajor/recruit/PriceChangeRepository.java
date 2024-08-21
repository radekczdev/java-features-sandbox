package com.czajor.recruit;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Data;

@Data
public class PriceChangeRepository {

  List<PriceChange> getPriceChanges() {
    return List.of(
        new PriceChange(
            ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
            10),
        new PriceChange(
            ZonedDateTime.of(2024, 3, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
            30),
        new PriceChange(
            ZonedDateTime.of(2024, 5, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
            25),
        new PriceChange(
            ZonedDateTime.of(2024, 7, 1, 0, 0, 0, 0, ZoneId.systemDefault()),
            60)
    );
  }
}
