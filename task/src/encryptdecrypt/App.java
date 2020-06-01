package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {

    private String mode = "enc";
    private String data = "";
    private String inPath = "";
    private String outPath = "";
    private int key = 0;
    private Algorithm algorithm;

    public void execute(String[] args) {
        readArgs(args);
        readData();
        encryptOrDecrypt();
    }

    private void readArgs(String[] args) {

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-alg":
                    if (args[i + 1].equals("shift")) {
                        algorithm = new ShiftAlgo();

                    } else if (args[i + 1].equals("unicode")) {
                        algorithm = new UnicodeAlgo();

                    } else {
                        System.err.println("unknown algorithm");
                        System.exit(1);
                    }
                    break;
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-in":
                    inPath = args[i + 1];
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-out":
                    outPath = args[i + 1];
                    break;
                default:
                    System.err.println("Unknown arguments");
                    System.exit(1);
            }
        }
    }

    private void readData() {

        if (data.equals("")) {
            String fileName = inPath;
            File file = new File(fileName);

            try {
                Scanner scanner = new Scanner(file);
                data = scanner.nextLine();

                scanner.close();

            } catch (FileNotFoundException e) {
                System.err.println(fileName + " not found");
                System.exit(1);
            }
        }
    }

    private void encryptOrDecrypt() {

        switch (mode) {
            case "enc":
                String cipher = algorithm.encrypt(data, key);
                outputFile(cipher, outPath);
                break;
            case "dec":
                String plainText = algorithm.decrypt(data, key);
                outputFile(plainText, outPath);
                break;
            default:
                System.err.println("Available operations are: enc -" +
                        " to encrypt; or dec - to decrypt");
                System.exit(1);
        }
    }

    private static void outputFile(String message, String outPath) {

        if (outPath.equals("")) {
            System.out.println(message);

        } else {
            File file = new File(outPath);

            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(message);
                fileWriter.close();

            } catch (IOException e) {
                System.err.println("Something went wrong with this file: " + outPath);
                System.exit(1);
            }
        }
    }
}
