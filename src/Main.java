import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {
	public static TreeSet<String> answer=null; //순서 유지
	public static String[] pieces=null;
	public static int piecesLen=0;

	public static boolean isPDNA(StringBuffer piece) {
		int length=piece.length();
		for(int i=0; i<length/2; i++) {
			if(piece.charAt(i)!=piece.charAt(length-i-1)) {
				return false;
			}
		}
		return true;
	}

	public static void bruteForce(int left) {
		if(left==piecesLen-1) { //다 찾았으면
			StringBuffer flatString=new StringBuffer();
			for(int i=0; i<piecesLen; i++) {
				flatString.append(pieces[i]);
			}

			if(isPDNA(flatString)) {
				answer.add(flatString.toString());
			}
			return;
		} else {
			for(int i=left; i<piecesLen; i++) {
				String temp=pieces[left];
				pieces[left]=pieces[i];
				pieces[i]=temp;
				
				bruteForce(left+1);
				
				temp=pieces[left];
				pieces[left]=pieces[i];
				pieces[i]=temp;
			}
		}
	}

	//예시
	//10 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaa aaaaaaaa aaaaaaaaa aaa a a a a a
	// 10!은 3백만이 넘는다.
	public static void main(String[] args) throws IOException {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

		int testcase=Integer.parseInt(reader.readLine());

		while(testcase-- > 0) {
			StringTokenizer st=new StringTokenizer(reader.readLine());
			int numPieces=Integer.parseInt(st.nextToken());

			answer=new TreeSet<String>();
			pieces=new String[numPieces];
			piecesLen=0;
			
			while(st.hasMoreTokens()) {
				pieces[piecesLen++]=st.nextToken();
			}

			bruteForce(0);
			System.out.println(answer.first());
		}
	}
}
