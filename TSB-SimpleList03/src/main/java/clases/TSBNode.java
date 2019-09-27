package clases;

public class TSBNode
{
   private Comparable info;
   private TSBNode next;
   
   public TSBNode ( )
   {
   }
   
   public TSBNode (Comparable x, TSBNode p)
   {
     info = x;
     next = p;
   }
   
   public TSBNode getNext()
   {
     return next;
   }
   
   public void setNext(TSBNode p)
   {
     next = p;
   }
   
   public Comparable getInfo()
   {
     return info;
   }
   
   public void setInfo(Comparable p)
   {
     if( p != null ) info = p;
   }

   @Override
   public String toString()
   {
     return info.toString();   
   }
}

