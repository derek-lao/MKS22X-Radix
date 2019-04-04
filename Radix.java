import java.util.Arrays;

public class Radix{
  public static void radixsort(int[] data){
    int[] absoluteBuckets = new int[10];
    int[] nonabsoluteBuckets = new int[19];
    int largest = data[0];
    int magnitudeCounter = 0;
    MyLinkedList<Integer> everything = new MyLinkedList<Integer>();
    int magnitude10 = 1;
    for(int i = 0; i < data.length ; i ++)
    {
      if(Math.abs(largest) > Math.abs(data[i]))
      largest = data[i];
      everything.add(data[i]);
    }
    while((largest / 10) != 0) // if onedigit number, then stays 0.
    {
      magnitudeCounter ++;
      largest /= 10;
    }
    if(magnitudeCounter == 0)
    {
      magnitude10 = 0;
    }
    for(;magnitude10 <= magnitudeCounter;)
    {
      @SuppressWarnings("unchecked")
      MyLinkedList<Integer>[] posNegBuckets = new MyLinkedList[19];
      for(int i = 0; i < posNegBuckets.length; i ++)
      {
        posNegBuckets[i] = new MyLinkedList<Integer>();
      }
      int sizeHolder = everything.size();
      for(int i = 0; i < sizeHolder; i ++)
      {
        // System.out.println("everything is now " + everything.toString());
        // System.out.println("size of everything is " + everything.size());
        // System.out.println("the value at everything.get(0) is " + everything.get(0));
        Integer currentNumber = everything.removeFirst();
        // System.out.println("currentNumber is " + currentNumber);
        int digit;
        if(magnitude10 == 1)
        {
          digit = currentNumber % 10;
        }
        else if(magnitude10 > 1)
        {
          digit = (currentNumber % ((int) Math.pow(10, magnitude10))) - (currentNumber % ((int)Math.pow(10, magnitude10 - 1)));
          digit /= ((int) Math.pow(10, magnitude10 - 1));
        }
        else
        {
          digit = currentNumber;
        }
        if(Math.abs(digit) > 9)
        {
          System.out.println("You weren't supposed to see this: Math.abs(digit) can not be greater than 9. Instead it is " + digit);
        }
        // System.out.println("My digit is " + digit);
        // System.out.println("My posNegBuckets[digit + 9].toString(), assuming not null, is " + posNegBuckets[digit + 9]);
        posNegBuckets[digit + 9].add(currentNumber);//take the mod 10, then add to bucket index plus the modulo;
      }
      magnitude10 ++;
      // System.out.println("everything.size() is " + everything.size());
      if(everything.size() != 0 && magnitude10 <= magnitudeCounter)
      {
        System.out.println("You weren't supposed to see this: everything.size() should be 0 but instead it is " + everything.size());
      }
      for(int i = 0; i < posNegBuckets.length; i ++)//for loop to attach all those buckets and assign "everything" MyLinkedList to it
      {
        // System.out.println("Your everything bucket is " + everything.toString());
        // System.out.println("Your posNegBuckets[i] is " + posNegBuckets[i].toString());
        if(posNegBuckets[i].size() != 0)
        everything.extend(posNegBuckets[i]);
      }
      // System.out.println("Your everything bucket is " + everything.toString());
    }
  }

  public static void main(String[] args){//testing things based on my non-knowledge of some java
    // System.out.println("The -5 % 2 is what? " + (-5) % 2);
    // System.out.println("The -13 % 5 is what? " + (-13) % 5);
    // System.out.println("-13 / 10 is -2 or -1? " + (-13) / 10);
    int[] randomArray = new int[10];
    for(int i = 0 ; i < randomArray.length ; i ++)
    {
      randomArray[i] = ((int)(Math.random() * 1000000));//this is positive numbers ONLY
    }
    System.out.println("Your starting array is " + Arrays.toString(randomArray));
    radixsort(randomArray);
    int[] radixSorted = randomArray;//(should be sorted);
    Arrays.sort(randomArray);
    int[] systemSorted = randomArray;
    if(Arrays.equals(radixSorted,systemSorted))
    {
      System.out.println("You final array is " + Arrays.toString(radixSorted));
      System.out.println("The system's sorted array is " + Arrays.toString(systemSorted));
      System.out.println("You succeeded");
    }
    else
    System.out.println("You failed, you stupid hippo");
  }
}
