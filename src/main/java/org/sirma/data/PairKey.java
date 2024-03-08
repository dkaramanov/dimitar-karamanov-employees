package org.sirma.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PairKey {

    private String id1;

    private String id2;

    @Override
    public boolean equals(Object o) {
        if (o instanceof PairKey pairKey) {
            return (id1.equals(pairKey.id1) && id2.equals(pairKey.id2)) ||
                    (id1.equals(pairKey.id2) && id2.equals(pairKey.id1));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id1.compareTo(id2) < 0 ? (id1 + id2).hashCode() : (id2 + id1).hashCode();
    }
}
