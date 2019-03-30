public class Node{

  private Node next,prev;
  private Integer data;

  public Node(Integer element){
    data=element;
    next=null;
    prev=null;
  }

  public Node next(){
    return this.next;
  }
  public Node prev(){
    return this.prev;
  }
  public void setNext(Node other){
    this.next=other;
  }
  public void setPrev(Node other){
    this.prev=other;
  }
  public String toString(){
    return ""+this.get();
  }

  public Integer get(){
    return this.data;
  }
  public void set(Integer value){
    this.data=value;
  }

  public boolean hasNext(){
    return this.next()!=null;
  }
  public boolean hasPrev(){
    return this.prev()!=null;
  }
}
