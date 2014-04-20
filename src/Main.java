import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;


public class Main {
	public static int[][] cache;
	public static String pattern;
	public static String find;
	
	public static boolean matches(int pPattern, int pFind) {
		//캐싱
		if(cache[pPattern][pFind]==1) {
			return true;
		} else if(cache[pPattern][pFind]==-1) {
			return false;
		}
		
		while(pPattern<pattern.length() && pFind<find.length() && (pattern.charAt(pPattern)==find.charAt(pFind) || pattern.charAt(pPattern)=='?')) {
			pPattern++;
			pFind++;
		}
		
		if(pPattern == pattern.length()) {
			cache[pPattern][pFind]=(pFind == find.length()) ? 1 : -1;
			return (pFind == find.length());
		}
		
		if(pattern.charAt(pPattern)=='*') {
			for(int i=pFind; i<=find.length(); i++) {
				if(matches(pPattern+1, i)) {
					cache[pPattern][pFind]=1;
					return true;
				}
			}
		}
		
		cache[pPattern][pFind]=-1;
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int testcase=Integer.parseInt(br.readLine());
		
		while(testcase-- > 0) {
			pattern=br.readLine();
			int filenameSize=Integer.parseInt(br.readLine());
			TreeSet<String> matchStrings=new TreeSet<String>();
			
			while(filenameSize-- > 0) {
				find=br.readLine();
				cache=new int[pattern.length()+1][find.length()+1];
				
				if(matches(0, 0)) {
					matchStrings.add(find);
				}
			}
			for(String matchString : matchStrings) {
				System.out.println(matchString);
			}
		}
	}
}
