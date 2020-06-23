# 敏捷web开发 双创周作业



## 第一次作业 : 实现 com.work1.Fibonacci.fibonacci 数列，要求(以Java为例)如下

- 不要提交依赖包等文件
- 分两次提交，第一次提交README.md，第二次提交代码
- 函数名为 Fibonacci.of()
- of是一个静态方法，入参是int，返回值是int
- 参数从1开始，各参考值如下

```
Fibonacci.of(1) == 1
Fibonacci.of(2) == 1
Fibonacci.of(3) == 2
Fibonacci.of(4) == 3
Fibonacci.of(5) == 5
...
```

- 在main函数用循环打印1~200的Fibonacci数列
- 提交到自己的github
- 将作业的github地址写在答案中

**实现** : 

```java
import static java.lang.Math.*;


public class com.work1.Fibonacci.fibonacci {
    public static int of (int n) {
        return (int)((double)1/(sqrt(5)) * (pow((1 + sqrt(5))/2, n + 1) - pow((1 - sqrt(5))/2, n + 1)));
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(com.work1.Fibonacci.fibonacci.of(i));
        }
    }
}
```


**作业地址** :  ./src/com.work1.Fibonacci.fibonacci.java



## 第二次作业 : 德州扑克

本作业采用TDD测试驱动开发, JUnit4作为单元测试框架

