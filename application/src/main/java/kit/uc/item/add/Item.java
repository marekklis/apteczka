package kit.uc.item.add;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Set;

@Data
public class Item {

    private final String name;
    private final Integer baseAmount;
    private final Integer currentAmount;
    private final DateTime expirationDate;
    private final DateTime openingDate;
    private final Integer expiresAfterOpening;
    private final Set<String> tags;
    private final String note;

}
