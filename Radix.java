public class Radix{
  public static void radixsort(int[] data){
    int[] absoluteBuckets = new int[10];
    int[] nonabsoluteBuckets = new int[19];
    // int[] posNegBuckets = new int[19];
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] posNegBuckets = new MyLinkedList[19];
    MyLinkedList<Integer> everything = new MyLinkedList<Integer>();
    int magnitude10 = 1;
    for(int i = 0; i < everything.size(); i ++, magnitude10 ++)
    {
      Integer currentNumber = everything.get(0);
      int digit;
      if(magnitude10 < 1)
      {
        digit = currentNumber % 10;
      }
      else
      {
        digit = currentNumber % ((int)Math.pow(10,magnitude10)) - currentNumber % ((int)Math.pow(10,magnitude10 - 1));
      }
      posNegBuckets[digit + 9].add(currentNumber);//take the mod 10, then add to bucket index plus the modulo;
    }
    for(int i = 0; i < posNegBuckets.length; i ++)//for loop to attach all those buckets and assign "everything" MyLinkedList to it
    {
      everything.extend(posNegBuckets[i]);
    }
  }

  public static void main(String[] args){//testing things based on my non-knowledge of some java
    System.out.println("The -5 % 2 is what? " + (-5) % 2);
    System.out.println("The -13 % 5 is what? " + (-13) % 5);
    System.out.println("-13 / 10 is -2 or -1? " + (-13) / 10);
  }
}
