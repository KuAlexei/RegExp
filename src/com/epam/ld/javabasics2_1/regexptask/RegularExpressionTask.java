package com.epam.ld.javabasics2_1.regexptask;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class RegularExpressionTask {

    public static void main(String[] args) {
        // На '.', символы
        // Дана строка 'ahb acb aeb aeeb adcb axeb'. Напишите регулярку, которая найдет строки ahb, acb, aeb по шаблону: буква 'a', любой символ, буква 'b'.
        assertArrayEquals(new String [] {"ahb", "acb", "aeb"}, findAll("ahb acb aeb aeeb adcb axeb", "a.b"));
        // Дана строка 'aba aca aea abba adca abea'. Напишите регулярку, которая найдет строки abba adca abea по шаблону: буква 'a', 2 любых символа, буква 'a'.
        assertArrayEquals(new String [] {"abba", "adca", "abea"}, findAll("aba aca aea abba adca abea", "a..a"));
        // Дана строка 'aba aca aea abba adca abea'. Напишите регулярку, которая найдет строки abba и abea, не захватив adca.
        assertArrayEquals(new String [] {"abba", "abea"}, findAll("aba aca aea abba adca abea", "ab.a"));

        // На '+', '*', '?', ()
        // Дана строка 'aa aba abba abbba abca abea'. Напишите регулярку, которая найдет строки aba, abba, abbba по шаблону: буква 'a', буква 'b' любое количество раз, буква 'a'.
        assertArrayEquals(new String [] {"aba", "abba", "abbba"}, findAll("aa aba abba abbba abca abea", "ab+a"));
        // Дана строка 'aa aba abba abbba abca abea'. Напишите регулярку, которая найдет строки aa, aba, abba, abbba по шаблону: буква 'a', буква 'b' любое количество раз (в том числе ниодного раза), буква 'a'.
        assertArrayEquals(new String [] {"aa", "aba", "abba", "abbba"}, findAll("aa aba abba abbba abca abea", "ab*a"));
        // Дана строка 'aa aba abba abbba abca abea'. Напишите регулярку, которая найдет строки aa, aba по шаблону: буква 'a', буква 'b' один раз или ниодного, буква 'a'.
        assertArrayEquals(new String [] {"aa", "aba"}, findAll("aa aba abba abbba abca abea", "ab?a"));
        // Дана строка 'aa aba abba abbba abca abea'. Напишите регулярку, которая найдет строки aa, aba, abba, abbba, не захватив abca abea.
        assertArrayEquals(new String [] {"aa", "aba", "abba", "abbba"}, findAll("aa aba abba abbba abca abea", "ab*a"));
        // Дана строка 'ab abab abab abababab abea'. Напишите регулярку, которая найдет строки по шаблону: строка 'ab' повторяется 1 или более раз.
        assertArrayEquals(new String [] {"ab", "abab", "abab", "abababab"}, findAll("ab abab abab abababab abea", "\\b(ab)+\\b"));

        // На экранировку
        // Дана строка 'a.a aba aea'. Напишите регулярку, которая найдет строку a.a, не захватив остальные.
        assertArrayEquals(new String [] {"a.a"}, findAll("a.a aba aea", "a\\.a"));
        // Дана строка '2+3 223 2223'. Напишите регулярку, которая найдет строку 2+3, не захватив остальные.
        assertArrayEquals(new String [] {"2+3"}, findAll("2+3 223 2223", "2\\+3"));
        // Дана строка '23 2+3 2++3 2+++3 345 567'. Напишите регулярку, которая найдет строки 2+3, 2++3, 2+++3, не захватив остальные (+ может быть любое количество).
        assertArrayEquals(new String [] {"2+3", "2++3", "2+++3"}, findAll("23 2+3 2++3 2+++3 345 567", "2\\++3"));
        // Дана строка '23 2+3 2++3 2+++3 445 677'. Напишите регулярку, которая найдет строки 23, 2+3, 2++3, 2+++3, не захватив остальные.
        assertArrayEquals(new String [] {"23", "2+3", "2++3", "2+++3"}, findAll("23 2+3 2++3 2+++3 445 677", "2\\+*3"));
        // Дана строка '*+ *q+ *qq+ *qqq+ *qqq qqq+'. Напишите регулярку, которая найдет строки *q+, *qq+, *qqq+, не захватив остальные.
        assertArrayEquals(new String [] {"*q+", "*qq+", "*qqq+"}, findAll("*+ *q+ *qq+ *qqq+ *qqq qqq+", "\\*q+\\+"));

        // На жадность
        // Дана строка 'aba accca azzza wwwwa'. Напишите регулярку, которая найдет все строки по краям которых стоят буквы 'a', и заменит каждую из них на '!'. Между буквами a может быть любой символ (кроме a).
        assertEquals("!b! !ccc! !zzz! wwwwa", "aba accca azzza wwwwa".replaceAll("\\ba([^a]+)a\\b", "!$1!"));
    }

    private static String[] findAll(String s, String regexp) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list.toArray(new String[0]);
    }


}
