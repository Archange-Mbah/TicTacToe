import java.util.Scanner;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PGraphics;



public class MySketch extends PApplet {
    public static void main(String[] args) {
	    PApplet.runSketch(new String[]{""}, new MySketch());
    }
    public void settings() {
        super.size(900, 900);
        
    }
    public void setup(){
        int pos=t.bestMove();
        t.makeMove(pos);
    }
    int vertical=0;
    int horizontal=0;
    Tictactoe t= new Tictactoe();
    public void  draw(){
        g.background(0);
        t.draw(super.g);
        g.noFill();
        g.stroke(0,0,255);
        g.rect(0+300*horizontal,0+300*vertical,300,300);
        
         
        if(t.isGameOver()){
            g.background(0);
            if(t.winner==1){
                
            } 
        }
        
    }



    public void keyPressed(){

         
        if(keyCode==DOWN) vertical=(vertical+1)%3;
           if(keyCode==UP) vertical=(vertical-1+3)%3;
        if(keyCode==RIGHT) horizontal=(horizontal+1)%3;
        if(keyCode==LEFT) horizontal=(horizontal-1+3)%3;
        if(keyCode==ENTER) {
            if(t.board[vertical*3+horizontal]==0 && !t.isGameOver()){
            t.makeMove(vertical*3+horizontal);
    

    
}
           if(!t.isGameOver()){
            int pos=t.bestMove();
   
             t.makeMove(pos);
          }
}

        }
    }




 public class Tictactoe{
 
    public int board[]=new int[9];
    
    public int turn=1;
    public int winner=1;
    int position;


    public void makeMove( int pos){
        assert pos>=0 && pos<9 && board[pos]==0;
        board[pos]=turn;
        turn=-turn;
    }
    public boolean  threeInRow(){
        if(sum(board[0],board[1],board[2])==3 || sum(board[0],board[1],board[2])==-3 ){ 
            winner=board[0];return true;
        }
        if(sum(board[3],board[4],board[5])==3 || sum(board[3],board[4],board[5])==-3){
          winner=board[3];
         return true;
        }
        if(sum(board[6],board[7],board[8])==3 || sum(board[6],board[7],board[8])==-3){
            winner=board[6];
             return true;
        }
        if(sum(board[0],board[3],board[6])==3 || sum(board[0],board[3],board[6])==-3){
          winner=board[0];
         return true;
        }
        if(sum(board[1],board[4],board[7])==3 || sum(board[1],board[4],board[7])==-3){
            winner=board[1];
             return true;
        }
        if(sum(board[2],board[5],board[8])==3 || sum(board[2],board[5],board[8])==-3){
            winner=board[2];
             return true;
        }
        if(sum(board[0],board[4],board[8])==3 || sum(board[0],board[4],board[8])==-3) {
            winner=board[0];
            return true;
        }
        if(sum(board[2],board[4],board[6])==3 || sum(board[2],board[4],board[6])==-3) {
            winner=board[2];
            return true;
        }
      
        
    
        return false;
}
   public boolean isGameOver(){
    if(threeInRow()) return true;
    else if(isFull()) {winner=0;
         return true;
        }
    return false;
   }
    
      public boolean isFull(){
        for(int i=0;i<9;i++){
            if(board[i]==0) return false;
        }
        return true;

      }
    public int sum(int a, int b,int c){
        return a+b+c;
    }
     Scanner scan = new Scanner(System.in);

  
     public void play() {
      
        while (!isGameOver()) {
           printBoard();
           if(turn==1){
            System.out.println("player 1");
            position=bestMove();
            makeMove(position);

           }
           else{
            System.out.println("player 2");
            position=scan.nextInt();
            makeMove(position);

           }

        }
        printBoard();
        if(winner==1){
            System.out.println("player 1 wins");
        }
        else if(winner==-1){
            System.out.println("2");
        }
        else {
            System.out.println("it's a tie");
        }
        
        
      }
      
    
     
  

   public void printBoard(){
    for(int i=0;i<9;i++){
        if(board[i]==1)
        System.out.print("x");
         if(board[i]==-1) System.out.print("O");
        if(board[i]==0) System.out.print("-");
     
        if((i+1)%3==0){
            System.out.println();
        }
    

   }
  }

   public int minimax(int depth, boolean isMax) {
    if (isGameOver()) {
        // Return the score of the game for the given player
        return winner;
    }
    int score=0;
        int bestScore=0;
    
    if (isMax) {
        // Maximizing player (AI)
         bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                // Make a move and evaluate its score
                board[i] = 1;
                 score = minimax(depth +1,false);
                // Undo the move
                board[i] = 0;
                if(score==1)return 1000;
                // Update the best score found so far
                bestScore = Math.max(score, bestScore);
            }
        }
        return bestScore;
    } else {
        // Minimizing player (human)
        bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                // Make a move and evaluate its score
                board[i] = -1;
                 score = minimax(depth + 1, true);
                // Undo the move
                board[i] = 0;
                if(score==-1) return -1000;
                // Update the best score found so far
                bestScore = Math.min(score, bestScore);
            }
        }
        return bestScore;
    }
}
  

public int bestMove() {
  int bestScore = Integer.MIN_VALUE;
  int bestMove = -1;
  
  for (int i = 0; i < 9; i++) {
      if (board[i] == 0) {
          // Make a move and evaluate its score
          board[i] = 1;
          if(checkWin(1)){
            board[i]=0;
        return i;
    }
          int score = minimax(0, false);
          // Undo the move
          board[i] = 0;
          // Update the best score and best move found so far
          if (score > bestScore) {
              bestScore = score;
              bestMove = i;
          }
      }
  }
  
  return bestMove;

   
}
 public boolean checkWin(int x){
    if(isGameOver() && winner==x) return true;
    return false;
 }


 public void draw(PGraphics g){
    for(int i=0;i<this.board.length;i++){
        if(board[i]==1){
            g.stroke(0,255,0);
            g.fill(0,255,0);
            g.line(0+300*(i%3),0+300*(i/3),300+300*(i%3),300+300*(i/3));
            g.line(300+300*(i%3),0+300*(i/3),0+300*(i%3),300+300*(i/3));
        }
        else if(board[i]==-1){
            g.stroke(255,0,0);
            g.fill(255,0,0);
            g.ellipse(150+300*(i%3),150+300*(i/3),300,300);
        }
        g.stroke(255);
        g.line(300,0,300,900);
        g.line(600,0,600,900);
        g.line(0,300,900,300);
        g.line(0,600,900,600);
       
    }
}

}
 

    



  