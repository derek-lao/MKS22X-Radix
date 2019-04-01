public class Node<E>{

  private Node<E> next,prev;
  private E data;

  public Node(E element){
    data=element;
    next=null;
    prev=null;
  }

  public Node<E> next(){
    return this.next;
  }
  public Node<E> prev(){
    return this.prev;
  }
  public void setNext(Node<E> other){
    this.next=other;
  }
  public void setPrev(Node<E> other){
    this.prev=other;
  }
  public String toString(){
    return ""+this.get();
  }

  public E get(){
    return this.data;
  }
  public void set(E value){
    this.data=value;
  }

  public boolean hasNext(){
    return this.next()!=null;
  }
  public boolean hasPrev(){
    return this.prev()!=null;
  }
}
