package zhizhu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CardList {

    private List<Card> list;

    // 待发牌的List是否已经发牌
    private boolean isReadyListUsed;

    private int number;

    public CardList() {
        list = new ArrayList<>();
        this.number = -1;
    }

    public CardList(int number) {
        list = new ArrayList<>();
        this.number = number;
    }

    /**
     * 移动若干张牌到另一个列 <Test>
     */
    public boolean moveSomeCard(CardList targetCardList) {
        // 本List没有元素
        if (this.list.size() == 0) {
            return false;
        }
        // cards倒叙存放要移动的牌，例如移动5432，则cards存放为2345
        List<Card> cards = Cache.moveCards;
        // 目标List没有元素
        if (targetCardList.getList().size() == 0) {
            // 以5432的循序放进目标List
            for (Card card : cards) {
                targetCardList.getList().add(card);
            }
            // 把本List中的5432删除
            for (Card card : cards) {
                this.list.remove(card);
            }
            return true;
        }
        // 本List有元素，目标List也有元素
        // 目标List的最后一张牌
        Card targetCard = targetCardList.getList().get(targetCardList.getList().size() - 1);
        // 点数相差1，则符合要求
        if (targetCard.getNum() - cards.get(0).getNum() == 1) {
            // 以5432的循序放进目标List
            for (Card card : cards) {
                targetCardList.getList().add(card);
            }
            // 把本List中的5432删除
            for (Card card : cards) {
                this.list.remove(card);
            }
            return true;
        }
        // 点数不符合要求
        return false;
    }

    /**
     * @return the isReadyListUsed
     */
    public boolean isReadyListUsed() {
        return isReadyListUsed;
    }

    /**
     * @param isReadyListUsed the isReadyListUsed to set
     */
    public void setReadyListUsed(boolean isReadyListUsed) {
        this.isReadyListUsed = isReadyListUsed;
    }

    /**
     * @return the list
     */
    public List<Card> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Card> list) {
        this.list = list;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /** 获取当前牌堆是否为空 */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /** 获取当前牌堆末尾的牌的点数，如果当前牌堆为空则返回-1 */
    public int getLastNumber() {
        return list.isEmpty() ? -1 : list.get(list.size() - 1).getNum();
    }

    /** 获取能够移动的最大的牌序列（从小大大逆序） */
    public List<Card> getMaxMoveCards() {
        List<Card> moveCards = new ArrayList<>();
        if (list.isEmpty()) return moveCards;
        int currentNum = list.get(list.size() - 1).getNum();
        String type = list.get(list.size() - 1).getType();
        moveCards.add(list.get(list.size() - 1));
        int index = list.size() - 2;
        while (index >= 0) {
            Card card = list.get(index);
            if (card.getNum() != currentNum + 1 || !card.getType().equals(type) || !card.isShow()) break;
            moveCards.add(card);
            index--;
            currentNum++;
        }
        return moveCards;
    }

    /**
     * 获取能够移动至目标牌堆的牌序列
     *
     * @param targetNum 目标牌堆最后一张牌的点数
     */
    public List<Card> getCanMoveCards(int targetNum) {
        List<Card> moveCards = new ArrayList<>();
        if (list.isEmpty()) return moveCards;
        int currentNum = list.get(list.size() - 1).getNum();
        String type = list.get(list.size() - 1).getType();
        moveCards.add(list.get(list.size() - 1));
        int index = list.size() - 2;
        while (index >= 0) {
            Card card = list.get(index);
            if (card.getNum() != currentNum + 1 || !card.getType().equals(type) || !card.isShow()) break;
            if (card.getNum() >= targetNum) break;
            moveCards.add(card);
            index--;
            currentNum++;
        }
        if (moveCards.isEmpty()) return moveCards;
        return moveCards.get(moveCards.size() - 1).getNum() == targetNum - 1 ? moveCards : new ArrayList<>();
    }

    public void blink() {
        if (list.isEmpty()) {
            Cache.emptyLabels.get(number).setIcon(new ImageIcon("img/blink.gif"));
            new Timer(1000, e -> {
                ((Timer) e.getSource()).stop();
                Cache.emptyLabels.get(number).setIcon(new ImageIcon("img/empty.gif"));
            }).start();
        } else {
            list.get(list.size() - 1).blink();
        }
    }

    @Override
    public String toString() {
        return "CardList{" +
                "list=" + list +
                '}';
    }
}
