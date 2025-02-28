/*
 * This source file was generated by the Gradle 'init' task
 */
package com.example.lab1;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String outputPath = "";
        String prefix = "";
        boolean isFull = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (i + 1 < args.length) {
                        outputPath = args[i + 1];
                        i++;
                    } else {
                        System.out.println("Ошибка: Не указано значение для -o");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[i + 1];
                        i++;
                    } else {
                        System.out.println("Ошибка: Не указано значение для -p");
                    }
                    break;
                case "-s":
                    isFull = false;
                    break;
                case "-f":
                    isFull = true;
                    break;
                default:
                    System.out.println("Неизвестный аргумент: " + args[i]);
                    break;
            }
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of incoming files: ");
        int numberOfFiles = scanner.nextInt();
        scanner.nextLine();
        String[] filePaths = new String[numberOfFiles];
        System.out.println("Enter the file paths:");
        for (int i = 0; i < numberOfFiles; i++) {
            filePaths[i] = scanner.nextLine();
        }
        String line;
        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        long countInts = 0;
        long countDoubles = 0;
        long countStrings = 0;
        long sumInts = 0;
        int maxInt = Integer.MIN_VALUE;
        int minInt = Integer.MAX_VALUE;
        double sumDoubles = 0;
        double maxDouble = Double.MIN_VALUE;
        double minDouble = Double.MAX_VALUE;
        int maxStringLength = Integer.MIN_VALUE;
        int minStringLength = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfFiles; i++) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePaths[i]))) {
                while ((line = br.readLine()) != null) {
                    if (isInteger(line)) {
                        int temp = Integer.parseInt(line);
                        ints.add(temp);
                        if (temp > maxInt) {
                            maxInt = temp;
                        }
                        if (temp < minInt) {
                            minInt = temp;
                        }
                        countInts++;
                        sumInts += temp;
                    } else if (isDouble(line)) {
                        double temp = Double.parseDouble(line);
                        doubles.add(temp);
                        if (temp > maxDouble) {
                            maxDouble = temp;
                        }
                        if (temp < minDouble) {
                            minDouble = temp;
                        }
                        countDoubles++;
                    } else {
                        strings.add(line);
                        int length = line.length();
                        if (length > maxStringLength) {
                            maxStringLength = length;
                        }
                        if (length < minStringLength) {
                            minStringLength = length;
                        }
                        countStrings++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String defaultIntOutFilePath = "integers.txt";
        String defaultDoubleOutFilePath = "floats.txt";
        String defaultStringOutFilePath = "strings.txt";
        String finalIntOutFilePath;
        String finalDoubleOutFilePath;
        String finalStringOutFilePath;
        if (outputPath != "") {
            finalIntOutFilePath = outputPath + "/" + prefix + defaultIntOutFilePath;
            finalDoubleOutFilePath = outputPath + "/" + prefix + defaultDoubleOutFilePath;
            finalStringOutFilePath = outputPath + "/" + prefix + defaultStringOutFilePath;
        } else {
            finalIntOutFilePath = prefix + defaultIntOutFilePath;
            finalDoubleOutFilePath = prefix + defaultDoubleOutFilePath;
            finalStringOutFilePath = prefix + defaultStringOutFilePath;
        }
        if (!ints.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalIntOutFilePath))) {
                for (Integer number : ints) {
                    writer.write(number.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!doubles.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalDoubleOutFilePath))) {
                for (Double number : doubles) {
                    writer.write(number.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!strings.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalStringOutFilePath))) {
                for (String number : strings) {
                    writer.write(number.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (isFull) {
            System.out.println("Full statistics:\n");
            if (countInts != 0) {
                System.out.println("Integers");
                System.out.println("Quantity: " + countInts);
                System.out.println("Min: " + minInt);
                System.out.println("Max: " + maxInt);
                System.out.println("Sum: " + sumInts);
                double average = (double) sumInts / countInts;
                System.out.printf("Average: %.2f%n", average);
                System.out.println();
            } else {
                System.out.println("No integers\n");
            }
            if (countDoubles != 0) {
                System.out.println("Floats");
                System.out.println("Quantity: " + countDoubles);
                System.out.println("Min: " + minDouble);
                System.out.println("Max: " + maxDouble);
                System.out.println("Sum: " + sumDoubles);
                double average = (double) sumDoubles / countDoubles;
                System.out.printf("Average: %.2f%n", average);
                System.out.println();
            } else {
                System.out.println("No floats\n");
            }
            if (countStrings != 0) {
                System.out.println("Strings");
                System.out.println("Quantity: " + countStrings);
                System.out.println("Min length: " + minStringLength);
                System.out.println("Max length: " + maxStringLength);
                System.out.println();
            } else {
                System.out.println("No strings\n");
            }
        } else {
            System.out.println("Partial statistics:\n");
            if (countInts != 0) {
                System.out.println("Integers");
                System.out.println("Quantity: " + countInts);
                System.out.println();
            } else {
                System.out.println("No integers\n");
            }
            if (countDoubles != 0) {
                System.out.println("Floats");
                System.out.println("Quantity: " + countDoubles);
                System.out.println();
            } else {
                System.out.println("No floats\n");
            }
            if (countStrings != 0) {
                System.out.println("Strings");
                System.out.println("Quantity: " + countStrings);
                System.out.println();
            } else {
                System.out.println("No strings\n");
            }
        }
    }
}
