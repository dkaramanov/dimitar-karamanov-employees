package org.sirma.parsers;

import org.sirma.parsers.base.BaseDateTimeParser;
import org.springframework.stereotype.Component;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;
@Component
public class BasicIsoDate extends BaseDateTimeParser {
    BasicIsoDate() {
        super(BASIC_ISO_DATE);
    }
}
