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
        s = s.replaceAll("-", "+-");
        s = s.replaceAll("\\+x", "+1x");
        s = s.replaceAll("-x", "-1x");
        s = s.replaceAll("=", "+=+");
        int[] result = new int[3];
        boolean isLeft = true;
        for (String cur: s.split("[\\+]")) {
            if (cur.equals("=")) {
                isLeft = false;
                continue;
            }
            if (cur.equals("")) continue;
            int index = getIndex(cur);
            int value = getValue(cur);
            if (isLeft) result[index] += value;
            else result[index] -= value;
        }
        return result;
    }

    static int getIndex(String s) {
        if (s.contains("x^2")) return 0;
        if (s.contains("x")) return 1;
        return 2;
    }

    static int getValue(String s) throws NumberFormatException {
        if (s.indexOf("x") == 0) return 1;
        if (s.indexOf("x") == -1) return Integer.parseInt(s);
        String number = s.substring(0, s.indexOf("x"));
        return Integer.parseInt(number);
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
