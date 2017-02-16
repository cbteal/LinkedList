
/**
 * This queue class will implement a queue data structure using the super 
 * class list and it's methods, which was built earlier in the assignment. 
 * A queue data structure has limited access. The first object in is the 
 * first object out; or FIFO for short.
 * 
 * @author Connor Teal
 * @version February 15, 2017
 */
public class Queue extends List
{
    /**
     * No argument constructor creates a new empty queue.
     */
    public Queue(){
        super();
    }

    /**
     * enqueue will add an object to the head of the queue
     * 
     * @param Object item to be added to queue
     */
    public void enqueue(Object next){
        this.append(next);
    }

    /**
     * dequeue will return the item at the tail of the queue. Also known
     * as the first item enqueued.
     * 
     * @return Object at tail of list
     */
    public Object dequeue(){
        try{
            // if size is less than or equal to zero throw exception
            if(this.size() <= 0){
                throw new LinkedListException("empty queue");
            }

            //return the object at the beginning of the linked list
            return super.remove(0);
        }catch(LinkedListException e){
            System.err.println("Dequeue Failed: " + e.getMessage());
        }
        return null;
    }

    /**
     * Override the insert method from superclass to maintain the list
     * characteristics of a queue FIFO
     * 
     * @param int of index
     */
    @Override
    public void insert(Object a, int n){
        enqueue(a);
    }

    /**
     * Override the remove method from superclass to maintain the list
     * characteristics of a queue FIFO
     * 
     * @param int of index
     * @return Object
     */
    @Override
    public Object remove(int index){
        return dequeue();
    }
    
    /**
     * Override delete method because it would compromise the queue data
     * structure.
     * 
     * @int index
     * @exception when called
     */
    public void delete(int index){
        try{
            throw new LinkedListException("Delete not usable in queue");
        }catch(LinkedListException e){
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        Queue empty = new Queue();
        Queue one = new Queue();
        Queue multiple = new Queue();

        one.enqueue(1);

        multiple.enqueue(10);
        multiple.enqueue(20);
        multiple.enqueue(30);

        System.out.println("Empty after en: " + empty.toString());
        System.out.println("One after en: " + one.toString());
        System.out.println("Multiple after en: " + multiple.toString());

        empty.dequeue();
        one.dequeue();
        multiple.dequeue();

        System.out.println("Empty after dq: " + empty.toString());
        System.out.println("One after dq: " + one.toString());
        System.out.println("Multiple after dq: " + multiple.toString());

        assert(multiple.size() == 2): "multiple size should be 2";

        multiple.dequeue();
        System.out.println("Multiple after dq: " + multiple.toString());

        assert(multiple.size() == 1): "multiple size should be 1";

        multiple.dequeue();
        System.out.println("Multiple after dq: " + multiple.toString());

        assert(multiple.size() == 0): "multiple size should be 1";
        assert(multiple.isEmpty()): "multiple should be empty";
        assert(one.isEmpty()): "one should be empty";
        assert(empty.isEmpty()): "empty should be empty";

        Queue a = new Queue();

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
