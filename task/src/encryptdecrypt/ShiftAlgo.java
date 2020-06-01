package encryptdecrypt;

public class ShiftAlgo implements Algorithm {

    @Override
    public String encrypt(String message, int key) {

        StringBuilder cipher = new StringBuilder();

        for (char c : message.toCharArray()) {

            if (Character.isAlphabetic(c)) {
                int shift = Character.isUpperCase(c) ? 65 : 97;
                cipher.append((char) (moduloOperation(c - shift +
                        key) + shift));
            } else {
                cipher.append(c);
            }
        }

        return cipher.toString();
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, -key);
    }

    private static int moduloOperation(int x) {
        return (x % 26 + 26) % 26;
    }

}
