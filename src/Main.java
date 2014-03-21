import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static int pick(ArrayList<Integer> picked, ArrayList<Integer> rest, int peopleCount) {
		if(picked.size()==peopleCount) {
			return 1;
		}

		int pickCount=0;
		int size=rest.size()/2;
		for(int i=0; i<size; i++) {
			int newpick1=rest.remove(0);
			int newpick2=rest.remove(0);

			if(!picked.contains(newpick1) && !picked.contains(newpick2)) { //둘다 없으면 등록
				ArrayList<Integer> newPicked=(ArrayList<Integer>)picked.clone();
				newPicked.add(newpick1);
				newPicked.add(newpick2);
				pickCount+=pick(newPicked, (ArrayList<Integer>)rest.clone(), peopleCount);
			}
		}

		return pickCount;
	}


	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

		int testcase=Integer.parseInt(in.readLine().trim());
		for(int a=0; a<testcase; a++) {
			String[] counts=in.readLine().split(" ");
			String[] relations=in.readLine().split(" ");
			int peopleCount=Integer.parseInt(counts[0].trim());
			int relationCount=Integer.parseInt(counts[1].trim());

			ArrayList<Integer> list=new ArrayList<Integer>(relationCount*2);
			if(relationCount>0) {
				for(int i=0; i<relations.length; i++) {
					list.add(Integer.parseInt(relations[i].trim()));
				}
				System.out.println(String.valueOf(pick(new ArrayList<Integer>(), list, peopleCount)));
			} else {
				System.out.println("0");
			}

		}
	}

	public static void makeGaraData() {
		//1개 입력
		System.out.println("1");
		System.out.println("10 0");
		System.out.println("");


		//50개 입력
	}
}