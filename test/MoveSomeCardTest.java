import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import zhizhu.Cache;
import zhizhu.Card;
import zhizhu.CardList;
import zhizhu.GameTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * CardList.moveSomeCard方法的测试类
 *
 * @author 阮泽同
 */
@RunWith(Parameterized.class)
public class MoveSomeCardTest {
    private final List<Card> moveList;

    private final List<Card> sourceBefore;

    private final List<Card> targetBefore;

    private final List<Card> sourceAfter;

    private final List<Card> targetAfter;

    private final boolean canMove;

    public MoveSomeCardTest(List<Card> moveList, List<Card> sourceBefore, List<Card> targetBefore, List<Card> sourceAfter, List<Card> targetAfter, boolean canMove) {
        this.moveList = moveList;
        this.sourceBefore = sourceBefore;
        this.targetBefore = targetBefore;
        this.sourceAfter = sourceAfter;
        this.targetAfter = targetAfter;
        this.canMove = canMove;
    }

    @BeforeClass
    public static void beforeClass() {
        GameTool.initCache();
    }

    @Before
    public void setUp() {
        Cache.moveCards.clear();
        Cache.moveCards.addAll(moveList);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Card[] s = new Card[14];
        for (int i = 1; i <= 13; i++) s[i] = new Card(Card.SPADE, i, true);
        List<Card> moveList = Arrays.asList(s[5], s[4], s[3], s[2]);
        Object[][] testCases = {
                {new ArrayList<>(), new ArrayList<>(), Arrays.asList(s[7], s[6]),
                        new ArrayList<>(), Arrays.asList(s[7], s[6]), false},
                {moveList, Arrays.asList(s[6], s[5], s[4], s[3], s[2]), new ArrayList<>(),
                        List.of(s[6]), Arrays.asList(s[5], s[4], s[3], s[2]), true},
                {moveList, Arrays.asList(s[6], s[5], s[4], s[3], s[2]), List.of(s[7]),
                        Arrays.asList(s[6], s[5], s[4], s[3], s[2]), List.of(s[7]), false},
                {moveList, Arrays.asList(s[6], s[5], s[4], s[3], s[2]), Arrays.asList(s[7], s[6]),
                        List.of(s[6]), Arrays.asList(s[7], s[6], s[5], s[4], s[3], s[2]), true},
        };
        return Arrays.asList(testCases);
    }

    @Test
    public void test() {
        CardList source = new CardList(), target = new CardList();
        source.setList(new ArrayList<>());
        source.getList().addAll(sourceBefore);
        target.setList(new ArrayList<>());
        target.getList().addAll(targetBefore);
        Assertions.assertEquals(canMove, source.moveSomeCard(target));
        Assertions.assertEquals(sourceAfter, source.getList());
        Assertions.assertEquals(targetAfter, target.getList());
    }
}
