package com.example.addTask;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class AddTaskTest {

    @Test
    public void testFindExpressions() {
        String digits = "98765432";
        List<String> expressions = AddTask.findExpressions(digits);

        // Проверяем, что список не пуст
        assertNotNull(expressions);
        assertFalse(expressions.isEmpty());

        // Проверяем, что каждое выражение корректно вычисляется в 100
        for (String expr : expressions) {
            assertTrue(evaluateExpression(expr) == 100);
        }
    }

    @Test
    public void testNoSolution() {
        String digits = "200000002"; // Невозможно получить 100
        List<String> expressions = AddTask.findExpressions(digits);
        System.out.println("Найденные выражения: " + expressions);
        assertTrue(expressions.isEmpty());
    }

    @Test
    public void testEdgeCaseSingleDigit() {
        String digits = "1"; // Единственная цифра, 100 не получить
        List<String> expressions = AddTask.findExpressions(digits);
        assertTrue(expressions.isEmpty());
    }

    private long evaluateExpression(String expr) {
        return new Object() {
            int pos = -1, ch;

            long parse() {
                nextChar();
                long x = parseExpression();
                if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            long parseExpression() {
                long x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            long parseTerm() {
                long x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            long parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();
                long x;
                int startPos = this.pos;
                if ((ch >= '0' && ch <= '9')) {
                    while (ch >= '0' && ch <= '9') nextChar();
                    x = Long.parseLong(expr.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }
}
