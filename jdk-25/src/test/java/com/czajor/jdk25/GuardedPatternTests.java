package com.czajor.jdk25;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

class GuardedPatternTests {

  @Test
  void evaluates_color_emojis_correctly() {
    // blue for negative
    String negative = evaluateTempColor(-15.0);
    // green for zero
    String zero = evaluateTempColor(0.0);
    // red for positive
    String positive = evaluateTempColor(25.0);
    assertThat(negative).isEqualTo("\uD83D\uDD35");
    assertThat(zero).isEqualTo("\uD83D\uDFE2");
    assertThat(positive).isEqualTo("\uD83D\uDD34");
  }

  @Test
  void throwsException_whenNullTempProvided() {
    assertThatThrownBy(() -> evaluateTempColor(null)).isNotNull();
  }

  private String evaluateTempColor(@NonNull Double temp) {
    if (temp == null) {
      throw new RuntimeException("Temperature value cannot be empty");
    }
    final String color = switch (temp.compareTo(0.0)) {
      case int t when t < 0 -> "\uD83D\uDD35";
      case int t when t > 0 -> "\uD83D\uDD34";
      default -> "\uD83D\uDFE2";
    };
    System.out.println(color);
    return color;
  }

}

