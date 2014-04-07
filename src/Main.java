import java.util.ArrayList;
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

	public static List<Integer> kmpSearch(String haystack, String needle) {
		int hLen=haystack.length();
		int nLen=needle.length();
		List<Integer> ret=new ArrayList<Integer>();

		//pi[i]=needle[..i]의 접미사&&접두사가 되는 문자열의 최대 길이
		int[] pi=getPartialMatch(needle);

		int begin=0, matched=0;
		while(begin <= hLen-nLen) {
			if(matched<nLen && haystack.charAt(begin+matched)==needle.charAt(matched)) {
				++matched;
				if(matched==nLen) {
					ret.add(begin); //매칭되는 index들을 return
				}
			} else {
				if(matched==0) {
					++begin;
				} else {
					begin+=matched-pi[matched-1];
					matched=pi[matched-1];
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		List<Integer> ret=kmpSearch("qweraabaabacsdfssd", "aabaabac");
		System.out.println(ret);
		List<Integer> ret2=kmpSearch("qwerabcdefghabcdefgh", "abcdefgh");
		System.out.println(ret2);
	}
}
