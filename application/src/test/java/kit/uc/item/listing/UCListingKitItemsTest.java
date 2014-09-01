package kit.uc.item.listing;

import kit.item.Item;
import kit.kit.KitItemsQuery;
import kit.user.UserPermissions;
import org.joda.time.DateTime;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.rabbitsoftware.actionpossible.ActionPossible;
import pl.rabbitsoftware.actionpossible.BasicActionPossible;
import testng.MockitoTestNGListener;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class UCListingKitItemsTest {

    private static final String KIT_ID = "kid_id";
    private static final String WARNING = "impossible warning";
    private static final ActionPossible IMPOSSIBLE = BasicActionPossible.impossible(WARNING);
    private static final ActionPossible POSSIBLE = BasicActionPossible.possible();
    @InjectMocks
    private UCListingKitItems uc;
    @Mock
    private UserPermissions userPermissions;
    @Mock
    private KitItemsQuery query;

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

    @Test
    public void shouldReturnItemsFromQueryIfUserCanListKit() {
        // given
        List<Item> items = Arrays.asList(item("item"));
        given(userPermissions.canListKit(KIT_ID)).willReturn(POSSIBLE);
        given(query.items(KIT_ID)).willReturn(items);
        // when
        List<Item> result = uc.items(KIT_ID);
        // then
        assertEquals(result, items);
    }

    private Item item(String name) {
        return new Item(name, 1, 1, DateTime.now(), DateTime.now(), 10, null, "note");
    }

}