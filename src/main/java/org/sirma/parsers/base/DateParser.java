package org.sirma.parsers.base;

import java.time.LocalDate;

public interface DateParser {

    LocalDate parse(String date);
}
