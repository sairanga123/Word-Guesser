//class for storing a single node binary tree of strings 
public class QuestionNode {
   public String data; 
   public QuestionNode left;
   public QuestionNode right;
   
   //constructs a leaf node with the given data
   public QuestionNode(String data) {
      this(data,null,null);
   }
   
   //constructs a branch node with the given data, left subtree and right subtree
   public QuestionNode(String data, QuestionNode left, QuestionNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
   }
}
