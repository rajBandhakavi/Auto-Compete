import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
 
/*
 * Auto-complete using tries in java
 */
public class Trie {
   
  public TrieNode root;
  public Trie(){
    root = new TrieNode(' ');
  }
   
  public void insertInTrie(String str){
    int strLen = str.length();
    if (strLen == 0) {
      root.isEndOfString = true;
    } else {
      int i=0;
      TrieNode current = root, child=null;
      while (i&amp;lt;strLen){
        child = current.subNode(str.toLowerCase().charAt(i));
        if (child == null){
          child = new TrieNode(str.toLowerCase().charAt(i));
          current.child.add(child);
        }
        current = child;
        i++;
      }
      current.isEndOfString  = true;
      current.leafNodeStr = str.toLowerCase();
    }
  }
   
  public TrieNode getLocationOfStringInTrie(String str){
    TrieNode current = this.root, child;
    int strLen = str.length();
    int i=0;
    while(i&amp;lt;strLen){
      child = current.subNode(str.toLowerCase().charAt(i));
      if (child != null) {
        current = child;
      } else {
        return null;
      }
      i++; 
    }
    if ( i == strLen){
      return current;
    }
    return null;
  }
   
  public void autocomplete(TrieNode node){
     
    if (node.isEndOfString) {
      System.out.println("-"+node.leafNodeStr);
    }
    for (TrieNode childNode : node.child){
      autocomplete(childNode);
    }
         
  }
   
  public boolean searchInTrie(String str){
    int strLen = str.length();
    if (strLen == 0){
      return true;
    }else {
      int i=0;
      TrieNode current = root, child =  null;
      while (i&amp;lt;strLen) {
        child = current.subNode(str.charAt(i));
        if (child != null) {
          current = child;
        }else {
          return false;
        }
        i++;
      }
      if (i == strLen) {
        return true;
      }
    }
    return false;
  }
     
  public class TrieNode {
    public char data;
    public boolean isEndOfString;
    public Collection&amp;lt;TrieNode&amp;gt; child;
    public String leafNodeStr;
     
    public TrieNode(char data){
      this.data = data;
      child = new LinkedList&amp;lt;TrieNode&amp;gt;();
      this.isEndOfString = false;
    }
     
    public TrieNode subNode(char data){
      if (child != null) {
        for (TrieNode childNode : child) {
          if (childNode.data == data){
            return childNode;
          }
        }
      }
      return null;
    }
  }
   
  public static void main(String[] args) throws IOException {
    Trie trie = new Trie();
    /*
    Use the below template to insert data into Trie.
    trie.insertInTrie("analyse"); 
    */
    
     
    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(in);
    String line;
    TrieNode location = null;
    System.out.println("Enter your keywords::");
    while (true) {
      line = br.readLine();
      if (line.equals("Y")){
        break;
      }
      location = trie.getLocationOfStringInTrie(line);
      if (location != null){
        trie.autocomplete(location);
      }else {
        System.out.println("No Match found for [ "+line+" ] in our data base");
      }
       
    }
     
  }
 
}