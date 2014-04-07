import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
	public static int[] getPartialMatch(String needle) {
		int nLen=needle.length();
		int[] pi=new int[nLen];

		int begin=1, matched=0;
		while(begin+matched<nLen) {
			if(needle.charAt(begin+matched) == needle.charAt(matched)) {
				++matched;
				pi[begin+matched-1]=matched;
			} else {
				if(matched==0) {
					++begin;
				} else {
					begin+=matched-pi[matched-1];
					matched=pi[matched-1];
				}
			}
		}

		return pi;
	}

	public static List<Integer> getPrefixSuffix(String needle) {
		List<Integer> ret=new ArrayList<Integer>();
		int[] pi=getPartialMatch(needle);

		int k=needle.length();
		while(k>0) {
			ret.add(k);
			k=pi[k-1];
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		String a=br.readLine();
		String b=br.readLine();

		String input=a+b;

		List<Integer> ret=getPrefixSuffix(input);
		Collections.sort(ret);

		StringBuilder sb=new StringBuilder();
		for(Integer r : ret) {
			sb.append(r);
			sb.append(" ");
		}
		System.out.println(sb);
	}
}
