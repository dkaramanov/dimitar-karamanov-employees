package org.sirma.service;

import org.sirma.data.PairData;
import org.sirma.data.PairKey;
import org.sirma.exception.ParseDateException;
import org.sirma.parsers.base.DateParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PairDataFactory {

    private final List<DateParser> dateParsers;

    public PairDataFactory(List<DateParser> dateParsers) {
        this.dateParsers = dateParsers;
    }

    public PairData parse(final String id1, final String id2, final String beginDate, final String endDate) throws ParseDateException {
        if (id1 == null || id2 == null || beginDate == null) {
            throw new RuntimeException("Invalid row data");
        }

        LocalDate localBeginDate = parseDate(beginDate);
        LocalDate localEndDate = parseDate(endDate);

        if (localBeginDate != null && localEndDate != null && localBeginDate.isAfter(localEndDate)) {
            throw new ParseDateException("begin date %s is after end date %s".formatted(localBeginDate.toString(), localEndDate.toString()));
        }

        return new PairData(new PairKey(id1, id2), localBeginDate, localEndDate);
    }

    private LocalDate parseDate(final String date) throws ParseDateException {
        if (date == null) {
            return null;
        }

        if ("NULL".equals(date.trim())) {
            return LocalDate.now();
        }

        for (DateParser parser: dateParsers) {
            try {
                final var localDate = parser.parse(date.trim());
                if (localDate != null) {
                    return localDate;
                }
            } catch (final Exception e) {
                // Could not parse go next parser
            }
        }
       throw new ParseDateException("Could not parse string %s".formatted(date));
    }
}
