package common;

import java.time.Year;

public final class Constants {
    private Constants() {}

    public static final String VEHICLE_FILE = "vehicle.dat";
    public static final int FIRST_VEHICLE_YEAR = 1886;
    public static final int CURRENT_YEAR = Year.now().getValue();
}
