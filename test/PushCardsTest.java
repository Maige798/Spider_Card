import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import zhizhu.*;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PushCardsTest {
    private static GamePanel gamePanel;

    private static Card s3 = new Card(Card.SPADE, 3, true);

    private static Card s6 = new Card(Card.SPADE, 6, true);

    @BeforeClass
    public static void beforeClass() {
        GameTool.initCache();
        GameTool.initCacheList();
        s3.addMouseListener(new MouseAdapter() {
        });
        s6.addMouseListener(new MouseAdapter() {
        });
        gamePanel = new GamePanel(false);
    }

    @Before
    public void setUp() {
        for (CardList poolList : Cache.poolLists) {
            poolList.getList().clear();
        }
        for (CardList readyList : Cache.readyLists) {
            readyList.getList().clear();
        }
    }

    @Test
    public void testTrue() {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) cardList.add(s6);
        Cache.readyLists[0].getList().addAll(cardList);
        for (CardList poolList : Cache.poolLists) {
            poolList.getList().add(s3);
        }
        gamePanel.pushCards();
        for (CardList poolList : Cache.poolLists) {
            Assertions.assertEquals(Arrays.asList(s3, s6), poolList.getList());
        }
    }

    @Test
    public void testFalse() {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) cardList.add(s6);
        Cache.readyLists[0].getList().addAll(cardList);
        gamePanel.pushCards();
        for (CardList poolList : Cache.poolLists) {
            Assertions.assertEquals(new ArrayList<>(), poolList.getList());
        }
    }
}
