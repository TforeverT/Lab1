package jdbc;

import java.util.Scanner;

/**
 * @author zzy && zyc.
 */
public class Helloworld {

    /**
    * deriative.
    * @param exp
    *            string
    * @param flag
    * char
    */
    public static void derivative(final String exp, final char flag) {
        String mm = "";
        int ii = -1;
        int l, x, y, j, k, s;
        l = exp.length();
        x = 0;
        while (ii < l - 1) {
            ii++;
            if ((exp.charAt(ii) == '+') || (ii == l - 1)) {
                if (ii == l - 1) {
                    y = ii;
                } else {
                    y = ii - 1;
                }
                k = 0;
                s = 1;
                // System.out.println(x);
                // System.out.println(y);
                for (j = x; j <= y; j++) {
                    if (exp.charAt(j) == flag) {
                        k++;
                    }
                }
                // System.out.println(k);
                s = k;
                if (k > 0) {
                    int pd = 0;
                    for (j = x; j <= y; j++) {
                        if ((exp.charAt(j) == flag)
                                && (pd == 0)) {
                            pd = 1;
                            mm = mm
                            + String.valueOf(s);
                        } else {
                            mm = mm + exp.charAt(j);
                        }
                    }
                }
                x = y + 1;
            }

        }
        for (ii = 0; ii < mm.length(); ii++) {
            if ((!((ii == mm.length() - 1)
                    && (mm.charAt(ii) == '+')))
                    && (!((ii == 0)
                    && (mm.charAt(ii) == '+')))) {
                System.out.print(mm.charAt(ii));
            }
        }
        if (mm.length() == 0) {
            System.out.print(0);
        }
        System.out.println();

    }

    /**
     * main.
     *
     * @param args
     *            string
     * @throws IOException
     */
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        // 标志，避免使用while true
        int x = 0;
        String a = new String();
        // 死循环
        while (x == 0) {
            // n用来保存输入的字符串
            String n = in.nextLine();
            // 输入的不是命令
            if (n.charAt(0) != '!') {
                a = n;
                pd(a);
            } else {
                // 化简命令，a为表达式，n为表达式
                if (n.charAt(1) == 's') {
                    simplify(a, n);
                } else if (n.charAt(1) == 'd') {
                    derivative(a, n.charAt(4));
                } else {
                    x = 1;
                }
            }
        }
        in.close();
    }

    /**
     * pd.
     * @param exp
     *            String
     *
     */
    public static void pd(final String exp) {
        int flag = 0;
        int l = exp.length();
        int index = 0;
        // 遍历字符串,x=1不合法
        for (int i = 0; i < l; i++) {
            char ch = exp.charAt(i);
            // 是数字
            if ((ch >= '0') && (ch <= '9')) {
                index++;
            } else {
                index = 0;
            }
            if ((index > 1) && (exp.charAt(i - index + 1) == '0')) {
                flag = 1;
            }
            if ((!(((ch >= '0') && (ch <= '9'))
                    || ((ch >= 'a') && (ch <= 'z'))))
                    && (ch != '*') && (ch != '+')) {
                flag = 1;
            }
            if (i > 0) {
                char c = exp.charAt(i - 1);
                if (((ch >= 'a') && (ch <= 'z'))
                        && ((c >= 'a')
                        && (c <= 'z'))) {
                    flag = 1;
                }
                if (((ch == '+') || (ch == '*'))
                        && ((c == '+') || (c == '*'))) {
                    flag = 1;
                }
                if (((ch >= 'a') && (ch <= 'z'))
                        && ((c >= '0') && (c <= '9'))) {
                    flag = 1;
                }
                if (((c >= 'a') && (c <= 'z'))
                        && ((ch >= '0') && (ch <= '9'))) {
                    flag = 1;
                }
            }
            if ((i == 0) || (i == l - 1)) {
                if ((ch == '+') || (ch == '*')) {
                    flag = 1;
                }
            }
        }
        if (flag == 1) {
            System.out.println("illegal input!");
        } else {
            System.out.println(exp);
        }
    }

    /**
     * simplify.
     * @param n
     *            string
     * @param a
     *              string
     */
    public static void simplify(final String n, final String a) {
        int[] d;
        d = new int[27];
        int l = n.length();
        int l2 = a.length();
        int i;
        for (i = 1; i <= 26; i++) {
            d[i] = 0;
        }
        for (i = 0; i < l; i++) {
            char m = n.charAt(i);
            if ((m >= 'a') && (m <= 'z')) {
                d[m - 96] = 1;
            }
        }
        i = 9;
        while (i < l2 - 1) {
            i++;
            char c = a.charAt(i);
            if ((c >= 'a') && (c <= 'z')) {
                int x;
                x = i + 2;
                int y;
                y = x;
                while (y < l2) {
                    if ((a.charAt(y) >= '0')
                        && (a.charAt(y) <= '9')) {
                        y++;
                    } else {
                        break;
                    }
                }
                y--;
                int k = 1;
                int s = 0;
                while (y - x >= 0) {
                    int e = 0;
                    e = a.charAt(y) - 48;
                    s = s + e * k;
                    y--;
                    k = k * 10;
                }
                d[a.charAt(x - 2) - 96] = -s;
            }
        }
        for (i = 0; i < l; i++) {
            char c = n.charAt(i);
            if (((c >= 'a') && (c <= 'z')) && (d[c - 96] < 0)) {
                System.out.print(-d[c - 96]);
            } else {
                System.out.print(c);
            }
        }
        System.out.println();
    }
}