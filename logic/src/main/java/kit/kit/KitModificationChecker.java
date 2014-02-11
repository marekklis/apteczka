package kit.kit;

import kit.user.UserKid;
import kit.user.UserKidListingQuery;
import pl.rabbitsoftware.actionpossible.ActionPossible;
import pl.rabbitsoftware.actionpossible.BasicActionPossible;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class KitModificationChecker {

    @Inject
    private UserKidListingQuery userKidListingQuery;

    public ActionPossible canModify(String kitId, String userId) {
        for (UserKid userKid : userKidListingQuery.execute(userId)) {
            if (userKid.getId().equals(kitId)) {
                return BasicActionPossible.possible();
            }
        }
        return BasicActionPossible.impossible("Apteczka nie może być edytowana przez użytkownika");
    }
}
