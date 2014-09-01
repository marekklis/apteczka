package kit.uc.item.listing;

import kit.user.UserPermissions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.rabbitsoftware.actionpossible.ActionPossible;
import pl.rabbitsoftware.actionpossible.BasicActionPossible;
import testng.MockitoTestNGListener;

import static org.mockito.BDDMockito.given;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class UCListingKitItemsTest {

    private static final String KIT_ID = "kid_id";
    private static final String WARNING = "impossible warning";
    private static final ActionPossible IMPOSSIBLE = BasicActionPossible.impossible(WARNING);
    @InjectMocks
    private UCListingKitItems uc;
    @Mock
    private UserPermissions userPermissions;

    @Test
    public void shouldThrowExceptionIfUserCanNotListGivenKit() {
        // given
        given(userPermissions.canListKit(KIT_ID)).willReturn(IMPOSSIBLE);
        try {
            // when
            uc.items(KIT_ID);
            Assert.fail("IllegalStateException should have been thrown");
        } catch (IllegalStateException ex) {
            // then
            assertEquals(ex.getMessage(), WARNING);
        }
    }

}