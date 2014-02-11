package kit.item;

import kit.kit.Kit;
import kit.kit.KitByItemFinder;
import kit.kit.KitModificationChecker;
import pl.rabbitsoftware.actionpossible.ActionPossible;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ItemModificationChecker {

    @Inject
    private KitByItemFinder kitByItemFinder;
    @Inject
    private KitModificationChecker kitModificationChecker;

    public ActionPossible canModify(String itemId, String userId) {
        Kit kit = kitByItemFinder.find(itemId);
        return kitModificationChecker.canModify(kit.getId(), userId);
    }
}
