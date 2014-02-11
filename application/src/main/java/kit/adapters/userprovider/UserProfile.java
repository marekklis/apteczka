package kit.adapters.userprovider;

import lombok.Data;

@Data
public class UserProfile {
    private final String id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final String email;
}
