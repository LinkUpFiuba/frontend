package connections;

import java.util.List;

import linkup.linkup.model.CardSwipeContent;
import linkup.linkup.model.User;

/**
 * Created by german on 9/9/2017.
 */

public interface ViewWithCards {
     void showCards(List<CardSwipeContent> users, boolean showToasts);
}
