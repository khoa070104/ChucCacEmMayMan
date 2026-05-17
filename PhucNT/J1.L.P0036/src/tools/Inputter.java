package tools;

import java.util.Scanner;

public class Inputter {

    private final Scanner ndl;

    public Inputter() {
        this.ndl = new Scanner(System.in);
    }

    public String getString(String mess) {
        System.out.print(mess);
        return ndl.nextLine().trim();
    }

    public int getInt(String mess) {
        String value;
        do {
            value = getString(mess);
        } while (!Acceptable.isValid(value, Acceptable.INTEGER_VALID));
        return Integer.parseInt(value);
    }

    public double getDouble(String mess) {
        String value;
        do {
            value = getString(mess);
        } while (!Acceptable.isPositiveReal(value));
        return Double.parseDouble(value);
    }

    public String inputAndLoop(String mess, String pattern) {
        String data;
        do {
            data = getString(mess);
            if (!Acceptable.isValid(data, pattern)) {
                System.out.println("Invalid data. Please re-enter.");
            }
        } while (!Acceptable.isValid(data, pattern));
        return data;
    }

    public String inputPositiveReal(String mess) {
        String data;
        do {
            data = getString(mess);
            if (!Acceptable.isPositiveReal(data)) {
                System.out.println("Invalid data. Please re-enter.");
            }
        } while (!Acceptable.isPositiveReal(data));
        return data;
    }

    public String inputOptional(String mess, String pattern) {
        String data;
        while (true) {
            data = getString(mess);
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isValid(data, pattern)) {
                return data;
            }
            System.out.println("Invalid data. Please re-enter or leave blank to skip.");
        }
    }

    public String inputOptionalPositiveReal(String mess) {
        String data;
        while (true) {
            data = getString(mess);
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isPositiveReal(data)) {
                return data;
            }
            System.out.println("Invalid data. Please re-enter or leave blank to skip.");
        }
    }

    public String inputOptionalPosition() {
        String data;
        while (true) {
            data = getString("New position (blank to skip): ");
            if (data.isEmpty()) {
                return "";
            }
            if (Acceptable.isValidPosition(data)) {
                return Acceptable.normalizePosition(data);
            }
            System.out.println("Invalid position. Please re-enter or leave blank to skip.");
        }
    }

    public String inputPosition() {
        String data;
        do {
            data = getString("Position [Goalkeeper/Defender/Midfielder/Forward/Winger]: ");
            if (!Acceptable.isValidPosition(data)) {
                System.out.println("Invalid position. Please re-enter.");
            }
        } while (!Acceptable.isValidPosition(data));
        return Acceptable.normalizePosition(data);
    }

}
