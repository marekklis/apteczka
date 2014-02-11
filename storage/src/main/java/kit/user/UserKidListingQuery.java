package kit.user;

import java.util.List;

public interface UserKidListingQuery {

    List<UserKid> execute(String userId);

}
