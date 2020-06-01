package encryptdecrypt;

public class UnicodeAlgo implements Algorithm {

    @Override
    public String encrypt(String message, int key) {

        StringBuilder cipher = new StringBuilder();

        for (char c : message.toCharArray()) {
            cipher.append((char) (c + key));
        }

        return cipher.toString();
    }

    @Override
    public String decrypt(String message, int key) {

        return encrypt(message, -key);
    }
}
