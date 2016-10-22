package jdbc;

import java.util.Scanner;

/**
 * @author WeiSiDa
 */
public class Helloworld {

	/**
	 * deriative.
	 * 
	 * 
	 * @param nn
	 *            string
	 */
	public static void derivative(String nn, char aa) {
		String mm = "";
		int i = -1;
		int l, x, y, j, k, s;
		l = nn.length();
		x = 0;
		while (i < l - 1) {
			i++;
			if ((nn.charAt(i) == '+') || (i == l - 1)) {
				if (i == l - 1) {
					y = i;
				}
				else {
					y = i - 1;
				}
				k = 0;
				s = 1;
				// System.out.println(x);
				// System.out.println(y);
				for (j = x; j <= y; j++) {
					if (nn.charAt(j) == aa) {
						k++;
					}
				}
				// System.out.println(k);
				s = k;
				if (k > 0) {
					int pd = 0;
					for (j = x; j <= y; j++) {
						if ((nn.charAt(j) == aa) && (pd == 0)) {
							pd = 1;
							mm = mm + String.valueOf(s);
						} else {
							mm = mm + nn.charAt(j);
						}
					}
				}
				x = y + 1;
			}

		}
		for (i = 0; i < mm.length(); i++) {
			if ((!((i == mm.length() - 1) && (mm.charAt(i) == '+'))) && (!((i == 0) && (mm.charAt(i) == '+')))) {
				System.out.print(mm.charAt(i));
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
     * 
     * @param args
     *            string
     * @throws IOException 
     */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// 标志，避免使用while true
		int x = 0;
		String a = null;
		// 没用到
		String b = null;
		a = new String();
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
				}
				// 求导命令
				else if (n.charAt(1) == 'd') {
					derivative(a, n.charAt(4));
				}
				else {
					x = 1;
				}
			}
		}
	}

	
    /**
     * matrixToString.
     * 
     * @return string
     * 
     * @param a
     *            number
     *
     * @param b
     *            matrix
     */
	public static void pd(String a) {
		int x = 0;
		int l = a.length();
		int y = 0;
		// 遍历字符串,x=1不合法
		for (int i = 0; i < l; i++) {
			char b = a.charAt(i);
			// 是数字
			if ((b >= '0') && (b <= '9')) {
				y++;
			}
			else {
				y = 0;
			}
			if ((y > 1) && (a.charAt(i - y + 1) == '0')) {
				x = 1;
			}
			if ((!(((b >= '0') && (b <= '9')) || ((b >= 'a') && (b <= 'z')))) && (b != '*') && (b != '+')) {
				x = 1;
			}
			if (i > 0) {
				char c = a.charAt(i - 1);
				if (((b >= 'a') && (b <= 'z')) && ((c >= 'a') && (c <= 'z'))) {
					x = 1;
				}
				if (((b == '+') || (b == '*')) && ((c == '+') || (c == '*'))) {
					x = 1;
				}
				if (((b >= 'a') && (b <= 'z')) && ((c >= '0') && (c <= '9'))) {
					x = 1;
				}
				if (((c >= 'a') && (c <= 'z')) && ((b >= '0') && (b <= '9'))) {
					x = 1;
				}
			}
			if ((i == 0) || (i == l - 1)) {
				if ((b == '+') || (b == '*')) {
					x = 1;
				}
			}
		}
		if (x == 1) {
			System.out.println("illegal input!");
		}
		else {
			System.out.println(a);
		}
	}

    /**
     * simplify.
     * 
     * @return simplified string
     * 
     * @param command1
     *            string
     */
	public static void simplify(String n, String a) {
		int d[];
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
					if ((a.charAt(y) >= '0') && (a.charAt(y) <= '9')) {
						y++;
					}
					else {
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
			}
			else {
				System.out.print(c);
			}
		}
		System.out.println();
	}
}