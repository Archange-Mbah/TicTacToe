import java.util.*;

 public class Tictactoe{
 
    private int board[]=new int[9];
    
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

}
 

    



  