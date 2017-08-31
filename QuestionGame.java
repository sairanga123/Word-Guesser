/* Sai Ranganathan 
creates the QuestionGame object which sets up the GUI and uses a QuestionTreeOne object*/

import java.awt.*; //for dimensions
import java.awt.event.*; //for events 
import javax.swing.*; //for guis components
import java.io.*;
import java.util.*;

public class QuestionGame implements ActionListener { 
     public static final String DICTIONARY = "dictionary.txt"; 
     private JFrame frame;
     private JFrame frame2;
     private JFrame frame3;
     private JFrame frame4;
     private boolean button;
     private QuestionTreeOne questions = new QuestionTreeOne();
     int x = 0;
     
   public static void main(String[] args)  {
         QuestionGame myFrame = new QuestionGame();
   }
       
   public QuestionGame() { 
      button = false;
      
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(410,700));
      frame.setBackground(Color.BLACK);
      frame.setTitle("Word Guesser");
      frame.setResizable(true);
      frame.setVisible(true);
      frame.setLayout(new FlowLayout());
      
      JLabel questionLabel = new JLabel("Word Guesser");
      questionLabel.setPreferredSize(new Dimension(380,550));
      questionLabel.setIcon(new ImageIcon("background.jpg"));
         
      JPanel backgroundPanel2 = new JPanel(new BorderLayout());
      JLabel welcome = new JLabel("Welcome to Word Guesser!");
      welcome.setFont(new Font("Times", Font.BOLD, 20));
      backgroundPanel2.add(welcome, BorderLayout.NORTH);
      JButton start = new JButton("Start Game");
      start.setPreferredSize(new Dimension(100,50));
      start.addActionListener(this);
      backgroundPanel2.add(start, BorderLayout.SOUTH);
       
      frame.add(questionLabel);
      frame.add(backgroundPanel2);
      
    } 
   
   public void actionPerformed(ActionEvent event){
     JButton yes = new JButton("yes");
     JButton no = new JButton("no");
     JButton cont2 = new JButton("Click to continue");
      if (x == 0) { 
         x++;
         frame.dispose();
         frame2 = new JFrame();
         frame2.setSize(new Dimension(410,700));
         frame2.setTitle("Word Guesser");
         frame2.setResizable(true);
         frame2.setVisible(true);
         frame2.setLayout(new BorderLayout());
         
         JPanel topPanel = new JPanel(new FlowLayout());
         JPanel centerPanel = new JPanel(new FlowLayout());
         JLabel intro = new JLabel("Hello and Welcome to the AI Word Guesser game!");
         JLabel intro2 = new JLabel("This game requires you to think of");
         JLabel intro3 = new JLabel("a word and allow the computer to guess it.");
         JLabel intro4 = new JLabel("If the computer cannot guess your word, you");
         JLabel intro5 = new JLabel("can teach the program your word and play again.");
         JLabel click = new JLabel("Click continue.");
         topPanel.add(intro);
         centerPanel.add(intro2); 
         centerPanel.add(intro3);
         centerPanel.add(intro4);
         centerPanel.add(intro5);
         centerPanel.add(click); 
         frame2.add(topPanel,BorderLayout.NORTH);
         frame2.add(centerPanel,BorderLayout.CENTER);
         
         JButton cont = new JButton("Continue");
         frame2.add(cont,BorderLayout.SOUTH); 
         cont.addActionListener(this);
      } else if (x==1) { 
           x++;
           frame2.dispose();
           frame3 = new JFrame();
           frame3.setSize(new Dimension(410,700));
           frame3.setTitle("20 Questions");
           frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame3.setResizable(true);
           frame3.setVisible(true);
           frame3.setLayout(new FlowLayout()); 
           
           JPanel thirdPanel = new JPanel(new FlowLayout());
           thirdPanel.setVisible(true);
           JLabel think = new JLabel("Think of any word that you would like me to guess!");
           thirdPanel.add(think);
           frame3.add(thirdPanel);
           JPanel fourthPanel = new JPanel(new BorderLayout());
           JLabel thinkLabel = new JLabel();
           thinkLabel.setPreferredSize(new Dimension(281,287));
           thinkLabel.setIcon(new ImageIcon("think.jpg"));
           fourthPanel.add(thinkLabel,BorderLayout.NORTH);
           
           fourthPanel.add(cont2,BorderLayout.SOUTH);
           frame3.add(fourthPanel); 
           cont2.addActionListener(this);
            
       } else if(x==2) {
            frame3.dispose();
            questions.askQuestions();
            button = questions.getAgain();
            if(button = true) {
               try { 
                        questions.write(new PrintStream(new File(DICTIONARY)));
                     } catch (FileNotFoundException e) {
               }
            }
            QuestionTreeOne questions2 = new QuestionTreeOne();
            questions2.askQuestions();
          
     }
    
   }
}
