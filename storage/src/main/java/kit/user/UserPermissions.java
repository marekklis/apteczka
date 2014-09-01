package kit.user;

import pl.rabbitsoftware.actionpossible.ActionPossible;

public interface UserPermissions {
    public ActionPossible canListKit(String kitId);
}
