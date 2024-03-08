package org.sirma.parsers;

import org.sirma.parsers.base.BaseDateTimeParser;
import org.springframework.stereotype.Component;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

@Component
public class IsoLocalDateParse extends BaseDateTimeParser {
    public IsoLocalDateParse() {
        super(ISO_LOCAL_DATE);
    }
}
