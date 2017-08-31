/*Sai Ranganathan

This program creates a question tree object that allows the user to play a game
of questions which learns the users responses by storing it in as data until it 
can correctly guess the object the user is thinking of*/

import java.util.*;
import java.io.*;
import java.awt.*; //for dimensions
import java.awt.event.*; //for events 
import javax.swing.*; //for guis components
import javax.swing.event.*; // for mouse events
import java.awt.event.ActionListener;


public class QuestionTreeOne implements ActionListener {
   private QuestionNode overallRoot;
   private Scanner console;
   private JFrame frame4;
   private JButton yes1;
   private JButton no1;
   private JButton yes2;
   private QuestionNode object;
   private JFrame frame5;
   private JFrame frame6;
   private JButton enter;
   private int x;
   private String question2;
   private boolean again;
   private JTextField word; 

   //creates the questionTree object and a scanner to read user input 
   public QuestionTreeOne() {
      console = new Scanner(System.in);
      overallRoot = new QuestionNode("computer");
      again = false; 
   }
   
   //method is used to read a file and store the information in the file to play 
   //the game of 20 questions.
   public void read(Scanner input) {
      overallRoot = readFile(overallRoot,input);
   }
   
   //takes in an question node object and a scanner object as parameters to read 
   //lines in the file and return the overallRoot questionNode which can
   //store all the data
   private QuestionNode readFile(QuestionNode overallRoot, Scanner input) {  
    while(input.hasNextLine()) {  
      String symbol = input.nextLine();
      String question = input.nextLine();
      if(symbol.equals("A:")) {
         overallRoot = new QuestionNode(question);
      } else {
         overallRoot = new QuestionNode(question);
         overallRoot.left = readFile(overallRoot.left,input);
         overallRoot.right = readFile(overallRoot.right,input);
      }
     }
      return overallRoot;
   }
   
   //method that takes in a printstream as a parameter and prints all the data stored
   //into a file
   public void write(PrintStream output) {
      writeFile(overallRoot,output);
   }
   
   //method that takes in a questionNode object and a printstream as parameters
   //and then reads all the data stored to print it back out into a file
   private void writeFile(QuestionNode overallRoot, PrintStream output) {
      if(overallRoot.right == null && overallRoot.left == null) {
         output.println("A:");
         output.println(overallRoot.data);
      } else {
         output.println("Q:");
      output.println(overallRoot.data);
      writeFile(overallRoot.left,output);
      writeFile(overallRoot.right,output);
      }
   }
   
   //allows the user to play the game of questions by prompting the user and trying to
   //guess what object the user is thinking about 
   public void askQuestions() {
      overallRoot = askUser(overallRoot);
   }
   
   //method that takes in a questionNode object as a parameter and modifies the 
   //data stored everytime the game is unable to guess the user's object. It returns 
   //back a questionNode object and prompts the user all the set of questions 
   //until it can try to find the object the user is thinking about given the data
   //stored
   public QuestionNode askUser(QuestionNode overallRoot) {
      frame4 = new JFrame();
      frame4.setSize(new Dimension(410,700));
      frame4.setTitle("20 Questions");
      frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame4.setResizable(true);
      frame4.setVisible(true);
      frame4.setLayout(new FlowLayout());
   
      if(overallRoot.left == null && overallRoot.right == null) {
         String phrase = "Would your object happen to be " + overallRoot.data + "?";
         JLabel guess = new JLabel(phrase);
         frame4.add(guess);
         
         JPanel panel1 = new JPanel(new GridLayout(1,1));
         yes1 = new JButton("Yes");
         yes1.isEnabled();
         yes1.addActionListener(this);
         no1 = new JButton("No"); 
         no1.addActionListener(this);
         panel1.add(yes1);
         panel1.add(no1);
         frame4.add(panel1);
       } else {  
            if(!yesTo(overallRoot.data)) {
                  overallRoot.right = askUser(overallRoot.right);
               } else {
               overallRoot.left = askUser(overallRoot.left);
            }
         }
         
         return overallRoot;   
   }
   
