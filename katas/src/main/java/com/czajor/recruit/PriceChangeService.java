package com.czajor.recruit;

import java.time.ZonedDateTime;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceChangeService {
  private final PriceChangeRepository priceChangeRepository = new PriceChangeRepository();

  Integer getLowestPriceForPeriod(ZonedDateTime start, ZonedDateTime end) {
    priceChangeRepository.getPriceChanges().sort(Comparator.comparing(PriceChange::validFrom));

    var pricesBefore = priceChangeRepository.getPriceChanges()
        .stream()
        .filter(pc -> pc.validFrom().isBefore(start) || pc.validFrom().isEqual(start))
        .toList();

    var lastPriceChange = pricesBefore.getLast();

    return priceChangeRepository.getPriceChanges()
        .stream()
        .filter(pc -> (pc.validFrom().isEqual(lastPriceChange.validFrom()) || pc.validFrom().isAfter(lastPriceChange.validFrom()))
            && (pc.validFrom().isBefore(end) || pc.validFrom().isEqual(end)))
        .map(PriceChange::price)
        .min(Comparator.naturalOrder()).orElseThrow();
  }
}
