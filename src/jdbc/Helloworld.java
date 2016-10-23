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
        String strMM = "";
        int iter = -1;
        int lll;
        int xxx;
        int yyy;
        int jjj;
        int kkk;
        int sss;
        lll = exp.length();
        xxx = 0;
        while (iter < lll - 1) {
            iter++;
            if ((exp.charAt(iter) == '+') || (iter == lll - 1)) {
                if (iter == lll - 1) {
                    yyy = iter;
                } else {
                    yyy = iter - 1;
                }
                kkk = 0;
                sss = 1;
                // System.out.println(x);
                // System.out.println(y);
                for (jjj = xxx; jjj <= yyy; jjj++) {
                    if (exp.charAt(jjj) == flag) {
                        kkk++;
                    }
                }
                // System.out.println(k);
                sss = kkk;
                if (kkk > 0) {
                    int pd = 0;
                    for (jjj = xxx; jjj <= yyy; jjj++) {
                        if ((exp.charAt(jjj) == flag)
                                && (pd == 0)) {
                            pd = 1;
                            strMM = strMM
                            + String.valueOf(sss);
                        } else {
                            strMM = strMM + exp.charAt(jjj);
                        }
                    }
                }
                xxx = yyy + 1;
            }

        }
        for (iter = 0; iter < strMM.length(); iter++) {
            if (!(iter == strMM.length() - 1
                    && strMM.charAt(iter) == '+')
                    && !(iter == 0
                    && strMM.charAt(iter) == '+')) {
                System.out.print(strMM.charAt(iter));
            }
        }
        if (strMM.length() == 0) {
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
        int flag = 0;
        String newStr = new String();
        // 死循环
        while (flag == 0) {
            // n用来保存输入的字符串
            String str = in.nextLine();
            // 输入的不是命令
            if (str.charAt(0) != '!') {
                newStr = str;
                pd(newStr);
            } else {
                // 化简命令，a为表达式，n为表达式
                if (str.charAt(1) == 's') {
                    simplify(newStr, str);
                } else if (str.charAt(1) == 'd') {
                    derivative(newStr, str.charAt(4));
                } else {
                    flag = 1;
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
        int len = exp.length();
        int index = 0;
        // 遍历字符串,x=1不合法
        for (int i = 0; i < len; i++) {
            char ch = exp.charAt(i);
            // 是数字
            if (ch >= '0' && ch <= '9') {
                index++;
            } else {
                index = 0;
            }
            if (index > 1 && exp.charAt(i - index + 1) == '0') {
                flag = 1;
            }
            if ((!((ch >= '0' && ch <= '9')
                    || (ch >= 'a' && ch <= 'z')))
                    && ch != '*' && ch != '+') {
                flag = 1;
            }
            if (i > 0) {
                char c = exp.charAt(i - 1);
                if ((ch >= 'a' && ch <= 'z')
                        && (c >= 'a'
                        && c <= 'z')) {
                    flag = 1;
                }
                if ((ch == '+' || ch == '*')
                        && (c == '+' || c == '*')) {
                    flag = 1;
                }
                if ((ch >= 'a' && ch <= 'z')
                        && (c >= '0' && c <= '9')) {
                    flag = 1;
                }
                if ((c >= 'a' && c <= 'z')
                        && (ch >= '0' && ch <= '9')) {
                    flag = 1;
                }
            }
            if (i == 0 || i == len - 1) {
                if (ch == '+' || ch == '*') {
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
     * @param exp
     *            string
     * @param exp2
     *              string
     */
    public static void simplify(final String exp, final String exp2) {
        int[] nums;
        nums = new int[27];
        int len = exp.length();
        int len2 = exp2.length();
        int iter;
        for (iter = 1; iter <= 26; iter++) {
            nums[iter] = 0;
        }
        for (iter = 0; iter < len; iter++) {
            char m = exp.charAt(iter);
            if ((m >= 'a') && (m <= 'z')) {
                nums[m - 96] = 1;
            }
        }
        iter = 9;
        while (iter < len2 - 1) {
            iter++;
            char ch = exp2.charAt(iter);
            if ((ch >= 'a') && (ch <= 'z')) {
                int x;
                x = iter + 2;
                int yyy;
                yyy = x;
                while (yyy < len2) {
                    if (exp2.charAt(yyy) >= '0'
                        && exp2.charAt(yyy) <= '9') {
                        yyy++;
                    } else {
                        break;
                    }
                }
                yyy--;
                int kkk = 1;
                int sss = 0;
                while (yyy - x >= 0) {
                    int eee = 0;
                    eee = exp2.charAt(yyy) - 48;
                    sss = sss + eee * kkk;
                    yyy--;
                    kkk = kkk * 10;
                }
                nums[exp2.charAt(x - 2) - 96] = -sss;
            }
        }
        for (iter = 0; iter < len; iter++) {
            char ch1 = exp.charAt(iter);
            if (ch1 >= 'a' && ch1 <= 'z' && nums[ch1 - 96] < 0) {
                System.out.print(-nums[ch1 - 96]);
            } else {
                System.out.print(ch1);
            }
        }
        System.out.println();
    }
}