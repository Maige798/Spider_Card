import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import zhizhu.Card;
import zhizhu.CardList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * CardList.getCanMoveCards方法的测试类
 *
 * @author 阮泽同
 */
@RunWith(Parameterized.class)
public class GetCanMoveCardsTest {
    private final CardList input;

    private final List<Card> expect;

    private final int targetNumber;

    public GetCanMoveCardsTest(CardList input, List<Card> expect, int targetNumber) {
        this.input = input;
        this.expect = expect;
        this.targetNumber = targetNumber;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Card s3 = new Card(Card.SPADE, 3, true);
        Card s4 = new Card(Card.SPADE, 4, true);
        Card s5 = new Card(Card.SPADE, 5, true);
        Card s6 = new Card(Card.SPADE, 6, true);
        Card s7 = new Card(Card.SPADE, 7, true);
        Card h5 = new Card(Card.HEART, 5, true);
        Card s5_undisplayed = new Card(Card.SPADE, 5, false);
        Card s6_undisplayed = new Card(Card.SPADE, 6, false);
        Object[][] testCases = {
                {new CardList(new ArrayList<>()), new ArrayList<>(), 5},
                {new CardList(List.of(s4)), List.of(s4), 5},
                {new CardList(Arrays.asList(s4, s3, s4, s3)), Arrays.asList(s3, s4), 5},
                {new CardList(Arrays.asList(s6, h5, s4, s3)), Arrays.asList(s3, s4), 5},
                {new CardList(Arrays.asList(s6_undisplayed, s5_undisplayed, s4, s3)), Arrays.asList(s3, s4), 5},
                {new CardList(Arrays.asList(s6, s5, s4, s3)), Arrays.asList(s3, s4, s5, s6), 7},
                {new CardList(Arrays.asList(s6, s5, s4, s3)), Arrays.asList(s3, s4), 5},
                {new CardList(Arrays.asList(s7, s7, s4, s3)), new ArrayList<>(), 6}
        };
        return Arrays.asList(testCases);
    }

    @Test
    public void test() {
        Assertions.assertEquals(expect, input.getCanMoveCards(targetNumber));
    }
}
