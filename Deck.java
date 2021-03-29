public class Deck{
   private Card[] cardDeck;
   
   public Deck(int c){
      cardDeck=new Card[c*52];
      for(int i=0;i<cardDeck.length;i++){
         cardDeck[i] = new Card((i%13)+1,i/13);
      }
   }
   
   public Card[] getArray(){
      return cardDeck;
   }
   
   public void shuffleDeck(){
      for (int i=0;i<cardDeck.length*4;i++){
         Card temp = new Card();
         int chosen=(int)(Math.random()*cardDeck.length);
         temp=cardDeck[chosen];
         cardDeck[chosen]=cardDeck[0];
         cardDeck[0]=temp;
      }
   }
   
   public Card removeCard(){
      Card temp = cardDeck[0];
      Card[] newDeck= new Card[cardDeck.length-1];
      for(int i=1;i<cardDeck.length;i++){
         newDeck[i-1]=cardDeck[i];
      }
      cardDeck=newDeck;
      return temp;
   }
   /*public String toString(){
      String wave="";
      for(int i=0;i<cardDeck.length;i++){
         wave+=((cardDeck[i].toString())+"\n");
      }
     return wave;
   }*/
   
   
}