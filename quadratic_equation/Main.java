package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
	    try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
	        System.out.println("Введите уравнение в формате ax^2 + b*x + c = 0");
	        System.out.println("Коэффициенты a, b, c - целые числа. Допустимы уравнения вида ax^2 = 0, bx + c = 0 и т.д.");
	        System.out.println("Если коэффициент равен 1 или -1, его можно пропустить");
	        System.out.println("Пробелы в записи уравнения можно пропускать");
	        solve(parse(reader.readLine()));
	    }
	    catch (IOException e) {System.out.println("Не удалось распознать уравнение");}
    }
    static int[] parse(String s) {
        System.out.println("Уравнение: " + s);
        s = s.replaceAll(" ", "");
        s = s.replace("=0", "");
        int[] result = new int[3];
        int k = s.indexOf("x^2");
        if (k == 0) {
            result[0] = 1;
            s = s.substring(3);
        }
        else if (k != -1) {
            String a = s.substring(0, k);
            s = s.substring(k + 3);
            if (a.equals("-")) result[0] = -1;
            else result[0] = Integer.parseInt(a);
        }
        k = s.indexOf("x");
        if (k == 0) {
            result[1] = 1;
            if (s.length() > 2) s = s.substring(2);
            else s = "";
        }
        else if (k != -1){
            String a = s.substring(0, k);
            if (s.length() > k + 1) s = s.substring(k + 1);
            else s = "";
            if (a.equals("-")) result[1] = -1;
            else if (a.equals("+")) result[1] = 1;
            else result[1] = Integer.parseInt(a);
        }
        s = s.replaceAll(" ", "");
        if (!s.equals("")) result[2] = Integer.parseInt(s);
        return result;
    }
    static void solve(int[] data) {
        if (data[0] != 0) {
            int d = data[1] * data[1] - 4 * data[0] * data[2];
            if (d < 0) {
                System.out.println("Нет корней");
            }
            else if (d == 0) {
                double result = (-1.0 * data[1]) / (2 * data[0]);
                System.out.println("Один корень: " + result);
            }
            else {
                double res1 = (-1.0 * data[1] - Math.sqrt(d)) / (2 * data[0]);
                double res2 = (-1.0 * data[1] + Math.sqrt(d)) / (2 * data[0]);
                System.out.println("Два корня: " + res1 + " и " + res2);
            }
        }
        else if (data[1] != 0) {
            double result = (-1.0 * data[2]) / (data[1]);
            if (result == -0.0) result = 0;
            System.out.println("Один корень: " + result);
        }
        else {
            if (data[2] != 0) {
                System.out.println("Нет корней");
            }
            else {
                System.out.println("Бесконечное множество решений");
            }
        }
    }
}
