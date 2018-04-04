package Poker;

/**
 * Created by vip on 2018/4/3.
 */
public class Player {
    private int[] initCards = new int[20];
    private int[] inHand = new int[20];
    private int[] outCards = new int[20];

    public Player(int[] initCards) {
//        this.initCards = initCards;
        System.arraycopy(initCards, 0, this.initCards, 0, initCards.length);

        //排序

        System.arraycopy(this.initCards, 0, this.inHand, 0, this.initCards.length);
    }

    //两种方式，1.下标出牌；2。值出牌
    public void play(int[] outCards) {

        //选中要出的牌，

        //判断规则，能不能这么出

        //打出去

        //记录
        System.arraycopy(outCards, 0, this.outCards, 0, outCards.length);

        ///剩下的牌面去掉这些牌。



    }

    public void pass() {

    }

}
