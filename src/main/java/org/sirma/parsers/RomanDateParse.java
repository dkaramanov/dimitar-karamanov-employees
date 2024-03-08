package org.sirma.parsers;

import org.sirma.parsers.base.DateParser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RomanDateParse implements DateParser {

    public static final int PARTS_COUNT = 3;
    public static final String ROMAN_SEPARATOR = "_";

    @Override
    public LocalDate parse(final String date) {
        final var romanNumbers = date.split(ROMAN_SEPARATOR);
        if (romanNumbers.length != PARTS_COUNT) {
            throw new RuntimeException("Could not parse roman date %s".formatted(date));
        }

        final var month = romanToArabic(romanNumbers[0]);
        final var day = romanToArabic(romanNumbers[1]);
        final var year = romanToArabic(romanNumbers[2]);

        return LocalDate.of(year, month, day);
    }

    private int romanToArabic(final String input) {
        var romanNumeral = input.toUpperCase();
        var result = 0;
        var romanNumerals = RomanNumeral.getReverseSortedValues();
        var i = 0;

        while ((!romanNumeral.isEmpty()) && (i < romanNumerals.size())) {
            final var symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (!romanNumeral.isEmpty()) {
            throw new IllegalArgumentException("%s cannot be converted to a Roman Numeral".formatted(input));
        }

        return result;
    }
}
