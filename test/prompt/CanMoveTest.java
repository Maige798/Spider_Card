package prompt;

import org.junit.jupiter.api.Test;
import zhizhu.Card;
import zhizhu.CardList;

import java.util.Arrays;

public class CanMoveTest {
    @Test
    public void testCanMove() {
        CardList cardList = new CardList();
        Card[] cards = new Card[]{
                new Card("heitao", 8),
                new Card("heitao", 7),
                new Card("heitao", 6),
                new Card("heitao", 4),
                new Card("heitao", 3),
                new Card("heitao", 2),
        };
        for (int i = 1; i < 6; i++) cards[i].setShow(true);
        cardList.setList(Arrays.asList(cards));
        System.out.println(cardList.getMaxMoveCards());
        System.out.println(cardList.getCanMoveCards(4));
        System.out.println(cardList.getCanMoveCards(8));
    }
}
