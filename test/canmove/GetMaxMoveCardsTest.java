package canmove;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import zhizhu.Card;
import zhizhu.CardList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CardList.getMaxMoveCards方法的测试类
 *
 * @author 阮泽同
 */
@RunWith(Parameterized.class)
public class GetMaxMoveCardsTest {

    private final CardList input;

    private final List<Card> expect;

    public GetMaxMoveCardsTest(CardList input, List<Card> expect) {
        this.input = input;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        Card s2 = new Card(Card.SPADE, 2, true);
        Card s3 = new Card(Card.SPADE, 3, true);
        Card s4 = new Card(Card.SPADE, 4, true);
        Card s5 = new Card(Card.SPADE, 5, true);
        Card s6 = new Card(Card.SPADE, 6, true);
        Card h4 = new Card(Card.HEART, 4, true);
        Card s4_undisplayed = new Card(Card.SPADE, 4, false);
        Card s5_undisplayed = new Card(Card.SPADE, 5, false);
        Object[][] testCases = {
                {new CardList(new ArrayList<>()), new ArrayList<>()},
                {new CardList(List.of(s4)), List.of(s4)},
                {new CardList(Arrays.asList(s6, s5, s3, s2)), Arrays.asList(s2, s3)},
                {new CardList(Arrays.asList(s5, h4, s3, s2)), Arrays.asList(s2, s3)},
                {new CardList(Arrays.asList(s5_undisplayed, s4_undisplayed, s3, s2)), Arrays.asList(s2, s3)},
                {new CardList(Arrays.asList(s5, s4, s3, s2)), Arrays.asList(s2, s3, s4, s5)}
        };
        return Arrays.asList(testCases);
    }

    @Test
    public void test() {
        Assertions.assertEquals(expect, input.getMaxMoveCards());
    }
}
