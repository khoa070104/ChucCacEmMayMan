package J1.S.P0009.utils;

import J1.S.P0009.constants.Message;

public final class Validation {

    // Chan khong cho tao object
    private Validation() {
    }

    // Kiem tra va chuyen doi chuoi sang so nguyen duong
    public static int getPositiveInt(String input) throws Exception {
        try {
            int number = Integer.parseInt(input);

            if (number <= 0) {
                throw new Exception(Message.POSITIVE_NUMBER);
            }

            return number;
        } catch (NumberFormatException e) {
            throw new Exception(Message.INVALID_NUMBER);
        }
    }
}
