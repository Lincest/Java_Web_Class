/**
 * @author 石学舟_18010500076
 *
 * 输入格式类似 : "2D3H5C9SKH"
 *
 * 输出返回 : "Black Wins" , "White Wins" or "Tie"
 *
 * 采用TDD模式, Test见test/com.work2.Poker/PokerMainTest.java
 *
 */

package com.work2.Poker;

import java.util.Scanner;

public class PokerMain {
    public static void main(String[] args) {
        System.out.println("Black : ");
        Scanner sc = new Scanner(System.in);
        String black = sc.next();
        System.out.println("White : ");
        String white = sc.next();

        PokerCompare poker = new PokerCompare(black, white);

        int ans = poker.compare();
        if (ans == 1)
            System.out.println("Black Wins");
        else if (ans == 2)
            System.out.println("White Wins");
        else if (ans == 3)
            System.out.println("Tie");
    }

}
