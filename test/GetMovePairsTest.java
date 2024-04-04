import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import zhizhu.Cache;
import zhizhu.Card;
import zhizhu.GameTool;
import zhizhu.MovePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * MovePair.getMovePairs方法的测试类
 *
 * @author 阮泽同
 */
@RunWith(Parameterized.class)
public class GetMovePairsTest {
    private final static int poolListNum = 1;

    private final List<List<Card>> poolCardLists;

    private final List<MovePair> expected;

    public GetMovePairsTest(List<List<Card>> poolCardLists, List<MovePair> expected) {
        this.poolCardLists = poolCardLists;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        int index = 0;
        for (List<Card> poolCardList : poolCardLists) {
            Cache.poolLists[index].setList(poolCardList);
            index++;
        }
    }

    @After
    public void tearDown() {
        Arrays.stream(Cache.poolLists).forEach(poolList -> poolList.setList(new ArrayList<>()));
    }

    @AfterClass
    public static void afterClass() {
        GameTool.initCacheList();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        GameTool.initCacheList();
        Card s3 = new Card(Card.SPADE, 3, true);
        Card s4 = new Card(Card.SPADE, 4, true);
        Card s5 = new Card(Card.SPADE, 5, true);
        Object[][] testCases = {
                {new ArrayList<>(), new ArrayList<>()},
                {List.of(Arrays.asList(s4, s3)), List.of(new MovePair(Arrays.asList(s3, s4), Cache.poolLists[poolListNum]))},
                {Arrays.asList(List.of(s3), List.of(s5)), new ArrayList<>()},
                {Arrays.asList(Arrays.asList(s5, s4, s3), List.of(s5)),
                        List.of(new MovePair(Arrays.asList(s3, s4), Cache.poolLists[poolListNum]))}
        };
        return Arrays.asList(testCases);
    }

    @Test
    public void test() {
        Assertions.assertEquals(expected, MovePair.getMovePairs(poolListNum));
    }
}
