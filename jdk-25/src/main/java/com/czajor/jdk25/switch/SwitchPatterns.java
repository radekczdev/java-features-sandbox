void main() {
    Object obj = "Hello, World!";
    String result = switch (obj) {
        case Integer i -> "It's an Integer: " + i;
        case String s when s.length() > 5 -> "It's a long String: " + s;
        case String s -> "It's a short String: " + s;
        case null -> "It's null";
        default -> "Unknown type";
    };
    System.out.println(result);
}