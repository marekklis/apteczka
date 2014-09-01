package kit.uc.item.listing;

import kit.item.Item;
import kit.user.UserPermissions;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import static pl.rabbitsoftware.actionpossible.Check.checkThat;

@Named
public class UCListingKitItems {

    @Inject
    private UserPermissions userPermissions;

    public List<Item> items(String kitId) {
        checkThat(userPermissions.canListKit(kitId));
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Chlorchinaldin", 90, 45, DateTime.now().plusDays(180), DateTime.now().minusDays(10), null, null, ""));
        items.add(new Item("Polopiryna S", 10, 5, DateTime.now().plusDays(5), DateTime.now().minusDays(100), null, null, ""));
        items.add(new Item("Aspiryna", 10, 5, DateTime.now().minusDays(5), DateTime.now().minusDays(100), null, null, ""));
        return items;
    }
}
