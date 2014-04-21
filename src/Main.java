import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static int[] cache;
	public static String example="";
	public static int len;

	public static int classify(int pString, int length) {
		char beforeChar=example.charAt(pString), iChar;
		boolean isSame=true, isSequencial=true, is;
		char a=beforeChar, b=beforeChar;

		for(int i=pString+1; i<pString+length; i++) {
			iChar=example.charAt(i);

			if(isSame) {
				isSame=(iChar==beforeChar);
			}
			if(isSequencial) {
				isSequencial=(Math.abs(iChar-beforeChar)==1);
			}

			beforeChar=iChar;
		}

		if(isSame) {
			return 1;
		}

		if(isSequencial) {
			return 2;
		}

		return 10;
	}

	public static int memory(int pString) {
		if(pString==len) {//기저사례
			return 0;
		}

		//불가 사례
		if(len-pString<3) { //끝이 2, 1개 남은 경우
			return Integer.MAX_VALUE;
		}

		if(cache[pString]!=0) {
			return cache[pString]; //난이도는 1~10 사이
		}

		int min=Integer.MAX_VALUE;
		for(int i=3; i<5; i++) {
			min=Math.min(min, memory(pString+i)+classify(pString, i));//min(현재지점+i이후의 난이도 + (현재지점~현재지점+i)의 난이도)
		}

		cache[pString]=min;
		return min;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int testcase=Integer.parseInt(br.readLine());
		while(testcase-- > 0) {
			example=br.readLine();
			len=example.length();
			cache=new int[example.length()+1];
			System.out.println(memory(0));
		}
	}
}
