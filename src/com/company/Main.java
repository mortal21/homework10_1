package com.company;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(final String[] args) {
        final int size = 10;
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        while (!sc.hasNextInt()) {
            System.out.println("Введенное значение не является целым числом");
            System.out.print("Введите целое число: ");
            sc.next();
        }
        int input = sc.nextInt();

        int[] arr1 = getFilledArray(size, input, Integer::sum);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = getFilledArray(size, input, (i, modifier) -> i * modifier);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = getFilledArray(size, input, (i, modifier) -> {
            if (i % 2 == 0) {
                return i / 2 + modifier;
            }
            return i * i - modifier;
        });
        System.out.println(Arrays.toString(arr3));

        // Число = остаток от деления модификтора на индекс+1
        int[] arr4 = getFilledArray(size, input, (i, modifier) -> modifier % (i + 1));
        System.out.println(Arrays.toString(arr4));

        // Число, если индекс меньше половины размера = модификатор - индекс, иначе = модификатор + индекс
        int[] arr5 = getFilledArray(size, input, (i, modifier) -> {
            if (i < size / 2) {
                return modifier - i;
            }
            return modifier + i;
        });
        System.out.println(Arrays.toString(arr5));
        sc.close();
    }

    static int[] getFilledArray(final int size, final int modifier, final Processable p) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = p.process(i, modifier);
        }
        return arr;
    }
}