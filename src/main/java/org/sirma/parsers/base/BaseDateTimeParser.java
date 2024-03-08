package org.sirma.parsers.base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class BaseDateTimeParser implements DateParser {

    private final DateTimeFormatter formatter;

    public BaseDateTimeParser(final DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
    @Override
    public final LocalDate parse(final String date) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (final Exception e) {
            return null;
        }
    }
}
