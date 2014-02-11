package kit.uc.item.add;

import kit.adapters.userprovider.UserProvider;
import kit.item.Item;
import kit.item.ItemInserter;
import kit.kit.KitModificationChecker;
import pl.rabbitsoftware.actionpossible.ActionPossible;
import pl.rabbitsoftware.actionpossible.AggregatingActionPossible;

import javax.inject.Inject;
import javax.inject.Named;

import static com.google.common.base.Strings.nullToEmpty;
import static pl.rabbitsoftware.actionpossible.BasicActionPossible.check;
import static pl.rabbitsoftware.actionpossible.Check.checkThat;

@Named
public class UCAddingItem {

    @Inject
    private UserProvider userProvider;
    @Inject
    private ItemInserter itemInserter;
    @Inject
    private KitModificationChecker kitModificationChecker;

    public void add(String kitId, Item item) {
        checkThat(canAdd());
        checkThat(canAddWithArguments(kitId, item));
        itemInserter.insert(kitId, item);
    }

    private ActionPossible canAdd() {
        return check(userProvider.provide() != null, "Brak zalogowanego użytkownika");
    }

    public ActionPossible canAddWithArguments(String kitId, Item item) {
        ActionPossible kidModifiable = kitModificationChecker.canModify(kitId, userProvider.provide().getId());

        AggregatingActionPossible can = new AggregatingActionPossible();
        can.check(kidModifiable.isPossible(), kidModifiable.explainImpossible());
        can.check(!nullToEmpty(item.getName()).equals(""), "Pole nazwa jest obowiązkowe");
        can.check(item.getCurrentAmount() != null, "Pole aktualna ilość jest obowiązkowe");
        return can;
    }
}
