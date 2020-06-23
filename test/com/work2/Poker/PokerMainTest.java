/**
 * @Author 石学舟_18010500076
 *
 * 用于测试驱动
 */
package com.work2.Poker;

import com.work2.Poker.PokerCompare;
import org.junit.Test;

import static org.junit.Assert.*;

public class PokerMainTest {

    String white1 = "2D3H5C9SKH";
    String black1 = "2H3D5S9CKD"; // Tie---3

    String white2 = "2C3H4S5C6H";
    String black2 = "2H3H5H9HkH"; // Black Wins---1

    String white3 = "2S8SASQS3S";
    String black3 = "2H4S4C2D4H"; // White Wins---2

    String white4 = "2C3H4S8CAH";
    String black4 = "2H3D5S9CKD"; // White Wins---2

    @Test
    public void main() {
        assertEquals(3, new PokerCompare(black1,white1).compare());
        assertEquals(1, new PokerCompare(black2,white2).compare());
        assertEquals(2, new PokerCompare(black3,white3).compare());
        assertEquals(2, new PokerCompare(black4,white4).compare());
    }
}