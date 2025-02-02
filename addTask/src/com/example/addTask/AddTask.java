package com.example.addTask;

import java.util.ArrayList;
import java.util.List;

public class AddTask {
    static final int TARGET = 100;

    public static List<String> findExpressions(String digits) {
        List<String> results = new ArrayList<>();
        generateExpressions(digits, 0, "", 0, 0, results);
        return results;
    }

    private static void generateExpressions(String digits, int pos, String expr, long currValue, long lastValue, List<String> results) {
        if (pos == digits.length()) {
            if (currValue == TARGET) {
                results.add(expr);
            }
            return;
        }

        for (int i = pos; i < digits.length(); i++) {
            String numStr = digits.substring(pos, i + 1);
            long num = Long.parseLong(numStr);

            if (pos == 0) {
                generateExpressions(digits, i + 1, numStr, num, num, results);
            } else {
                generateExpressions(digits, i + 1, expr + "+" + numStr, currValue + num, num, results);
                generateExpressions(digits, i + 1, expr + "-" + numStr, currValue - num, -num, results);
                generateExpressions(digits, i + 1, expr + "*" + numStr, currValue - lastValue + lastValue * num, lastValue * num, results);
                if (num != 0 && lastValue % num == 0) {
                    generateExpressions(digits, i + 1, expr + "/" + numStr, currValue - lastValue + lastValue / num, lastValue / num, results);
                }
            }

            if (digits.charAt(pos) == '0' && i > pos) {
                break;
            }
        }
    }
}