import java.util.*;
import java.io.*;
public class BlackJackDriver{
   public static void main(String[]args) throws IOException{
      boolean play=true;
      Deck cards=new Deck(1);
      Scanner input=new Scanner(System.in);
      boolean doubleDown=false;
      boolean stay=true;
      boolean dead=false;
      boolean first=true;
      int playerCount=0;
      Card pCard=new Card();
      int pAces=0;
      int dealerCount=0;
      int dAces=0;
      int winStreak=0;
      Card dCard= new Card();
         //start deal
      cards.shuffleDeck();
      System.out.println("How much money will you bring to the table?");
      int money=input.nextInt();
      if(money<=0){
         System.out.println("The casino kicks your broke a** out \"Come back when you got more money!\"");
         return ;}
      
      System.out.println("How much money will you bet per game?");
      int bet=input.nextInt();
      while(play){
         if(money<bet){
            System.out.println("The casino kicks your broke a** out \"Come back when you got more money!\"");
            System.out.println("(You need enough money to pay the ante)");
            return ;}
         System.out.println("Your win streak: "+winStreak);
         if(winStreak>4){
            System.out.println("The Dealer thinks you've played enough tonight");
            System.out.println("The bouncers politely suggest you leave");
            System.out.println("$"+money+" ain't much, but it's honest work.");
            return;}
      
         //This is the tasty card dealing
         dealerCount=0;
         dAces=0;
         dCard=cards.removeCard();
         if (cardValue(dCard.getRank())==11)
            dAces++;
         dealerCount+=cardValue(dCard.getRank());
         System.out.println("Dealer has "+dCard+" (Other is face down)");
         //do second card later
         
         //player cards
         playerCount=0;
         pAces=0;
         pCard=cards.removeCard();
         if (cardValue(pCard.getRank())==11)
            pAces++;
         playerCount+=cardValue(pCard.getRank());
         System.out.println("Player has "+pCard);
         
         pCard=cards.removeCard();
         if (cardValue(pCard.getRank())==11)
            pAces++;
         playerCount+=cardValue(pCard.getRank());
         System.out.println("Player has "+pCard);
         //your stuff
         while(stay==true&&dead==false){
            if(playerCount>21){
               dead=true;
               break;}
            if(playerCount==0){
               playerCount=0;
               pAces=0;
               pCard=cards.removeCard();
               if (cardValue(pCard.getRank())==11)
                  pAces++;
               playerCount+=cardValue(pCard.getRank());
               System.out.println("Player has "+pCard);
            
               pCard=cards.removeCard();
               if (cardValue(pCard.getRank())==11)
                  pAces++;
               playerCount+=cardValue(pCard.getRank());
               System.out.println("Player has "+pCard);
               
            }
            System.out.println("The Dealer has: "+dealerCount);
            System.out.println("You have: "+playerCount);
            if(playerCount==21){
               System.out.println("Blackjack!!! You win triple the amount you bet");
               money+=(bet*3);
               System.out.println("You have: $"+money);
               stay=true;
               dead=true;
               winStreak++;
               break;}
            System.out.println("Press 1 to hit, Press 2 to check, Press 3 to double down");
            int ans=input.nextInt();
            while(ans!=1&&ans!=2&&ans!=3){
               System.out.println("try again bozo");
               ans=input.nextInt();
            }
            if(ans==1){
               pCard=cards.removeCard();
               if (cardValue(pCard.getRank())==11)
                  pAces++;
               playerCount+=cardValue(pCard.getRank());
               System.out.println("Player draws "+pCard);
               stay=true;
               doubleDown=false;}
            if(ans==2){
               stay=false;
               doubleDown=false;}
            if(ans==3){
               pCard=cards.removeCard();
               if (cardValue(pCard.getRank())==11)
                  pAces++;
               playerCount+=cardValue(pCard.getRank());
               System.out.println("Player draws "+pCard);
               stay=false;
               doubleDown=true;}
            System.out.println("You have: "+playerCount);
            if(stay==false)
               break;
            if(playerCount>21){
               while(pAces==1&&playerCount>21){
                  playerCount-=10;
                  pAces-=1;}
               if(playerCount>21){
                  dead=true;
                  System.out.println("You lost! (You lost because you had more than 21) The Dealer had: "+dealerCount);
                  money-=(bet);
                  if(doubleDown)
                     money-=bet;
                  System.out.println("You have: $"+money);
                  winStreak=0;
               }
            }
            if(playerCount==21){
               System.out.println("Blackjack!!! You win triple the amount you bet");
               money+=(bet*3);
               if(doubleDown)
                  money+=bet*3;
               System.out.println("You have: $"+money);
               stay=true;
               dead=true;
               winStreak++;
               break;}
         
                    
         }
         if(!dead){
            System.out.println("The Dealer reveals the face down card!");
            dCard=cards.removeCard();
            if (cardValue(dCard.getRank())==11)
               dAces++;
            dealerCount+=cardValue(dCard.getRank());
            System.out.println("Dealer has "+dCard);
            while(dealerCount<17&&playerCount<=21){
               System.out.println("The Dealer draws!");
               dCard=cards.removeCard();
               if (cardValue(dCard.getRank())==11)
                  dAces++;
               dealerCount+=cardValue(dCard.getRank());
               System.out.println("Dealer has "+dCard);
               System.out.println("The Dealer has: "+dealerCount);
            }
         }
         if(stay==false){
            while(dealerCount>21&&dAces>0){
               dealerCount-=10;
               dAces--;}
            if(dealerCount>playerCount&&dealerCount<21){
               dead=true;
               System.out.println("You lost! The Dealer had: "+dealerCount);
               money-=(bet);
               if(doubleDown)
                  money-=bet;
               System.out.println("You have: $"+money);
               winStreak=0;
            }/*
            else if(dealerCount==playerCount&&dealerCount==21)
               System.out.println("Congrats! You Won!(Even though you tied): The Dealer had: "+dealerCount);*/
            else if(dealerCount==21&&playerCount<dealerCount){
               System.out.println("You lost!(You had less than the Dealer); The Dealer had: "+dealerCount);
               money-=(bet);
               if(doubleDown)
                  money-=bet;
               System.out.println("You have: $"+money);
               winStreak=0;
            }
            else if(dealerCount==playerCount&&dealerCount<21){
               System.out.println("You lost!(Even though you tied); The Dealer had: "+dealerCount);
               money-=(bet);
               if(doubleDown)
                  money-=bet;
               System.out.println("You have: $"+money);
               winStreak=0;
            }
            else if(dealerCount>playerCount&&playerCount>21){
               System.out.println("You lost! (You lost because you had more than 21) The Dealer had: "+dealerCount);  
               money-=(bet);
               if(doubleDown)
                  money-=bet;
               System.out.println("You have: $"+money);
               winStreak=0;
            }
            else if(dead==true){
               System.out.println("You lost! You had more than 21 points");
               money-=(bet);
               if(doubleDown)
                  money-=bet;
               System.out.println("You have: $"+money);
               winStreak=0;
            }
               
            else {
               System.out.println("Congrats! You Won!: The Dealer had: "+dealerCount);
               money+=(bet);
               if(doubleDown)
                  money+=bet;
               System.out.println("You have: $"+money);
               winStreak++;
            }
         }
         System.out.println("Do you want to play again?(y/n)");
         String ans=input.next();
         while(!ans.equals("y")&&!ans.equals("n")){
            System.out.println("try again bozo");
            ans=input.next();
         }
         if(ans.equals("y")){
            play=true;
            stay=true;
            dead=false;
            System.out.println("There are "+cards.getArray().length+" cards left in the deck");
            System.out.println("");
            System.out.println("----------------------------------------------------------------");}
         if(ans.equals("n"))
            play=false;
      
      }
   }
   public static int cardValue(int x){
      if(x==1){
         return 11;
      }
      else if (10<x)
         return 10;
      else
         return x;
   }
}
