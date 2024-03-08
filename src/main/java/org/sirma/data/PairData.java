package org.sirma.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PairData {

    private PairKey pairKey;

    private LocalDate bDate;

    private LocalDate eDate;

    public boolean equals(Object o) {
        if (o instanceof PairData data) {
            return pairKey.equals(data.pairKey);
        }
        return false;
    }

    public String toString() {
        return "id1 = " + pairKey.getId1() + " id2 = " + pairKey.getId2() + " bDate = " + (bDate == null ? "" : bDate.toString()) + " eDate = " + (eDate == null ? "" : eDate.toString());
    }
}
