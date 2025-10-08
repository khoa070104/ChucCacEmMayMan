package enums;

public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Day getDay(String day) {
        try {
            return Day.valueOf(day.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
