package jdbc;

import java.util.Scanner;
public class helloworld 
{
	//检验表达式是否合法，输出表达式信息
	public static void pd(String a)
	{
		int x=0;
		int l=a.length();
		int y=0;
		//遍历字符串,x=1不合法
		for (int i=0; i<l; i++)
		{
			char b=a.charAt(i);
			//是数字
			if ((b>='0')&&(b<='9'))
				y++;
			else
				y=0;
			if ((y>1)&&(a.charAt(i-y+1)=='0'))
				x=1;
			if ((!(((b>='0')&&(b<='9'))||((b>='a')&&(b<='z'))))&&(b!='*')&&(b!='+'))
			{
				x=1;
			}
			if (i>0)
			{
				char c=a.charAt(i-1);
				if (((b>='a')&&(b<='z'))&&((c>='a')&&(c<='z')))
					x=1;
				if (((b=='+')||(b=='*'))&&((c=='+')||(c=='*')))
					x=1;
				if (((b>='a')&&(b<='z'))&&((c>='0')&&(c<='9')))
					x=1;
				if (((c>='a')&&(c<='z'))&&((b>='0')&&(b<='9')))
					x=1;
			}
			if ((i==0)||(i==l-1))
			{
			    if ((b=='+')||(b=='*'))
			    	x=1;
			}
	    }
		if (x==1)
			System.out.println("illegal input!");
		else
			System.out.println(a);
	}
	public static void simplify(String n,String a)
	{
    	int d[];
    	d=new int[27];
		int l=n.length();
		int l2=a.length();
    	int i;
    	for (i=1; i<=26; i++)
    	{
    		d[i]=0;
    	}
    	for (i=0; i<l; i++)
    	{
    		char m=n.charAt(i);
    		if ((m>='a')&&(m<='z'))
    			d[m-96]=1;
    	}
    	i=9;
    	while(i<l2-1)
    	{
    	    i++;
    		char c=a.charAt(i);
    	    if ((c>='a')&&(c<='z'))
    	    {
    	    	int x;
    	    	x=i+2;
    	        int y;
    	        y=x;
	    	    while (y<l2)
	    	    {
	    	        if ((a.charAt(y)>='0')&&(a.charAt(y)<='9'))
	    	        	y++;
	    	        else 
	    	        	break;
	    	    }
	    	    y--;
	    	    int k=1;
	    	    int s=0;
	    	    while(y-x>=0)
	    	    {
	    	        int e=0;
	    	        e=a.charAt(y)-48;
	    	    	s=s+e*k;
	    	        y--;
	    	        k=k*10;
	    	    }
	    	    d[a.charAt(x-2)-96]=-s;
    	    }
    	}
    	for (i=0; i<l; i++)
    	{
    	    char c=n.charAt(i);
    	    if (((c>='a')&&(c<='z'))&&(d[c-96]<0))
    	    	System.out.print(-d[c-96]);
    	    else
    	    	System.out.print(c);
    	}
    	System.out.println();
	}
	public static void derivative(String n,char a)
	{
		String m="";
		int i=-1;
	    int l,x,y,j,k,s;
	    l=n.length();
	    x=0;
	    while (i<l-1)
	    {
	        i++;
	        if ((n.charAt(i)=='+')||(i==l-1))
	        {
	        	if (i==l-1)
	        		y=i;
	        	else
	        		y=i-1;
	        	k=0;
	        	s=1;
	        	//System.out.println(x);
	        	//System.out.println(y);
	        	for (j=x; j<=y; j++)
	        	{
	        	    if (n.charAt(j)==a)
	        	        k++;	
	        	}
	        	//System.out.println(k);
                s=k;
	        	if (k>0)
	        	{
		        	int pd=0;
	        		for (j=x; j<=y; j++)
		        	{
		        	    if ((n.charAt(j)==a)&&(pd==0))
		        	    {
		        	    	pd=1;
		        	    	m=m+String.valueOf(s);
		        	    }
		        	    else
		        	    	m=m+n.charAt(j);
		        	}
	        	}
	        	x=y+1;
	        }
	        
	    }
	    for (i=0; i<m.length(); i++)
	    {
	    	if ((!((i==m.length()-1)&&(m.charAt(i)=='+')))&&(!((i==0)&&(m.charAt(i)=='+'))))
	    	    System.out.print(m.charAt(i));	
	    }
	    if (m.length()==0)
	    	System.out.print(0);
	    System.out.println();
	    	
	}
    public static void main(String[] args)
    {
    	Scanner in=new Scanner(System.in);
    	//标志，避免使用while true
        int x=0;
    	String a=null;
    	//没用到
    	String b=null;
    	a=new String();
    	//死循环
    	while(x==0)
    	{
    		//n用来保存输入的字符串
    		String n=in.nextLine();
    		//输入的不是命令
    	    if (n.charAt(0)!='!')
    	    {
    	    	a=n;
    	    	pd(a);
            }
    	    else
    	    {
    	    	//化简命令，a为表达式，n为表达式
    	    	if (n.charAt(1)=='s')
    	    		simplify(a,n);
    	    	//求导命令
    	    	else
    	    		derivative(a,n.charAt(4));
    	    }
    	}
    }
}