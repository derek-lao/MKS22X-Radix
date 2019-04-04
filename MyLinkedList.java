public class MyLinkedList<E>{
  // we want the user to see the DATA in the node, but NOT THE NODE ITSELF
  private Node<E> start,end;
  private int length;

  public MyLinkedList(){
    length = 0;
    // initiate();
  }

  /** adds the specified element to the end of the list
  * @param value is the specified elmeent being added
  * I will attempt to change the end node to a different node,
  * with data as the element
  */
  public boolean add(E value){
    if(length == 0)
    {
      start = new Node<E>(value);
      end = start;
      start.setNext(end);
      start.setPrev(null);
      end.setPrev(start);
      end.setNext(null);
      initiate();
      length ++;
      return true;
    }
    Node<E> newEnd = new Node<E>(value);
    newEnd.setPrev(end);
    end.setNext(newEnd);
    end = newEnd;
    length ++;
    // System.out.println("Element to be added: "+value);
    // System.out.println("List now: "+this.toString());
    return true;
  }

  /**
  *@return the number of elements in this list
  */
  public int size(){
    return length;
  }

  /**
  *note you don't have get(index) yet, nor would you want to use it here
  *@return a string in the form of [element,element,element]
  */
  public String toString(){
    if(length == 0)
    return "[]";
    String answer="["+start.get();
    Node<E> current = start;
    int i = 1;
    while(current.hasNext() && i < length)
    {
      current = current.next();
      answer += (","+current.get());
      i ++;
    }
    return answer + "]";
  }

  /**
  *Returns the element at the specified position in this list.
  *@param index is the specified position
  *@return the element at that specified position
  */
  public E get(int index){
    if(index < 0 || index >= length)
    throw new IndexOutOfBoundsException();
    return this.getNode(index).get();
  }

  /**
  *replaces element at the specified position on the list with
  a specified value, and returns the previous value
  *@param index is the specified position
  *@param value is the element that is to replace the original value
  *@return the element previously at the specified positions
  */
  public E set(int index,E value){
    if(index < 0 || index >= length)
    throw new IndexOutOfBoundsException();
    E originalValue = this.getNode(index).get();
    this.getNode(index).set(value);
    return originalValue;
  }

  /**
  *check if the value is contained in the list at all
  @param value is the value that the user is going to check the whole list for
  @return true if the value is found, and false if the value has not been found
  */
  public boolean contains(E value){
    //we might use indexOf
    return indexOf(value) != -1;
  }

  /**
  *Returns the index of the first occurrence of the specified element
  in this list, or -1 if this list does not contain the element
  *@param value is the specified elements
  *@return the index of the first occurence, or -1 if the element
  is not contained
  */
  public int indexOf(E value){
    Node<E> current = start;
    int i = 0;
    while(current.hasNext())
    {
      if(current.get() == value)
      {
        return i;
      }
      current = current.next();
      i ++;
    }
    return -1;
  }

  /**
  *Inserts the specified element at the specified position in this list.
  Shifts the element currently at that position (if any) and any
  subsequent elements to the right (adds one to their indices).
  *@param index is the specified position
  *@param value is the element to be inserted
  */
  public void add(int index,E value){
    if(index < 0 || index > length)
    throw new IndexOutOfBoundsException();
    // System.out.println("List before: "+this.toString());
    else if(index == length)
    this.add(value);
    else
    {
      Node<E> current = this.getNode(index);
      // System.out.println("My data in my current node I am looking at is: " + current.get());
      // System.out.println("My data in my start node, which should also be my current node, is: " + start.get());
      // System.out.println("Does my current hasPrev? Expecting false: "+current.hasPrev());
      // System.out.println("Does my start hasPrev? Expecting false: "+start.hasPrev());
      Node<E> answer = new Node<E>(value);
      // System.out.println("Does my start hasPrev()? : " + start.hasPrev());
      // System.out.println("What is my start.prev().get()? : " + start.prev().get());
      // System.out.println("What is my start.get()? " + start.get());
      // System.out.println("Does my current hasPrev()? : " + current.hasPrev());
      if(current.hasPrev())
      {
        Node<E> currentPrev = current.prev();
        currentPrev.setNext(answer);
        answer.setPrev(currentPrev);
        current.setPrev(answer);
        answer.setNext(current);
      }
      else
      {
        answer = new Node<E>(value);
        // System.out.println("The data in this new start node is: "+start.get());
        current.setPrev(answer);
        answer.setNext(current);
        answer.setPrev(null);
        start = answer;
      }
      length ++;
    }
    // System.out.println("List after: "+this.toString());
  }

  /**
  *changes reference points in the code
  *remove Node at given index, returns removed element
  *@param index is the specified index
  *@return the removed element
  */
  public E remove(int index){
    if(index < 0 || index >= length)
    throw new IndexOutOfBoundsException();
    else{
      Node<E> current = this.getNode(index);
      E answer = current.get();
      if(index == length - 1)
      {
        Node<E> currentPrev = current.prev();
        end.set(currentPrev.get());
        end.setPrev(currentPrev.prev());
        end.setNext(null);
        length --;
        return answer;
      }
      if(index == 0)
      {
        return removeFirst();
      }
      else
      {
        Node<E> currentNext = current.next();
        Node<E> currentPrev = current.prev();
        currentPrev.setNext(currentNext);
        currentNext.setPrev(currentPrev);
        return answer;
      }
    }
  }

  /**
  *Removes the first occurrence of the specified element from this list,
  if it is present.
  If this list does not contain the element, it is unchanged
  *@param value is the element to be removed
  *@return true if the list includes the element, and false if not
  */
  public boolean remove(E value){
    if(this.contains(value))
    {
      this.remove(this.indexOf(value));
      return true;
    }
    else
    {
      return false;
    }
  } //indexOf() would also be useful

  public E removeFirst(){
    if(length > 1)
    {
      E value = start.get();
      start = start.next();
      start.setPrev(null);
      length --;
      return value;
    }
    else if(length == 1)
    {
      E value = start.get();
      start = null;
      end = null;
      length --;
      return value;
    }
    else
    {
      throw new IndexOutOfBoundsException();
    }
  }

  public E removeLast(){
    if(length > 1)
    {
      E value = end.get();
      end = end.prev();
      end.setNext(null);
      length --;
      return value;
    }
    else if(length == 1)
    {
      E value = start.get();
      start = null;
      end = null;
      length --;
      return value;
    }
    else
    {
      throw new IndexOutOfBoundsException();
    }
  }

  public void extend(MyLinkedList<E> other){
    //in O(1) runtime, move the elements from other onto the end of this
    //The size of other is reduced to 0
    //The size of this is now the combined sizes of both original lists
    if(this.length == 0)
    {
      this.start = other.start;
      this.end = other.end;
    }
    else
    {
      this.end.setNext(other.start);
      other.start.setPrev(this.end);
      this.end = other.end;
    }
    this.length = this.length + other.size();
    other.length = 0;
    other.start.setPrev(null);
    other.end.setNext(null);
  }

  // helper function to clearly set the start and next nodes
  private void initiate(){
    this.start.setPrev(null);
    this.end.setNext(null);
  }

  // helper function to loop through the linked list to get a node
  // at a specific index.  That node would be used for whatever it needs
  // to be used for in other methods, such as get(index) and set(value,index)
  private Node<E> getNode(int index){
    Node<E> current = start;
    int i = 0;
    while(i < index && current.hasNext())
    {
      current = current.next();
      i ++;
    }
    return current;
  }

  public static void main(String[] args){//for testing
    MyLinkedList<Integer> thing = new MyLinkedList<Integer>();
    for(Integer i = 0; i < 10; i ++)
    {
      thing.add(i);
    }
    Node<Integer> startNext = thing.start.next();
    System.out.println("thing.start.get() should be 0: " + thing.start.get());
    System.out.println("thing.start.next().prev().get() should be 0: " + thing.start.next().prev().get());
    System.out.println("thing.start.next().next().get() should be 2: " + thing.start.next().next().get());
    System.out.println("startNext.prev().get() should be 0; " + startNext.prev().get());
    System.out.println("startNext.next().get() should be 2; " + startNext.next().get());
  }

}
