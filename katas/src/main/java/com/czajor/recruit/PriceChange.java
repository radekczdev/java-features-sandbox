package com.czajor.recruit;

import java.time.ZonedDateTime;

public record PriceChange(ZonedDateTime validFrom, Integer price) {
}
