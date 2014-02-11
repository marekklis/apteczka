package kit.uc.item.update;

import kit.adapters.userprovider.UserProvider;
import kit.item.Item;
import kit.item.ItemUpdater;
import kit.kit.KitModificationChecker;
import pl.rabbitsoftware.actionpossible.ActionPossible;
import pl.rabbitsoftware.actionpossible.AggregatingActionPossible;

import javax.inject.Inject;
import javax.inject.Named;

import static com.google.common.base.Strings.nullToEmpty;
import static pl.rabbitsoftware.actionpossible.BasicActionPossible.check;
import static pl.rabbitsoftware.actionpossible.Check.checkThat;

@Named
public class UCUpdatingItem {

    @Inject
    private UserProvider userProvider;
    @Inject
    private ItemUpdater itemUpdater;
    @Inject
    private KitModificationChecker kitModificationChecker;

    public void update(String itemId, Item item) {
        checkThat(canUpdate());
        checkThat(canUpdateWithArguments(itemId, item));
        itemUpdater.update(itemId, item);
    }

    private ActionPossible canUpdate() {
        return check(userProvider.provide() != null, "Brak zalogowanego użytkownika");
    }

    public ActionPossible canUpdateWithArguments(String itemId, Item item) {
        ActionPossible kidModifiable = kitModificationChecker.canModify(itemId, userProvider.provide().getId());

        AggregatingActionPossible can = new AggregatingActionPossible();
        can.check(kidModifiable.isPossible(), kidModifiable.explainImpossible());
        can.check(!nullToEmpty(item.getName()).equals(""), "Pole nazwa jest obowiązkowe");
        can.check(item.getCurrentAmount() != null, "Pole aktualna ilość jest obowiązkowe");
        return can;
    }

}
