package zhizhu;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Card extends JLabel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -495643825896146278L;

    public static final int SPADE = 1;
    public static final int HEART = 2;
    public static final int FLOWER = 3;
    public static final int DIAMOND = 4;

    private String type;

    private int num;

    private boolean isShow;

    private String picture;

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the isShow
     */
    public boolean isShow() {
        return isShow;
    }

    /**
     * @param isShow the isShow to set
     */
    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    public Card(String type, int num) {
        super();
        this.type = type;
        this.num = num;
        isShow = false;
        picture = Cache.cardBackPic;
        this.setIcon(new ImageIcon(picture));
    }

    /**
     * @param type   类型
     * @param num    点数
     * @param isShow 是否展示
     */
    public Card(int type, int num, boolean isShow) {
        super();
        this.type = switch (type) {
            case SPADE -> "heitao";
            case HEART -> "hongtao";
            case FLOWER -> "caohua";
            case DIAMOND -> "fangpian";
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        this.num = num;
        this.isShow = isShow;
        picture = isShow ? "img/" + type + num + ".gif" : Cache.cardBackPic;
        this.setIcon(new ImageIcon(picture));
    }

    public void showCard() {
        setShow(true);
        setPicture("img/" + type + num + ".gif");
        this.setIcon(new ImageIcon(getPicture()));
    }

    public void hiddenCard() {
        setShow(false);
        setPicture(Cache.cardBackPic);
        this.setIcon(new ImageIcon(getPicture()));
    }

    public void blink() {
        setIcon(new ImageIcon("img/blink.gif"));
        new Timer(1000, e -> {
            ((Timer) e.getSource()).stop();
            setIcon(new ImageIcon(getPicture()));
        }).start();
    }

    @Override
    public String toString() {
        return "[" + type + num + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return num == card.num && isShow == card.isShow && Objects.equals(type, card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, num, isShow);
    }

    public static void main(String[] args) {
        List<Card> list = List.of(new Card(Card.SPADE, 1, true));
    }
}
