package zhizhu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MovePair implements Comparable<MovePair> {
    private List<Card> sourceCards;

    private CardList targetCardList;

    public MovePair(List<Card> sourceCards, CardList targetCardList) {
        this.sourceCards = sourceCards;
        this.targetCardList = targetCardList;
    }

    public List<Card> getSourceCards() {
        return sourceCards;
    }

    public void setSourceCards(List<Card> sourceCards) {
        this.sourceCards = sourceCards;
    }

    public CardList getTargetCardList() {
        return targetCardList;
    }

    public void setTargetCardList(CardList targetCardList) {
        this.targetCardList = targetCardList;
    }

    /**
     * 获取当前牌池中的所有移动提示
     *
     * @deprecated
     */
    public static List<MovePair> getAllMovePairs_old() {
        List<MovePair> pairs = new ArrayList<>();
        CardList[] poolLists = Cache.poolLists;
        for (int i = 0; i < 10; i++) {
            int expectedNum = poolLists[i].getLastNumber();
            for (int j = 0; j < 10; j++) {
                if (i == j) continue;
                List<Card> moveCards;
                moveCards = expectedNum == -1 ? poolLists[j].getMaxMoveCards() : poolLists[j].getCanMoveCards(expectedNum);
                if (!moveCards.isEmpty()) pairs.add(new MovePair(moveCards, poolLists[i]));
            }
        }
        Collections.sort(pairs);
        return pairs;
    }

    /**
     * 获取当前牌池中的所有移动提示
     */
    public static List<MovePair> getAllMovePairs() {
        List<MovePair> pairs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pairs.addAll(getMovePairs(i));
        }
        Collections.sort(pairs);
        return pairs;
    }

    /**
     * 获取能够移动到序号为poolListNum的牌堆的所有移动提示<Test>
     *
     * @param poolListNum 目标牌堆序号
     */
    public static List<MovePair> getMovePairs(int poolListNum) {
        List<MovePair> pairs = new ArrayList<>();
        CardList[] poolLists = Cache.poolLists;
        int expectedNum = poolLists[poolListNum].getLastNumber(); // poolLists[poolListNum]为空时，expectedNum为-1
        for (int i = 0; i < 10; i++) {
            if (i == poolListNum) continue;
            List<Card> moveCards = expectedNum == -1 ? poolLists[i].getMaxMoveCards() : poolLists[i].getCanMoveCards(expectedNum);
            if (!moveCards.isEmpty()) pairs.add(new MovePair(moveCards, poolLists[i]));
        }
        return pairs;
    }

    public void blink() {
        sourceCards.forEach(Card::blink);
        new Timer(1000, e -> {
            ((Timer) e.getSource()).stop();
            targetCardList.blink();
        }).start();
    }

    @Override
    public String toString() {
        return "ChangePair{" +
                "sourceCards=" + sourceCards +
                ", targetCardList number=" + targetCardList.getNumber() +
                '}';
    }

    @Override
    public int compareTo(MovePair o) {
        return o.getSourceCards().size() - this.getSourceCards().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovePair movePair = (MovePair) o;
        return Objects.equals(sourceCards, movePair.sourceCards) && Objects.equals(targetCardList, movePair.targetCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceCards, targetCardList);
    }
}
