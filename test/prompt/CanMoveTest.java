package prompt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zhizhu.Cache;
import zhizhu.Card;
import zhizhu.CardList;
import zhizhu.GameTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanMoveTest {
    @Test
    public void testCanMove() {
        GameTool.initCache();
        Card[] s = new Card[14];
        for (int i = 1; i <= 13; i++) s[i] = new Card(Card.SPADE, i, true);
        List<Card> moveList = Arrays.asList(s[2], s[3], s[4], s[5]);
        Cache.moveCards.clear();
        Cache.moveCards.addAll(moveList);
        List<Card> sourceBefore = Arrays.asList(s[6], s[5], s[4], s[3], s[2]);
        List<Card> targetBefore = new ArrayList<>();
        List<Card> sourceAfter = List.of(s[6]);
        List<Card> targetAfter = Arrays.asList(s[5], s[4], s[3], s[2]);

        CardList source = new CardList(), target = new CardList();
        source.setList(new ArrayList<>());
        source.getList().addAll(sourceBefore);
        target.setList(new ArrayList<>());
        target.getList().addAll(targetBefore);
        source.getList().remove(s[4]);
        System.out.println(source);
        System.out.println(target);
        Assertions.assertTrue(source.moveSomeCard(target));
        Assertions.assertEquals(sourceAfter, source.getList());
        Assertions.assertEquals(targetAfter, target.getList());
    }

    @Test
    public void test() {
        GameTool.initCache();
        Card[] s = new Card[14];
        for (int i = 1; i <= 13; i++) s[i] = new Card(Card.SPADE, i, true);
        List<Card> moveList = Arrays.asList(s[2], s[3], s[4], s[5]);
        Cache.moveCards.clear();
        Cache.moveCards.addAll(moveList);
        System.out.println(Cache.moveCards);
        Cache.moveCards.remove(s[2]);
        System.out.println(Cache.moveCards);
    }
}
