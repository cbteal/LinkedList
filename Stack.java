/**
 * Stack will be an implementation of the stack ADT using the linked list
 * we built in the first portion of the assignment as a super class. A stack 
 * is a data structure that acts like a stack of paper. Data is added to the 
 * top and pulled from the the top. In other words it's last in first out, or 
 * LIFO for short.
 * 
 * @author Connor Teal
 * @version February 15, 2017
 */
public class Stack extends List
{
    /**
     * Default constructor will create a new empty stack. It does so by
     * calling the super constructor which creates an empty list.
     */
    public Stack(){
        super();
    }
    
    /**
     * push adds a new data item to the stack. It does so using the list
     * method append.
     * 
     * @param Object to be added
     * @return Nothing
     */
    public void push(Object next){
        append(next);
    }
    
    /**
     * Pop will return the item at the top of the stack.
     * 
     * @return Object from the top of the stack
     * @exception when trying to pop from empty stack
     */
    public Object pop(){
        try{
            //If empty throw an exception
            if(this.isEmpty()){
                throw new LinkedListException("Empty stack");
            }
            
            //Return the head of the list
            return super.remove(this.size() - 1);
        }catch(LinkedListException e){
            System.err.println("Failed to pop: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Override the remove method from superclass to maintain the list
     * characteristics of a Stack LIFO
     * 
     * @param int of index
     * @return Object
     */
    @Override
    public Object remove(int index){
        return pop();
    }
    
    /**
     * Override the insert method from superclass to maintain the list
     * characteristics of a Stack LIFO
     * 
     * @param Object to be added
     * @param int of index
     */
    @Override
    public void insert(Object a, int n){
        push(a);
    }
    
    /**
     * Override delete method because it would compromise the stack data
     * structure.
     * 
     * @int index
     * @exception when called
     */
    public void delete(int index){
        try{
            throw new LinkedListException("Delete not usable in stack");
        }catch(LinkedListException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        Stack empty = new Stack();
        Stack one = new Stack();
        Stack multiple = new Stack();
        
        one.push(1);
        
        multiple.push(10);
        multiple.push(20);
        multiple.push(30);
        
        System.out.println("Empty after push: " + empty.toString());
        System.out.println("One after push: " + one.toString());
        System.out.println("Multiple after push: " + multiple.toString());
        
        empty.pop();
        one.pop();
        multiple.pop();
        
        System.out.println("Empty after pop: " + empty.toString());
        System.out.println("One after pop: " + one.toString());
        System.out.println("Multiple after pop: " + multiple.toString());
        
        Stack a = new Stack();
        Stack b = new Stack();
        
        a.push(1);
        a.push(2);
        a.push(3);
        
        assert(a.size() == 3): "size should be 3";
        assert((int) a.pop() == 3): "pop should return 3";
        assert(a.size() == 2): "size should be 2";
        assert(a.indexOf(2) == 1): "index of 2 should be 1";
        assert((int) a.pop() == 2): "pop should return 2";
        assert((int) a.pop() == 1): "pop should return 1";
        
        //Testing exception
        assert(a.pop() == null);
        System.out.println(a.pop());
        a.pop();
        
        a.insert(1,0);
        a.insert(2,1);
        a.insert(3,5);
        System.out.println("a after inserts: " + a.toString());
        a.remove(14);
        System.out.println("a after remove: " + a.toString());
        a.remove(14);
        a.remove(14);
        a.remove(14);
        System.out.println("a after remove all: " + a.toString());
        
        a.delete(1);
    }
}
