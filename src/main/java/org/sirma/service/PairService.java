package org.sirma.service;

import org.sirma.data.PairData;
import org.sirma.data.PairDataResult;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PairService {

    public PairDataResult findLongTimePair(final List<PairData> data) {
        final var pairDataResultStream = data.stream().map(d -> new PairDataResult(d.getPairKey(), ChronoUnit.DAYS.between(d.getBDate(), d.getEDate()))).collect(Collectors.groupingBy(PairDataResult::getPairKey));

        List<PairDataResult> pairs = new ArrayList<>();
        pairDataResultStream.keySet().forEach(key -> pairs.add(new PairDataResult(key, sumOf(pairDataResultStream.get(key)))));

        pairs.forEach(System.out::println);
        System.out.println("+----------------------------------------+");
        final var optional = pairs.stream().max(Comparator.comparingLong(PairDataResult::getDays));
        return optional.orElse(null);
    }

    private long sumOf(List<PairDataResult> pairDataResults) {
        AtomicLong sum = new AtomicLong();
        pairDataResults.forEach(i -> sum.addAndGet(i.getDays()));
        return sum.get();
    }
}
