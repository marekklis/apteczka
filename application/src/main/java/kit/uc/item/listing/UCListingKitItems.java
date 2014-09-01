package kit.uc.item.listing;

import kit.item.Item;
import kit.kit.KitItemsQuery;
import kit.user.UserPermissions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static pl.rabbitsoftware.actionpossible.Check.checkThat;

@Named
public class UCListingKitItems {

    @Inject
    private UserPermissions userPermissions;
    @Inject
    private KitItemsQuery kitItemsQuery;

    public List<Item> items(String kitId) {
        checkThat(userPermissions.canListKit(kitId));
        return kitItemsQuery.items(kitId);
    }
}