![](https://cdn.jsdelivr.net/gh/Lincest/PicGoStorage@master/img/20200623224259.png)

其中src/com/work2/Poker下内容为源代码

test/com/work2/Poker下内容为测试文件

- 测试文件PokerMainTest内容如下 : 

```java
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
```

测试样例为任务要求 

返回结果如下 : 

![](https://cdn.jsdelivr.net/gh/Lincest/PicGoStorage@master/img/20200623224712.png)

- PokerMain主函数如下 : 

```java
/**
 * @author 石学舟_18010500076
 *
 * 输入格式样例 : "2D3H5C9SKH"
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

```



- 逻辑具体实现如下 : 

```java
/**
 * @Author 石学舟_18010500076
 *
 * 德州扑克 主要逻辑部分
 */
package com.work2.Poker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PokerCompare {

    private int[] blacknum;
    private int[] whitenum ;
    private char[] blackflower;
    private char[] whiteflower;

    public int[] getBlacknum() {
        return blacknum;
    }

    public int[] getWhitenum() {
        return whitenum;
    }

    public char[] getBlackflower() {
        return blackflower;
    }

    public char[] getWhiteflower() {
        return whiteflower;
    }

    public PokerCompare() {
    }

    public PokerCompare(String black_, String white_) {
        char[] black = black_.toCharArray();
        char[] white = white_.toCharArray();
        blacknum = new int[5];
        whitenum = new int[5];
        blackflower = new char[5];
        whiteflower = new char[5];
        int x = 0, y =  0;
        for (int i = 0; i < black.length; i++) { // 提取花色和数值
            if (i % 2 == 0) {
                if(black[i] == 'T') blacknum[x] = 10;
                if(white[i] == 'T') whitenum[x] = 10;
                if(black[i] == 'J') blacknum[x] = 11;
                if(white[i] == 'J') whitenum[x] = 11;
                if(black[i] == 'Q') blacknum[x] = 12;
                if(white[i] == 'Q') whitenum[x] = 12;
                if(black[i] == 'K') blacknum[x] = 13;
                if(white[i] == 'K') whitenum[x] = 13;
                if(black[i] == 'A') blacknum[x] = 14;
                if(white[i] == 'A') whitenum[x] = 14;
                if(black[i] <= '9' && black[i] >='0') blacknum[x] = black[i] - '0';
                if(white[i] <= '9' && white[i] >='0') whitenum[x] = white[i] - '0';
                x++;
            }
            else {
                blackflower[y] = black[i];
                whiteflower[y] = white[i];
                y++;
            }
        }
    }

    private int getMaxNum(int[] num) {
        int maxn = -1;
        for (int i = 0; i < num.length; i++) {
            maxn = Math.max(num[i], maxn);
        }
        return maxn;
    }

    private int getMaxNum(int[] num, int k) {   // 重载, 获取第k大的值
        Arrays.sort(num);
        return num[num.length - k];
    }

    private int compareNumByMax() { // 按顺序比较大小
        int k = 1;
        while(getMaxNum(blacknum,k) == getMaxNum(whitenum,k)) {
            if (k==5) return 3; // 全相等
            k++;
        }
        if (getMaxNum(blacknum, k) > getMaxNum(whitenum, k))
            return 1;
        else
            return 2;
    }

    private boolean isSequence(int[] num, char[] flower) {  // 比较是否是同花顺
        for (int i = 1; i < num.length; i++) {
            if (num[i] - num[i-1] != 1) return false;
        }
        for (int i = 1; i < flower.length; i++) {
            if (flower[i] != flower[i-1]) return false;
        }
        return true;
    }

    private boolean isSameFlower(char[] flower) {   // 比较是否是同花
        for (int i = 1; i < flower.length; i++) {
            if (flower[i] != flower[i-1]) return false;
        }
        return true;
    }

    private boolean isSameNum(int[] num) { // 比较是否是顺子
        for (int i = 1; i < num.length; i++) {
            if (num[i] - num[i-1] != 1) return false;
        }
        return true;
    }

    private int isThreeSame(int[] num) { // 是否是三条, 如果不是返回-1, 如果是返回点数
        int[] judge = new int[15];
        for (int i = 0; i < num.length; i++) {
            judge[num[i]]++; // 计数
            if (judge[num[i]] == 3) return num[i];
        }
        return -1;
    }

    private boolean isTwoPair(int[] num) {  // 是否是两对
        int[] judge = new int[15];
        int cnt = 0;
        for (int i = 0; i < num.length; i++) {
            judge[num[i]]++;
            if (judge[num[i]] == 2) cnt++;
            if (cnt == 2)
                return true;
        }
        return false;
    }

    private int[] twoPairCmp(int[] num) { // 返回大对子, 小对子, 单牌
        int[] ans = new int[3];
        Arrays.sort(num);
        ans[0] = num[0];
        int k = 1;
        for (int i = 1; i < num.length; i++) {
            if(num[i] != num[i-1]) ans[k++] = num[i];
        }
        Arrays.sort(ans);
        return ans;
    }

    private int isPair(int[] num ) { // 判断是否是对子并返回对子的大小, 不是则返回-1
        int[] judge = new int[15];
        for (int i = 0; i < num.length; i++) {
            judge[num[i]]++;
            if (judge[num[i]] == 2) return num[i];
        }
        return -1;
    }

    private int[] PairCmp(int[] num ) {    // 返回三张单排从小到大排序后的数组
        int[] ans = new int[3];
        int k = 0;
        Arrays.sort(num);
        for (int i = 1; i < num.length - 1; i++) {
            if (num[i] != num[i-1] && num[i] != num[i+1])
                ans[k++] = num[i];
        }
        if(num[num.length-1] != num[num.length-2]) ans[2] = num[num.length-1];
        Arrays.sort(ans);
        return ans;
    }

    public int compare() {
        int blackwin = 1; // 黑胜
        int whitewin = 2; // 白胜
        int tie = 3;    // 平手
        int maxblack = getMaxNum(blacknum); // 获取黑方最大的值
        int maxwhite = getMaxNum(whitenum); // 获取白方最大的值

        if (isSequence(blacknum, blackflower)) {    // 黑色是同花顺
            if (!isSequence(whitenum, whiteflower)) // 白色不是同花顺
                return 1;
            else                                    // 白色也是
                if(maxblack > maxwhite) return 1;
                else if (maxblack < maxwhite) return 2;
                else
                    return 3;
        }

        else if (isSequence(whitenum, whiteflower)) { // 白色是同花顺黑色不是
            return 2;
        }

        else if (isSameFlower(blackflower)) { // 比较同花
            if (!isSameFlower(whiteflower))
                return 1;
            else
                return compareNumByMax();
        }

        else if (isSameFlower(whiteflower)) {
            return 2;
        }


        else if (isSameNum(blacknum)) { // 比较顺子
            if (!isSameNum(whitenum))
                return 1;
            else
                return compareNumByMax();
        }

        else if (isSameNum(whitenum)) {
            return 2;
        }

        else if (isThreeSame(blacknum) != -1) { // 比较三条
            int b = isThreeSame(blacknum);
            int w = isThreeSame(whitenum);
            if (w == -1)
                return 1;
            else
            {
                if (b > w) return 1;
                else if (w > b) return 2;
                else return 3;
            }
        }

        else if (isThreeSame(whitenum) != -1) {
            return 2;
        }

        else if (isTwoPair(blacknum)) { // 比较两对
            if (!isTwoPair(whitenum))
                return 1;
            else
            {   // 从大到小比较 大对子, 中对子, 小对子
                int[] blackans = twoPairCmp(blacknum);
                int[] whiteans = twoPairCmp(whitenum);
                if (blackans[0] > whiteans[0]) return 1;
                else if (whiteans[0] > blackans[0]) return 2;
                else
                {
                    if (blackans[1] > whiteans[1]) return 1;
                    else if (blackans[1] < whiteans[1]) return 2;
                    else {
                        if (blackans[3] > whiteans[3]) return 1;
                        else if (blackans[3] < whiteans[3]) return 2;
                        else return 3;
                    }
                }
            }
        }

        else if (isTwoPair(whitenum)) {
            return 2;
        }


        else if (isPair(blacknum) != -1) {
            if (isPair(whitenum) == -1) return 1;
            else
            {
                int b = isPair(blacknum);
                int w = isPair(whitenum);
                if (b > w) return 1;
                else if (w > b) return 2;
                else {
                    int[] ansb = twoPairCmp(blacknum);
                    int[] answ = twoPairCmp(whitenum);
                    if(ansb[2] > answ[2]) return 1;
                    else if(ansb[2] < answ[2]) return 2;
                    else {
                        if (ansb[1] > answ[1]) return 1;
                        else if(ansb[1] < answ[1]) return 2;
                        else {
                            if (ansb[0] > answ[0]) return 1;
                            else if (ansb[0] < answ[0]) return 2;
                            else
                                return 3;
                        }
                    }
                }
            }
        }

        else if (isPair(whitenum) != -1)
            return 2;


        else { // 现在只能都是散牌了
            Arrays.sort(blacknum);
            Arrays.sort(whitenum);  // 直接原地排序
            for (int i = 4; i >=0 ; --i) {
                if (blacknum[i] > whitenum[i])
                    return 1;
                else if(blacknum[i] < whitenum[i])
                    return 2;
            }
            return 3;
        }
    }
}

```

内容均在src中可见

