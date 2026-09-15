package Utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class AppLogger {
    private static final Logger LOGGER = Logger.getLogger("TraditionalFeastOrderManagement");

    static {
        try {
            FileHandler handler = new FileHandler("application.log", true);
            handler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(handler);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException | SecurityException exception) {
            LOGGER.log(Level.WARNING, "Cannot initialize file logger", exception);
        }
    }

    private AppLogger() {}

    public static void log(String message, Throwable exception) {
        LOGGER.log(Level.SEVERE, message, exception);
    }
}
