package com.example.task02;

public class Task02 {
    public static String solution(String input) {
        long var = Long.parseLong(input);

        if (var >= Byte.MIN_VALUE && var <= Byte.MAX_VALUE) {
            return "byte";
        } else if (var >= Short.MIN_VALUE && var <= Short.MAX_VALUE) {
            return "short";
        } else if (var >= Integer.MIN_VALUE && var <= Integer.MAX_VALUE) {
            return "int";
        } else {
            return "long";
        }
    }

    public static void main(String[] args) {
        // Здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:
        /*
        String result = solution("12345");
        System.out.println(result);
         */
    }
}
