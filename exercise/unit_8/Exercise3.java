package exercise.unit_8;

import java.util.NoSuchElementException;

public class Exercise3 {
    public static void main(String[] args) {
        RomanNumbers test = new RomanNumbers("MCMXCV");
        RomanNumbers test2 = new RomanNumbers(1995);

        test2.decimalToRomanNumber();
        if (test2.getRomanNumberString().equals(test.getRomanNumberString())) {
            System.out.println(true);
        }

    }
}

class RomanNumbers {
    private static final String[] symbols = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
    private static final int[] numbers = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };

    private String romanNumberString;
    private int romanNumber;

    public RomanNumbers(String romanNumberString) {
        this.romanNumberString = romanNumberString;
        this.romanNumber = 0;
    }

    public RomanNumbers(int romanNumber) {
        this.romanNumberString = "";
        this.romanNumber = romanNumber;
    }

    public String getRomanNumberString() {
        romanNumberStringCondition(romanNumberString);
        return romanNumberString;
    }

    public int getRomanNumber() {
        romanNumberCondition(romanNumber);
        return romanNumber;
    }

    private void setRomanNumberString(String input) {
        romanNumberStringCondition(input);
        this.romanNumberString = input;
    }

    private void romanNumberStringCondition(String str) {
        if (str.equals("")) {
            throw new NoSuchElementException("적절한 로마 숫자를 찾을 수 없습니다.");
        }
    }

    private void romanNumberCondition(int number) {
        if (number == 0) {
            throw new NoSuchElementException("적절한 로마 숫자를 찾을 수 없습니다.");
        }
    }

    public void decimalToRomanNumber() {
        StringBuilder builder = new StringBuilder();
        int number = getRomanNumber();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (number >= numbers[i]) {
                builder.append(symbols[i]);
                number -= numbers[i];
            }
        }

        setRomanNumberString(builder.toString());
    }
}
