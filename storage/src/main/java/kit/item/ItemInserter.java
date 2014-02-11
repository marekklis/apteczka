package kit.item;

import pl.rabbitsoftware.actionpossible.ActionPossible;

public interface ItemInserter {
    void insert(String kitId, Item item);

    ActionPossible canInsert(String kitId, Item item);
}
