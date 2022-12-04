import java.util.ArrayList;
import java.util.List;

class Trie<T extends String>{
	
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	/**
	 * @param str
	 * @return Weather String exist or not in Trie
	 */
	public boolean isStringExist(String str) {
		TrieNode temp = this.root;
		for(char ch : str.toCharArray()) {
			if( !temp.isExistCharacter(ch) ) {
				return false;
			}
			temp = temp.getNextNode(ch);
		}
		return temp.isEnded;
	}
	
	/**
	 * @param str
	 * Inserts the String into trie
	 */
	public void insertString(String str) {
		TrieNode temp = this.root;
		for(char ch : str.toCharArray()) {
			if( !temp.isExistCharacter(ch) ) {
				TrieNode curNode = new TrieNode();
				temp.putNewTrieNode(curNode, ch);
			}
			temp.cnt++;
			temp = temp.getNextNode(ch);
		}
		temp.cnt++;
		temp.isEnded = true;
	}
	
	/**
	 * @param prefixStr
	 * Take String as input
	 * @return The number of String such that the prefix matches with given String
	 */
	public int findPrefixCount(String prefixStr) {
		TrieNode temp = this.root;
		for(char ch : prefixStr.toCharArray()) {
			if( !temp.isExistCharacter(ch) ) {
				return 0;
			}
			temp = temp.getNextNode(ch);
		}
		return temp.cnt;
	}
	
	/**
	 * 
	 * @param prefixStr
	 * @return List of strings with specified prefix
	 */
	public List<String> getListOfPrefixString(String prefixStr){
		List<String> allStrings = new ArrayList<>();
		TrieNode temp = this.root;
		for(char ch : prefixStr.toCharArray()) {
			if( !temp.isExistCharacter(ch) ) {
				return allStrings;
			}
			temp = temp.getNextNode(ch);
		}
		
		StringBuilder str = new StringBuilder(prefixStr);
		getAllStrings(temp, allStrings, str);
		return allStrings;
	}
	
	private void getAllStrings(TrieNode temp, List<String> allStrings, StringBuilder preString) {
	
		if( temp.isEnded ) {
			for(int i=0;i<temp.cnt; i++) allStrings.add(new String(preString));
			return ;
		}
		
		for(int i=0;i<256; i++) {
			TrieNode curNode = temp.trieNode[i];
			if(curNode != null) {
				preString.append((char)(i));
				getAllStrings(curNode, allStrings, preString);
				preString.deleteCharAt(preString.length() - 1);
			}
		}
		
	}
	
	static class TrieNode{
		final TrieNode trieNode[];
		boolean isEnded;
		int cnt;
		
		TrieNode(){
			trieNode = new TrieNode[256];
			isEnded = false;
			cnt = 0;
		}
		
		public boolean isExistCharacter(char ch){
			return this.trieNode[(int)ch] != null;
		}

		public TrieNode getNextNode(char ch){
			return this.trieNode[(char)ch];
		}

		public void putNewTrieNode(TrieNode node, char ch){
			this.trieNode[(char)ch] = node;
		}

		public boolean isWordEnded(){
			return this.isEnded;
		}
	}
}

public class Main {
	public static void main(String ar[]) {
		Trie<String> a = new Trie<>();
		
		a.insertString("javare gionformethods");
		a.insertString("javatp oint");
		a.insertString("javad ownload");
		a.insertString(" javascript");
		

		a.getListOfPrefixString("java").forEach(System.out::println);
		a.getListOfPrefixString("jaf").forEach(System.out::println);
		a.getListOfPrefixString("a").forEach(System.out::println);

		System.out.println(a.isStringExist("javadownload"));
		System.out.println(a.isStringExist("a"));
		System.out.println(a.isStringExist("javatpoint"));
		
		
	}
}
