package com.easymail.customSupportLibraries;

import java.util.Scanner;

public class GetNumber {
    // get byte value with constraints
    public static byte getByte(byte constraint1, byte constraint2) {
        var input =  new Scanner(System.in);
        byte byteValue;
        while (!input.hasNextByte()) {
            System.out.print("Invalid input, Enter again: ");
            input.nextLine();
        }
        byteValue = input.nextByte();
        if (byteValue < constraint1 || byteValue > constraint2) {
            System.out.print("Invalid input, Enter again: ");
            byteValue = GetNumber.getByte(constraint1, constraint2);
        }
        return byteValue;
    }

    public static int getNormalInt() {
        var input = new Scanner(System.in);
        return input.nextInt();
    }
}
