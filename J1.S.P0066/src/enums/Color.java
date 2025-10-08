package enums;

public enum Color {
    WHITE, YELLOW, ORANGE, GREEN, BLUE, PURPLE, PINK, RED, BROWN, NO_COLOR;

    public static Color getColor(String color) {
        try {
            if (color.equalsIgnoreCase("no color")) {
                return NO_COLOR;
            }
            return Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
