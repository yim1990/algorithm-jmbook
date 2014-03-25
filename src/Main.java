import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;


public class Main {
	public static TreeSet<String> answer=new TreeSet<String>(); //순서 유지

	public static boolean isPDNA(StringBuffer piece) {
		int length=piece.length();
		for(int i=0; i<length/2; i++) {
			if(piece.charAt(i)!=piece.charAt(length-i-1)) {
				return false;
			}
		}
		return true;
	}

	public static int bruteForce(ArrayList<String> pieces, ArrayList<String> selected, int selectedStringLength) {
		int pSize=pieces.size();
		if(pSize==0) { //다 찾았으면
			StringBuffer flatString=new StringBuffer();
			for(String select : selected) {
				flatString.append(select);
			}

			if(isPDNA(flatString)) {
				answer.add(flatString.toString());
				return flatString.length();
			} else {
				return 987654321;
			}
		}

		int answerStringLength;
		for(int i=0; i<pSize; i++) {
			ArrayList<String> nPieces=(ArrayList<String>) pieces.clone();
			String picked=nPieces.remove(i);
			selected.add(picked);
			selectedStringLength+=picked.length();
			answerStringLength=bruteForce(nPieces, selected, selectedStringLength); //답을 찾았으면 작은 값이 나오고, 못찾았으면 무한대 값이 나온다.
			if(selectedStringLength*2 > answerStringLength) { //답이 있으면서, 진행상황이 절반을 넘었다면
				break;
			}

			selectedStringLength-=picked.length();
			selected.remove(selected.size()-1);
		}

		return 987654321;
	}

	//예시
	//10 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaa aaaaaaaa aaaaaaaaa aaa a a a a a
	// 10!은 3백만이 넘는다.
	public static void main(String[] args) throws IOException {
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

		int testcase=Integer.parseInt(reader.readLine());

		while(testcase-- > 0) {
			String[] line=reader.readLine().split(" ");
			int pieces=Integer.parseInt(line[0]);

			ArrayList<String> list=new ArrayList<String>();

			for(int i=1; i<line.length; i++) {
				list.add(line[i]);
			}

			bruteForce(list, new ArrayList<String>(),0);
			System.out.println(answer.first());
			answer.clear();
		}
	}
}
