import java.util.Arrays;

public class Radix{
  public static void radixsort(int[] data){
    int largest = data[0];
    int magnitudeMax = 0;
    MyLinkedList<Integer> everything = new MyLinkedList<Integer>();
    int magnitude10 = 1;
    // System.out.println("Largest number before passing through array is " + largest);
    for(int i = 0; i < data.length ; i ++)
    {
      if(Math.abs(largest) < Math.abs(data[i]))
      {
        largest = data[i];
      }
      everything.add(data[i]);
      // System.out.println("Largest has become " + largest);
    }
    // System.out.println("Largest number is " + largest);
    while((largest / 10) != 0) // if onedigit number, then stays 0.
    {
      magnitudeMax ++;
      largest /= 10;
    }
    // System.out.println("magnitudeMax is " + magnitudeMax);
    if(magnitudeMax == 0)
    {
      magnitude10 = 0;
    }
    for(;magnitude10 <= magnitudeMax + 1;)
    {
      @SuppressWarnings("unchecked")
      MyLinkedList<Integer>[] posNegBuckets = new MyLinkedList[19];
      for(int i = 0; i < posNegBuckets.length; i ++)
      {
        posNegBuckets[i] = new MyLinkedList<Integer>();
      }
      int sizeHolder = everything.size();
      for(int i = 0; i < sizeHolder; i ++) // sizeHolder is 0??
      {
        Integer currentNumber = everything.removeFirst();
        int digit;
        // System.out.println("magnitude10 is " + magnitude10);
        // System.out.println("magnitudeMax is " + magnitudeMax);
        if(magnitude10 == 1)
        {
          digit = currentNumber % 10;
        }
        else if(magnitude10 > 1)
        {
          digit = (currentNumber % ((int) Math.pow(10, magnitude10))) - (currentNumber % ((int)Math.pow(10, magnitude10 - 1)));
          digit /= ((int) Math.pow(10, magnitude10 - 1));
        }
        // else if(magnitude10 == magnitudeMax && magnitudeMax != 0)
        // {
        //   digit = currentNumber - (currentNumber % ((int)Math.pow(10, magnitude10)));
        //   digit /= ((int) Math.pow(10, magnitude10));
        // }
        else //if magntiudeMax == 0
        {
          digit = currentNumber;
        }
        if(Math.abs(digit) > 9)
        {
          System.out.println("You weren't supposed to see this: Math.abs(digit) can not be greater than 9. Instead it is " + Math.abs(digit));
        }
        // System.out.println("My digit is " + digit);
        // System.out.println("My posNegBuckets[digit + 9].toString(), assuming not null, is " + posNegBuckets[digit + 9]);
        posNegBuckets[digit + 9].add(currentNumber);//take the mod 10, then add to bucket index plus the modulo;
      }
      magnitude10 ++;
      // System.out.println("everything.size() is " + everything.size());
      if(everything.size() != 0 && magnitude10 <= magnitudeMax)
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
    for(int i = 0; i < data.length; i ++)
    {
      data[i] = everything.removeFirst();
    }
    // System.out.println("magnitudeMax is (should be 8): " + magnitudeMax); // should be 8
  }

  public static void main(String[] args){//testing things based on my non-knowledge of some java
    // System.out.println("The -5 % 2 is what? " + (-5) % 2);
    // System.out.println("The -13 % 5 is what? " + (-13) % 5);
    // System.out.println("-13 / 10 is -2 or -1? " + (-13) / 10);
    // int[] randomArray1 = new int[10];
    // int[] randomArray2 = new int[10];
    // for(int i = 0 ; i < randomArray.length ; i ++)
    // {
    //   int randomParity = ((int)(Math.random() * 10)) % 2;
    //   int randomNumber = ((int)(Math.random() * 1000000000));
    //   if(randomParity == 1)
    //   randomNumber *= -1;
    //   randomArray1[i] = randomNumber;
    //   randomArray2[i] = randomArray1;
    // }
    int[] randomArray1 = {208873134, 29480117, -326454636, 507927922, 263222758, 793752583, -520564150, -756239038, -48751804, 196604064};
    int[] randomArray2 = new int[10];
    for(int i = 0; i < randomArray1.length ; i ++)
    {
      randomArray2[i] = randomArray1[i];
    }
    System.out.println("Your starting array is " + Arrays.toString(randomArray1));
    radixsort(randomArray1);
    System.out.println();
    // System.out.println("Your radix sorted random array is " + Arrays.toString(randomArray));
    int[] radixSorted = randomArray1;//(should be sorted);
    System.out.println("Your radixSorted is " + Arrays.toString(radixSorted));
    Arrays.sort(randomArray2);
    // System.out.println("Your system sorted random array is " + Arrays.toString(randomArray));
    int[] systemSorted = randomArray2;
    System.out.println("Your systemSorted is " + Arrays.toString(systemSorted));
    if(Arrays.equals(radixSorted,systemSorted))
    {
      System.out.println("You radix sorted array is " + Arrays.toString(radixSorted));
      System.out.println("The system's sorted array is " + Arrays.toString(systemSorted));
      System.out.println("You succeeded");
    }
    else
    System.out.println("You failed, you stupid hippo");
    // System.out.println("4 % 5 is 0 or 4? " + 4 % 5);
  }




}
