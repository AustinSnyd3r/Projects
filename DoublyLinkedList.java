import java.util.*;

class Node<T>{
   
   //fields for node class
   public T data;
   public Node next;
   public Node prev;
   
   //constructor for a node
   public Node(T data){
   
      this.data = data;
      this.next = null;
      this.prev = null;
   }
}

public class DoublyLinkedList<T>{

   //fields for head and tail
   Node head;
   Node tail;
   private int length;
   
   
   //METHODS 
   
   //insertAtFront(T data) - adds a new node at the front of the list.
   public void insertAtFront(T data){
      Node insertNode = new Node(data);
      
      if(head == null){ //for the empty list
         head = insertNode;
         tail = insertNode;
      }
      else{
         //new 1st node now points to old 1st node, then change the head pointer. 
         insertNode.next = head;
         head.prev = insertNode;
         head = insertNode;
         
      }
      //increment length
      length++;
     }
     
   /////////////////////////////////////////////////////////////////////////////////
   
   
   //insertAtEnd(T data)- adds a new node at the end of the list
   public void insertAtEnd(T data){
      Node insertNode = new Node(data);
      
      //empty list. head and tail are both the new node
      if(head == null){
         head = insertNode;
         tail = insertNode;
      }
      else{           
         tail.next = insertNode;    //connect tail node to new one
         insertNode.prev = tail;    //make prev connection from new to old tail
         tail = insertNode;         //set new node as tail
      }
      //increment length
      length++;
   }
   
   ///////////////////////////////////////////////////////////////////////////////////////////////
   
   
   //insertAtIndex(int index, T data) - adds a new node at the specified index location in the list.
   public void insertAtIndex(int index, T data){
      Node insertNode = new Node(data);
      
      Node temp = head;
      int counter = 0;
      
      //if its going in the front/end just call other method
      if(index == 0){
         insertAtFront(data);
      }   
      else if(index == length){
         insertAtEnd(data);       
      }     
      else if(index > 0 && index < length){
         
         //gets us to the correct location for the node
         while(counter < index - 1 && temp != null){
            //increment counter and move temp pointer
            temp = temp.next;
            counter++;
         }
      
         //make connection from new node to next node
         insertNode.next = temp.next;
         
         //make connection from new node to previous node
         insertNode.prev = temp.prev;
         
         //connect next node to new one
         temp.next.prev = insertNode;
         
         //connect prev node to new one
         temp.next = insertNode;
            
         length++;
       }       
    }
   ////////////////////////////////////////////////////////////////////////////////////////////
      
   //removeByIndex(int index) - removes the node from the list at the specified index location.
   public void removeByIndex(int index){
      
      Node temp = head; 
      int counter = 0;
      
      //if the node isnt there, and index out of bounds cases
      if(temp == null || index < 0 || index > length-1){
         System.out.println("No Node: removeByIndex(" + index + ")");
         return;
      }
      
      //we want to remove first node, just move head forward
      if(index == 0){
         head = temp.next;
         length--;
         System.out.println("removing from head");
      }
      //case for tail. set tail back one and cut off old node with .next = null;
      else if(index == length-1){
         tail = tail.prev;
         tail.next = null;      
         length--;
      }
      else{
                           
         while(counter < index-1 && temp != null){
            temp = temp.next;
            counter++;
         }
         
         //temp is on node before one we are removing, so change .next to skip node we remove
         temp.next = temp.next.next;
         //make connection from node after removed one back to temp
         temp.next.prev = temp;
                  
         //reduce length
         length--;
       }
   }
   
   /////////////////////////////////////////////////////////////////////////////////////////////////
   
     
   //removeByKey(T key) - removes a node from the list which contains the first occurrence of the key.
   public void removeByKey(T key){
      
      Node temp = head;
      
      //works without doing this. 
      String keytemp = key.toString();
        
      for(int i = 0; i <= length - 1; i++){
         //case for head. move head forward 1.
         if(keytemp.equals(temp.data.toString()) && i == 0){
            head = temp.next;
            length--;
         }
         //case for tail. set new tail and break connection
         if(keytemp.equals(temp.data.toString()) && i == length-1){
            tail = tail.prev;
            tail.next = null;
            length--;
         }      
         if(keytemp.equals(temp.data.toString()) && i > 0 && i <= length-1){
            
            //temp lands on node we remove.
            //previousnode.next is now pointing to removednode.next
            temp.prev.next = temp.next;
            //nextnode.prev is now pointing to removednode.prev
            temp.next.prev = temp.prev;
            
            //node is still in there. unaccesible... increment length -1
            length--;          
         }
         
         //go to next node
         temp = temp.next;        
       } 
  
   }
   
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   //contains(T key) - returns the index location of the node storing the key. Returns -1 if the key is not in the list.
   public int contains(T key){
      Node temp = head;
      
      //goes through all nodes. returns i as index if it finds it. 
      for(int i = 0; i <= length - 1; i++){                
         if(key == temp.data){
            return i;
         }         
         temp = temp.next;
       }
      
      //returns -1 if it isnt found
      return -1;
   }
   
   ///////////////////////////////////////////////////////////////////////////////////
   
   
   //set(int index, T data) - updates the data at the specified index location.
   public void set(int index, T data){
      Node temp = head; 
      int counter = 0;
      
      //goes through the nodes
      while(counter < index && temp != null){
         temp = temp.next;
         counter++;
      }
      //outofbounds, calls to return
      if(temp == null){
         System.out.println("OUTOFBOUNDS");
         return;
      }
      
      //sets the data.
      temp.data = data;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    

   //get(int index) - returns the data at the specified index location. If index is out of bounds, returns null.
   public T get(int index){
      Node temp = head;
      int counter = 0;
      
      //goes through the nodes
      while(counter < index && temp != null){
         temp = temp.next;
         counter++;
      }
      //outofbounds    
      if(temp == null){
         System.out.println("out of bounds (getter method)");
         return null;
      }
      
      //else
      return (T) temp.data;
   }
   
   //////////////////////////////////////////////////////////////////////////////////////////
   
   //size() - returns the length of the list.
   public int size(){
      return length;
   }
   
   ///////////////////////////////////////////////////////////////////////////////////////////
   
   //toString() - overwrites the toString method to print out all of the data in the list.
   public String toString(){
      StringBuilder str = new StringBuilder();      
      Node temp = head;     
      
      //goes through nodes and appends data to stringbuilder
      while(temp != null){
         str.append(temp.data);
         str.append(" , ");
         temp = temp.next;
      }
      //returns stringbuilder object as string
      return str.toString();
   }
   
   /////////////////////////////////////////////////////////////////////////////////////////////
}