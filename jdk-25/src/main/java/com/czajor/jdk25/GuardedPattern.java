void main() {
  // blue for negative
  evaluateTempColor(-15.0);
  // green for zero
  evaluateTempColor(0.0);
  // red for positive
  evaluateTempColor(25.0);
  evaluateTempColor(null);
}

private String evaluateTempColor(Double temp) {
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