package zhizhu;

import javax.swing.*;
import java.util.List;

public class Cache {
    /** �Ƴ��ƶ� */
    public static CardList[] poolLists;
    /** �������ƶ� */
    public static CardList[] readyLists;
    public static List<List<Card>> finishLists;

    public static List<StepForBack> stepList;

    public static int level;

    public static String cardBackPic;
    public static String cardEmptyPic;

    public static int pushCardNum;


    public static int xMouse, yMouse;

    // �ƶ�����
    public static List<Card> moveCards;
    public static List<Card> finishCards;

    // �����Ϊ�յ�JLabel
    public static List<JLabel> emptyLabels;
    // �������ƶ���
    public static int moveCardFromNo;


    public static boolean isMousePressed;

    public static boolean ctrlKeyPresswd;

    public static List<MovePair> movePairs;

    private static int movePairIndex = 0;

    public static List<StepForBack> getStepList() {
        return stepList;
    }

    /**
     * ˢ�µ�ǰ�������ʾ��Ϣ
     */
    public static void updateMovePairs() {
        movePairs = MovePair.getAllMovePairs();
        movePairIndex = 0;
    }

    public static void movePairBlink() {
        if (movePairs.isEmpty()) return;
        movePairs.get(movePairIndex).blink();
        movePairIndex = (movePairIndex + 1) % movePairs.size();
    }

    public static void show() {
        System.out.println("-<Cache>----------------------------------------------");
        System.out.println("pool lists:");
        for (CardList poolList : poolLists) {
            System.out.println(poolList);
        }
        System.out.println("readyLists");
        for (CardList readyList : readyLists) {
            System.out.println(readyList);
        }
    }
}
