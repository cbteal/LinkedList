/**
 * This is a custom exception class that will be used to handle exceptions
 * in my implementation of linked list.
 * 
 * @author Connor Teal
 * @version February 15, 2017
 */
public class LinkedListException extends Exception
{
    /**
     * Empty constructor will throw default message
     */
    public LinkedListException(){
        super();
    }
    
    /**
     * String argument constructor creates a LLE with a custom message
     */
    public LinkedListException(String message){
        super(message);
    }
}
