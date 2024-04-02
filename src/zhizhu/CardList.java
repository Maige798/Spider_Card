package zhizhu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CardList {

    private List<Card> list;

    // �����Ƶ�List�Ƿ��Ѿ�����
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
     * �ƶ��������Ƶ���һ���� <Test>
     */
    public boolean moveSomeCard(CardList targetCardList) {
        // ��Listû��Ԫ��
        if (this.list.size() == 0) {
            return false;
        }
        // cards������Ҫ�ƶ����ƣ������ƶ�5432����cards���Ϊ2345
        List<Card> cards = Cache.moveCards;
        // Ŀ��Listû��Ԫ��
        if (targetCardList.getList().size() == 0) {
            // ��5432��ѭ��Ž�Ŀ��List
            for (Card card : cards) {
                targetCardList.getList().add(card);
            }
            // �ѱ�List�е�5432ɾ��
            for (Card card : cards) {
                this.list.remove(card);
            }
            return true;
        }
        // ��List��Ԫ�أ�Ŀ��ListҲ��Ԫ��
        // Ŀ��List�����һ����
        Card targetCard = targetCardList.getList().get(targetCardList.getList().size() - 1);
        // �������1�������Ҫ��
        if (targetCard.getNum() - cards.get(0).getNum() == 1) {
            // ��5432��ѭ��Ž�Ŀ��List
            for (Card card : cards) {
                targetCardList.getList().add(card);
            }
            // �ѱ�List�е�5432ɾ��
            for (Card card : cards) {
                this.list.remove(card);
            }
            return true;
        }
        // ����������Ҫ��
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

    /** ��ȡ��ǰ�ƶ��Ƿ�Ϊ�� */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /** ��ȡ��ǰ�ƶ�ĩβ���Ƶĵ����������ǰ�ƶ�Ϊ���򷵻�-1 */
    public int getLastNumber() {
        return list.isEmpty() ? -1 : list.get(list.size() - 1).getNum();
    }

    /** ��ȡ�ܹ��ƶ������������У���С������� */
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
     * ��ȡ�ܹ��ƶ���Ŀ���ƶѵ�������
     *
     * @param targetNum Ŀ���ƶ����һ���Ƶĵ���
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
