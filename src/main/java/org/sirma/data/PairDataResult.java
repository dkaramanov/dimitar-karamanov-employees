package org.sirma.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PairDataResult {

    private PairKey pairKey;

    private long days;

    @Override
    public boolean equals(Object o) {
        if (o instanceof PairDataResult data) {
            return pairKey.equals(data.pairKey);
        }
        return false;
    }
}
