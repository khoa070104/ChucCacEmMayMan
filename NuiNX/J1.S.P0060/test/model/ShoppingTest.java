package model;

import java.util.Arrays;

/**
 * Bộ kiểm thử độc lập cho các chức năng tính tiền và thanh toán của Wallet và Person.
 *
 * @version 29/09/2026
 */
public class ShoppingTest {

    public static void main(String[] args) {
        testCalcTotalNormalCases();
        testCalcTotalEdgeCases();
        testCalcTotalInvalidCases();

        testPayMoneyNormalCases();
        testPayMoneyEdgeCases();
        testPayMoneyInvalidCases();

        testPersonIntegration();
        testPersonInnerWalletClass();

        System.out.println("ShoppingTest: PASSED");
    }

    private static void testCalcTotalNormalCases() {
        Wallet wallet = new Wallet(500);

        // Case 1 trong đặc tả: 2 hóa đơn [100, 200] -> tổng 300
        assertEquals(300, wallet.calcTotal(new int[] {100, 200}), "Total of [100, 200] must be 300");

        // Case 2 trong đặc tả: 2 hóa đơn [200, 200] -> tổng 400
        assertEquals(400, wallet.calcTotal(new int[] {200, 200}), "Total of [200, 200] must be 400");

        // Nhiều hóa đơn
        assertEquals(1000, wallet.calcTotal(new int[] {100, 200, 300, 400}), "Total of [100, 200, 300, 400] must be 1000");

        // 1 hóa đơn
        assertEquals(150, wallet.calcTotal(new int[] {150}), "Total of [150] must be 150");
    }

    private static void testCalcTotalEdgeCases() {
        Wallet wallet = new Wallet();

        // Mảng hóa đơn rỗng -> tổng 0
        assertEquals(0, wallet.calcTotal(new int[] {}), "Empty bills total must be 0");

        // Hóa đơn giá trị 0 -> tổng hợp lệ
        assertEquals(100, wallet.calcTotal(new int[] {0, 100}), "Bills with 0 must sum correctly");
    }

    private static void testCalcTotalInvalidCases() {
        Wallet wallet = new Wallet();

        // Bills là null
        assertIllegalArgument(() -> wallet.calcTotal(null));

        // Hóa đơn có giá trị âm
        assertIllegalArgument(() -> wallet.calcTotal(new int[] {100, -50, 200}));
    }

    private static void testPayMoneyNormalCases() {
        // Ví 500, hóa đơn 300 -> đủ tiền mua
        Wallet wallet1 = new Wallet(500);
        assertTrue(wallet1.payMoney(300), "Wallet 500 should be able to pay 300");

        // Ví 200, hóa đơn 400 -> không đủ tiền mua
        Wallet wallet2 = new Wallet(200);
        assertFalse(wallet2.payMoney(400), "Wallet 200 should not be able to pay 400");

        // Ví 200, hóa đơn 200 -> vừa đủ tiền mua
        Wallet wallet3 = new Wallet(200);
        assertTrue(wallet3.payMoney(200), "Wallet 200 should be able to pay 200");

        // Kiểm tra phương thức static payMoney
        assertTrue(Wallet.payMoney(300, 500), "Static payMoney: 500 should pay 300");
        assertFalse(Wallet.payMoney(400, 200), "Static payMoney: 200 cannot pay 400");
        assertTrue(Wallet.payMoney(200, 200), "Static payMoney: 200 can pay 200");
    }

    private static void testPayMoneyEdgeCases() {
        // Ví 0 đồng, hóa đơn 0 đồng -> thanh toán được
        Wallet walletZero = new Wallet(0);
        assertTrue(walletZero.payMoney(0), "Wallet 0 can pay total 0");

        // Ví 0 đồng, hóa đơn > 0 -> không thanh toán được
        assertFalse(walletZero.payMoney(1), "Wallet 0 cannot pay total 1");
    }

    private static void testPayMoneyInvalidCases() {
        // Tạo ví với số tiền âm
        assertIllegalArgument(() -> new Wallet(-1));

        // Cập nhật số tiền âm cho ví
        Wallet wallet = new Wallet(100);
        assertIllegalArgument(() -> wallet.setAmount(-10));

        // Thanh toán số tiền âm
        assertIllegalArgument(() -> wallet.payMoney(-50));
        assertIllegalArgument(() -> Wallet.payMoney(-10, 100));
        assertIllegalArgument(() -> Wallet.payMoney(100, -10));
    }

    private static void testPersonIntegration() {
        // Person khởi tạo mặc định
        Person person1 = new Person();
        assertEquals(0, person1.getWallet().getAmount(), "Default person wallet should have 0 amount");

        // Person khởi tạo với số tiền ví
        Person person2 = new Person(500);
        assertEquals(500, person2.getWallet().getAmount(), "Person with 500 wallet");
        assertEquals(300, person2.calcTotal(new int[] {100, 200}), "Person calcTotal");
        assertTrue(person2.payMoney(300), "Person can pay 300 with 500 wallet");
        assertFalse(person2.payMoney(600), "Person cannot pay 600 with 500 wallet");

        // Person cập nhật ví tiền
        Wallet newWallet = new Wallet(1000);
        person2.setWallet(newWallet);
        assertEquals(1000, person2.getWallet().getAmount(), "Person updated wallet amount");
        assertTrue(person2.payMoney(600), "Person can pay 600 with updated 1000 wallet");

        // Ngoại lệ khi khởi tạo Person với null wallet
        assertIllegalArgument(() -> new Person((Wallet) null));
        assertIllegalArgument(() -> person2.setWallet(null));
    }

    private static void testPersonInnerWalletClass() {
        // Kiểm thử inner class Person.Wallet
        Person.Wallet innerWallet = new Person.Wallet(350);
        assertEquals(350, innerWallet.getAmount(), "Inner wallet amount must be 350");
        assertTrue(innerWallet.payMoney(300), "Inner wallet can pay 300");
        assertFalse(innerWallet.payMoney(400), "Inner wallet cannot pay 400");
        assertEquals(300, innerWallet.calcTotal(new int[] {100, 200}), "Inner wallet calcTotal");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " - Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("Assertion failed: " + message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError("Assertion failed (expected false): " + message);
        }
    }

    private static void assertIllegalArgument(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException expected) {
            // Đạt kết quả mong đợi
        }
    }
}
