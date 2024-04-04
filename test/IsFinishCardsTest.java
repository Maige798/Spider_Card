import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import zhizhu.Cache;
import zhizhu.Card;
import zhizhu.GamePanel;
import zhizhu.GameTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * GameTool.isFinishCardsµƒ≤‚ ‘¿‡
 *
 * @author »Ó‘ÛÕ¨
 */
@RunWith(Parameterized.class)
public class IsFinishCardsTest {
    private static GamePanel panel;

    private static final int listNumber = 1;

    private final List<Card> cardList;

    private final boolean expectValue;

    private final List<Card> expectList;

    public IsFinishCardsTest(List<Card> cardList, boolean expectValue, List<Card> expectList) {
        this.cardList = cardList;
        this.expectValue = expectValue;
        this.expectList = expectList;
    }

    private static void init() {
        GameTool.initCache();
        GameTool.initCacheList();
        panel = new GamePanel(false);
    }

    @Before
    public void setUp() {
        Cache.poolLists[listNumber].setList(cardList);
    }

    @After
    public void tearDown() {
        Cache.poolLists[listNumber].setList(new ArrayList<>());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        init();
        Card[] s = new Card[14];
        for (int i = 1; i <= 13; i++) s[i] = new Card(Card.SPADE, i, true);
        Card h6 = new Card(Card.HEART, 6, true), h6_undisplayed = new Card(Card.HEART, 6, false);

        List<Card> l1 = new ArrayList<>();
        for (int i = 12; i >= 1; i--) l1.add(s[i]);
        List<Card> l2 = new ArrayList<>();
        for (int i = 13; i >= 2; i--) l2.add(s[i]);
        List<Card> l3 = new ArrayList<>();
        for (int i = 13; i >= 7; i--) l3.add(s[i]);
        l3.add(h6);
        for (int i = 5; i >= 1; i--) l3.add(s[i]);
        List<Card> l4 = new ArrayList<>();
        for (int i = 13; i >= 7; i--) l4.add(s[i]);
        l4.add(s[7]);
        for (int i = 5; i >= 1; i--) l4.add(s[i]);
        List<Card> l5 = new ArrayList<>();
        for (int i = 13; i >= 1; i--) l5.add(s[i]);
        List<Card> l6 = new ArrayList<>();
        l6.add(h6_undisplayed);
        for (int i = 13; i >= 1; i--) l6.add(s[i]);

        Object[][] testCases = {
                {l1, false, l1},
                {l2, false, l2},
                {l3, false, l3},
                {l4, false, l4},
                {l5, true, new ArrayList<>()},
                {l6, true, List.of(h6_undisplayed)},
        };
        return Arrays.asList(testCases);
    }

    @Test
    public void test() {
        Assertions.assertEquals(expectValue, GameTool.isFinishCards(listNumber, panel));
        Assertions.assertEquals(expectList, Cache.poolLists[listNumber].getList());
        List<Card> cardList = Cache.poolLists[listNumber].getList();
        Assertions.assertTrue(cardList.isEmpty() || cardList.get(cardList.size() - 1).isShow());
    }
}
