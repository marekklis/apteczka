package kit.uc.item.add;

import kit.adapters.storage.ItemInserter;
import kit.adapters.userprovider.UserProvider;
import pl.rabbitsoftware.actionpossible.ActionPossible;
import pl.rabbitsoftware.actionpossible.AggregatingActionPossible;

import static com.google.common.base.Strings.nullToEmpty;
import static pl.rabbitsoftware.actionpossible.BasicActionPossible.check;
import static pl.rabbitsoftware.actionpossible.Check.checkThat;

public class UCAddingItem {

    private UserProvider userProvider;

    private ItemInserter itemInserter;

    public void add(String kitId, Item item) {
        checkThat(canAdd());
        checkThat(canAddWithArguments(kitId, item));
        itemInserter.insert(kitId, item);
    }

    private ActionPossible canAdd() {
        return check(userProvider.provide() != null, "Brak zalogowanego użytkownika");
    }

    public ActionPossible canAddWithArguments(String kitId, Item item) {
        AggregatingActionPossible can = new AggregatingActionPossible();
        can.check(!nullToEmpty(item.getName()).equals(""), "Pole nazwa jest obowiązkowe");
        can.check(item.getCurrentAmount() != null, "Pole aktualna ilość jest obowiązkowe");
        return can;
    }
}
