package Poker;

import java.util.Random;

/**
 * Created by vip on 2018/4/3.
 */
public class Cards {

    private enum Suits {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }
    private int[][] cards = new int[4][14];//牌库

    private static int RED_JOKER = 99;//大王
    private static int BLACK_JOKER = 100;//小王

    private int[][] playerA = new int[2][17];
    private int[][] playerB = new int[2][17];
    private int[][] playerC = new int[2][17];
    private int[][] land = new int[2][3];//地主牌。
    private boolean[][] follow = new boolean[4][14];//跟踪牌是否发出去了。
    
    public Cards() {
       
        System.out.println(cards.length);
        System.out.println(cards[0].length);
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length - 1; j++) {
                cards[i][j] = j + 1;
                follow[i][j] = true;
            }
        }
        cards[0][13] = RED_JOKER;
        cards[1][13] = BLACK_JOKER;
        follow[0][13] = true;
        follow[0][13] = true;
    }



    /**
     * 洗牌。
     */
    public void shuffle() {
        int count = 0;//发出去的牌数量。
        int countA = 0;//A有的牌数
        int countB = 0;//
        int countC = 0;//
        Random random = new Random();
        while (true) {
            int suits = random.nextInt(4);//花色
            int num = random.nextInt(14);//牌面大小
            int belong = random.nextInt(3);//识别发给谁
            if ( belong == 0 ) {//发给A
                if (follow[suits][num]) {//判断牌是不是发出去了
                    for (; countA < playerA[0].length; ) {
                        playerA[0][countA] = cards[suits][num];
                        playerA[1][countA] = suits;
                        follow[suits][num] = false;
                        countA++;
                        count++;
                        break;
                    }
                    
                }
            } else if (belong == 1) {//发给B
                if (follow[suits][num]) {//判断牌是不是发出去了
                    for (; countB < playerB[0].length; ) {
                        playerB[0][countB] = cards[suits][num];
                        playerB[1][countB] = suits;
                        follow[suits][num] = false;
                        countB++;
                        count++;
                        break;
                    }

                }
            } else {
                if (follow[suits][num]) {//判断牌是不是发出去了
                    for (; countC < playerC[0].length; ) {
                        playerC[0][countC] = cards[suits][num];
                        playerC[1][countC] = suits;
                        follow[suits][num] = false;
                        countC++;
                        count++;
                        break;
                    }

                }
            }
            if (count == 51) {
                int countLand = 0;
                for (int i = 0; i < cards.length; i++) {
                    for (int j = 0; j < cards[i].length; j++) {
                        if (follow[i][j]) {
                            land[0][countLand] = cards[i][j];
                            land[1][countLand] = i;
                            countLand++;
                        }
                        if (countLand == 3) {
                            break;
                        }
                    }

                }
                break;
            }

        }
        System.out.println("playerA");
        printPlayer(playerA);
        System.out.println("playerB");
        printPlayer(playerB);
        System.out.println("playerB");
        printPlayer(playerC);
        System.out.println("land");
        printPlayer(land);
    }

    public static void printPlayer(int[][] player) {
        for (int i = 0, j = 0; i < player.length;) {
            System.out.printf("%-4d", player[i][j]);
            j++;
            if (j >= player[i].length) {
                i++;
                j = 0;
                System.out.println();
            }
        }
    }
    /**
     * 发牌。
     */
    public void deal() {

    }

    public int[][] getPlayerA() {
        return playerA;
    }

    public int[][] getPlayerB() {
        return playerB;
    }

    public int[][] getPlayerC() {
        return playerC;
    }

    public int[][] getLand() {
        return land;
    }
}
