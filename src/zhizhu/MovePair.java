package zhizhu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static List<MovePair> getAllMovePairs() {
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
                ", targetCardList end=" + targetCardList.getLastNumber() +
                '}';
    }

    @Override
    public int compareTo(MovePair o) {
        return o.getSourceCards().size() - this.getSourceCards().size();
    }
}
