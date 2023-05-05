import java.util.Stack;
import java.util.StringTokenizer;
import java.lang.*;

public class EvaluateExpressions{
   //Function one: public String convertToPostFix(String str)
   public static String convertToPostFix(String s){
   
      //Variables, and other utility
      Stack<String> stack = new Stack<String>();
      StringBuilder postfix = new StringBuilder();          
      StringTokenizer str = new StringTokenizer(s," ");          
      String token = "";
      boolean flag = true;
      
      
      //go through while we have more tokens
      while(str.hasMoreTokens()){
         flag = true;
         token = str.nextToken();
      
         //if its a character it can get added to the string
         if(Character.isDigit(token.charAt(0))){
            postfix.append(token);
            postfix.append(" ");
         }
         //case for + and -, if empty pushes
         if(token.equals("+") || token.equals("-")){
            if(stack.empty()){
               stack.push(token);
            }
            else{
               while(!stack.empty() && flag != false){  
                  //+ and - have equal or less prec than any other operator. if theres one below we pop and append              
                  if(isOperator(token)){
                     postfix.append(stack.pop());
                     postfix.append(" ");                    
                  }
                  else{   
                     //if theres nothing change the flag               
                     flag = false;
                  }
               } 
               //push the token. 
               stack.push(token);
              }        
         }  
         if(token.equals("*") || token.equals("/")){
            if(stack.empty()){
               stack.push(token);
            }
            else{
               while(!stack.empty() && flag != false){
                  //only * and / have equal precedence as themselves, append and pop. 
                  if(stack.peek().equals("*") || stack.peek().equals("/")){
                     postfix.append(stack.pop());  
                     postfix.append(" ");                   
                  }
                  else{  
                     //if only +, or - or nothing below change flag, exit loop                 
                     flag = false;
                  }
               }
               //push the token
               stack.push(token);
            }
         }    
    }
    
    //go through stack and append 
    while(!stack.empty()){
      postfix.append(stack.pop());
      postfix.append(" ");
    }
    
    return postfix.toString();
   }
   
   /////////////////////////////////////////////////////////////
   
   //helper
   public static boolean isOperator(String token){
    if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
      return true;
    }
    
    return false;
  }
  

   //fuction two: evaluatePostFix
   public static double evaluatePostFix(String s){
      
      //variables and datastructures
      StringTokenizer str = new StringTokenizer(s," ");  
      Stack<String> stack = new Stack<String>();
      String token = "";
      double tempOne, tempTwo = 0;
      
      System.out.print(s + "evaluates to: ");
      
      while(str.hasMoreTokens()){
         
         token = str.nextToken();

         //if number is a digit, (or character if u want). push to stack
        if(Character.isDigit(token.charAt(0))){
            stack.push(token);          
        }                 
         //if the character is an operator +, -, *, /, pop the stack and save to variables
         //then apply the operator to them, unless it is a / b when b = 0;
        if(token.equals("+")){
          tempOne = Double.parseDouble(stack.pop());
          tempTwo = Double.parseDouble(stack.pop());
                  
          //add them and put into tempOne so no new variable
          tempOne += tempTwo;
          //push it back to the stack as a string
          stack.push(Double.toString(tempOne));
        }
        if(token.equals("-")){
          tempOne = Double.parseDouble(stack.pop());
          tempTwo = Double.parseDouble(stack.pop());
          
          //add them and put into temptwo because they flip when coming out. 
          tempTwo -= tempOne;
          //push it back to the stack as a string
          stack.push(Double.toString(tempTwo));
        }
        if(token.equals("*")){
          tempOne = Double.parseDouble(stack.pop());
          tempTwo = Double.parseDouble(stack.pop());
          
          tempOne *= tempTwo;
          stack.push(Double.toString(tempOne));
         
        }
        if(token.equals("/")){
           tempOne = Double.parseDouble(stack.pop());
           tempTwo = Double.parseDouble(stack.pop());
               
           //safeguard for dividing by zero.      
           if(tempOne != 0){
             tempTwo /= tempOne;           
             stack.push(Double.toString(tempTwo));
           }
           //if div/0 return -1 and print message
           else{
            System.out.println("Divide by zero error: UNDEFINED");
            return -1; 
           }          
        }  
      }   
      //return the final result
      return Double.parseDouble(stack.pop());
   }
}