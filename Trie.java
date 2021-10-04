import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class Trie{
	public static TrieNode root;
	public static void main(String[] args) throws Exception{
		// to get input from user
		Scanner sc = new Scanner(System.in);
		
		// initializing Trie
		root = new TrieNode();
		String word;
		boolean user = true;
		// print operation numbers
		printOperations();
		while(user){
			System.out.println("Enter The Operation Number");
			int operationNumber = sc.nextInt();
			switch(operationNumber){
				case 1:
					System.out.println("Enter The Word");
					word = sc.next();
					insert(word.toLowerCase());
					break;

				case 2:
					word = sc.next();
					if(search(word.toLowerCase())){
						System.out.println(word + " exist in trie");
					}
					else{
						System.out.println(word + " does not exist in trie");
					}
					break;

				case 3: 
					user = false;
					break;
				
				default:
					System.out.println("Please Select Appropriate Operation Number");
			} 
		}
	}

	public static void printOperations(){
		System.out.println("1. Add Word");
		System.out.println("2. search Word");
		System.out.println("3. Terminate Program");
	}

	// insert into trie
	public static void insert(String word){
		TrieNode temp = root;
		for(char ch : word.toCharArray()){
			// the current character may already exist
			// may not be there
			if(!temp.isExistCharacter(ch)){
				// create new Node
				TrieNode newNode = new TrieNode();
				temp.putNewTrieNode(newNode, ch);
				// it will return nextNode 
				temp = temp.getNextNode(ch);
			}
		}
		temp.isEnded = true;
	}

	public static boolean search(String word){
		TrieNode temp = root;

		for(char ch : word.toCharArray()){
			if(temp.isExistCharacter(ch)){
				temp = temp.getNextNode(ch);
			}
			else{
				return false;
			}
		}
		return temp.isWordEnded();
	}

	public static class TrieNode{
		final TrieNode trieNode[];
		boolean isEnded;
		
		TrieNode(){
			trieNode = new TrieNode[26];
			isEnded = false;
		}

		public boolean isExistCharacter(char ch){
			return this.trieNode[ch - 'a'] != null;
		}

		public TrieNode getNextNode(char ch){
			return this.trieNode[ch - 'a'];
		}

		public void putNewTrieNode(TrieNode node, char ch){
			this.trieNode[ch- 'a'] = node;
		}

		public boolean isWordEnded(){
			return this.isEnded;
		}
	}
}


