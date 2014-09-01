package kit.kit;

import kit.item.Item;

import java.util.List;

public interface KitItemsQuery {
    List<Item> items(String kitId);
}
