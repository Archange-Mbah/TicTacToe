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
    int vertical=0;
    int horizontal=0;
      TicTac t= new TicTac();
    public void  draw(){
        g.background(0);
        t.draw(super.g);
        g.noFill();
        g.stroke(0,0,255);
        g.rect(0+300*horizontal,0+300*vertical,300,300);
        
        
        
    }



    public void keyPressed(){
        if(keyCode==DOWN) vertical=(vertical+1)%3;
           if(keyCode==UP) vertical=(vertical-1+3)%3;
        if(keyCode==RIGHT) horizontal=(horizontal+1)%3;
        if(keyCode==LEFT) horizontal=(horizontal-1+3)%3;
        if(keyCode==ENTER) {
            if(t.board[vertical*3+horizontal]==0 && !t.GameOver()){
            t.move(vertical*3+horizontal);
    

            if(!t.GameOver()){
            int pos=t.rand.nextInt(9);
            while(t.board[pos]!=0){
                pos=t.rand.nextInt(9);
            }
            t.move(pos);

    
}
}

        }
    }
}




    

class TicTac{
     int[] board = new int[9];
    
     int turn=1;
     

     Scanner scan = new Scanner(System.in);
     Random rand = new Random();
     int winner=0;
     public void move(int pos){
        assert pos>=0 && pos<9;
        if(board[pos]==0){
            board[pos]=turn;
            turn=turn*-1;
        }
        

}
 public boolean GameOver(){
     if(board[0]==board[1] && board[1]==board[2] && board[0]!=0){
         System.out.println("Player "+board[0]+" wins");
     
     return true;}
     else if(board[3]==board[4] && board[4]==board[5] && board[3]!=0){
         System.out.println("Player "+board[3]+" wins");
     return true;}
     else if(board[6]==board[7] && board[7]==board[8] && board[6]!=0){
         System.out.println("Player "+board[6]+" wins");
     return true;}
     else if(board[0]==board[3] && board[3]==board[6] && board[0]!=0){
         System.out.println("Player "+board[0]+" wins");
         return true;
     }
     else if(board[1]==board[4] && board[4]==board[7] && board[1]!=0){
         System.out.println("Player "+board[1]+" wins");
         return true;
     }
     else if(board[2]==board[5] && board[5]==board[8] && board[2]!=0){
         System.out.println("Player "+board[2]+" wins");
         return true;
     }
     else if(board[0]==board[4] && board[4]==board[8] && board[0]!=0){
         System.out.println("Player "+board[0]+" wins");
         return true;
     }
     else if(board[2]==board[4] && board[4]==board[6] && board[2]!=0){
         System.out.println("Player "+board[2]+" wins");
         return true;
     }
     else{
        
         return  false;
     }
 }
    public void printBoard(){
        for(int i=0;i<9;i++){
            System.out.print(board[i]+" ");
            if((i+1)%3==0){
                System.out.println();
            }
        }
    }
    public void play(){
        while(!GameOver()){
            System.out.println("Enter a valid position");
            int pos=scan.nextInt();
           move(pos);
            winner++;

            printBoard();
            System.out.println();
            System.out.println("Computer's turn");
            if(!GameOver()){
            int b=rand.nextInt(9);
            move(b);
            winner ++;
            printBoard();

        }
    }
    System.out.println("Game Over");
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