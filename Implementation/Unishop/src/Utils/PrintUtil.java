package Utils;

public final class PrintUtil {

    public static void welcomeMessage(String message) {
        int messageLength = message.length();

        // Print top border
        printBorder(messageLength + 4);

        // Print welcome message with borders
        System.out.println("## " + message + " ##");

        // Print bottom border
        printBorder(messageLength + 4);
    }

    private static void printBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
}
