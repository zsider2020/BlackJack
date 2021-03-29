public class Card{
   private int suit;
   private int rank;
   private String name;
   
   public Card(){
      this(1,0);
   }
   public Card(int r, int s){
      suit=s;
      rank=r;
   }
   public void setSuit(int s){
      suit=s;
   }
   public void setRank(int r){
      rank=r;
   }
   public int getSuit(){
      return suit;
   }
   public int getRank(){
      return rank;
   }
   public String toString(){
   name="The ";
   
   if (rank==1)
   name+="Ace";
   else if (rank==11)
   name+="Jack";
   else if (rank==12)
   name+="Queen";
   else if (rank==13)
   name+="King";
   else
   name+=rank;
   
   name+=" of ";
   
   if (suit==0)
      name+="Spades";
   else if (suit==1)
      name+="Diamonds";
   else if (suit==2)
      name+="Clubs";
   else
      name+="Hearts";
   return name;
   }
}