   //actionPerformed method that interacts with GUI elements in the Frame.
   public void actionPerformed(ActionEvent event) {
   
   if( x == 0) {
      frame4.dispose();
      if(event.getSource() == yes1) {
      
         frame5 = new JFrame();
         frame5.setSize(new Dimension(410,700));
         frame5.setTitle("Word Guesser");
         frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame5.setResizable(true);
         frame5.setVisible(true);
         frame5.setLayout(new FlowLayout());
            
         JLabel right = new JLabel("Great, I got it right!");
         JPanel panel5 = new JPanel(new FlowLayout());
         panel5.add(right);
         frame5.add(panel5);
      } else if(event.getSource() == no1) {
      
               JFrame frame5 = new JFrame();
               frame5.setSize(new Dimension(410,700));
               frame5.setTitle("Word Guesser");
               frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame5.setResizable(true);
               frame5.setVisible(true);
               frame5.setLayout(new FlowLayout());
               
               JLabel name = new JLabel("What is the name of your object?");
               frame5.add(name);
               word = new JTextField(10);
               frame5.add(word);
               JPanel enterPanel = new JPanel(new FlowLayout());
               JButton enter = new JButton("Enter");
               enterPanel.add(enter);
               frame5.add(enterPanel);
               String wording = word.getText();
               System.out.println(wording);
               enter.addActionListener(this);
               x++;
                              
          }
       } else if(x==1) {
       
               String wording = word.getText(); 
               object = new QuestionNode(wording);
               System.out.println(wording);
               
               frame6 = new JFrame();
               frame6.setSize(new Dimension(410,700));
               frame6.setTitle("Word Guesser");
               frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame6.setResizable(true);
               frame6.setVisible(true);
               frame6.setLayout(new FlowLayout());
               
               JPanel panel2 = new JPanel(new BorderLayout()); 
               JLabel difference1 = new JLabel("Give me a yes/no question that distinguishes your");
               JLabel difference2 = new JLabel("object and mine");
               panel2.add(difference1,BorderLayout.NORTH);
               panel2.add(difference2,BorderLayout.CENTER);
               frame6.add(panel2);
               
               JPanel center = new JPanel(new BorderLayout());
               JTextArea question1 = new JTextArea();
               center.add(question1,BorderLayout.NORTH); 
               String question2 = question1.getText();
               
               
               JLabel answer = new JLabel("And what is the answer for your question?");
               center.add(answer,BorderLayout.SOUTH);
               frame6.add(center);
               JPanel panel3 = new JPanel(new GridLayout(1,1));
               
               JButton yes2 = new JButton("Yes");
               JButton no2 = new JButton("No");
               panel3.add(yes2);
               panel3.add(no2);
               frame6.add(panel3);  
               yes2.addActionListener(this);
               x++;
                  
        } else if( x == 2) {

             if(event.getSource() == yes2) {
                  overallRoot = new QuestionNode(question2,object,overallRoot);
               } else {
                  overallRoot = new QuestionNode(question2,overallRoot,object);
               }
               
               frame6.setVisible(false);
               JFrame frame7 = new JFrame();
               frame7.setSize(new Dimension(410,700));
               frame7.setTitle("Word Guesser");
               frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame7.setResizable(true);
               frame7.setVisible(true);
               frame7.setLayout(new FlowLayout());
               
               
               JLabel again = new JLabel("Do you want to go again?");
               frame7.add(again);
                
               JButton yes3 = new JButton("Yes"); 
               JButton no3 = new JButton("No"); 
               JPanel fourthPanel = new JPanel(new GridLayout(1,1));
               fourthPanel.add(yes3);
               fourthPanel.add(no3);
               frame7.add(fourthPanel);
               yes3.addActionListener(this);
               x++; 
         } else if(x == 3) {
               again = true;
               System.out.println(again);
               
               JFrame frame7 = new JFrame();
               frame7.setSize(new Dimension(410,700));
               frame7.setTitle("Word Guesser");
               frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame7.setResizable(true);
               frame7.setVisible(true);
               frame7.setLayout(new FlowLayout());
               
               JLabel think2 = new JLabel("Please think of an object for me to guess");
               frame7.add(think2);
               JButton cont3 = new JButton("Click to continue");
               frame7.add(cont3);
        }
              
   } 
               
   //returns whether the user wants to play the game again or not
   public boolean getAgain() {
      return again; 
   }               

   //method that prompts the user and reads the answer 
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }   
}
