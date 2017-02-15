
/**
 * The list class is our own implementation of a linked list. A link list 
 * is an ADT with unique properties. Unlike the array based implementations
 * from previous assignments link list uses a reference to 'link' elements. 
 * 
 * This means that they're no longer in a fixed area of memory. They can be
 * scattered without and they use pointers to link to the previous item in
 * the list. The last item will point to null.
 * 
 * 
 * @author Connor Teal
 * @version February 14, 2017
 */
public class List
{
    /**
     * This inner class node will serve to act as a node in our linked list.
     * The reason we use inner class inside the list because it's not used
     * elsewhere and we can avoid privacy leaks by making it a private inner
     * class.
     * 
     * This also allows us to use the instance variables without needing
     * getters and mutators.
     */
    private class Node
    {
        private Object data;
        private Node link;

        /**
         * No argument constructor sets instance variables to default of 
         * data and link null.
         */
        public Node(){
            link = null;
            data = null;
        }

        /**
         * Full argument constructor sets Node instance variables to given 
         * values
         * 
         * @param Object data object to set
         * @param Node link to set reference
         */
        public Node(Object data, Node link){
            this.data = data;
            this.link = link;
        }
    }

    //Head node that indicates start of list
    private Node head = null;

    /**
     * No argument constructor sets head to null to indicate start of list.
     */
    public List(){
        head = null;
    }

    /**
     * Append will add a node to the end of the list by creating a new 
     * node with the new object as data. The new node points to the previous
     * head.
     * 
     * @param Object next object to be added
     * @return Nothing
     */
    public void append(Object next){
        //Sets head node to a new node with passed object that points to
        //previous head
        this.head = new Node(next,head);
    }

    /**
     * Size will traverse through a list and return the number of items in
     * the list. It runs until the Node is equal to null, which indicates 
     * the start of the list (no more items to count).
     * 
     * @return int size is the number of objects in the list
     */
    public int size(){
        int count = 0;
        Node position = head;

        //While we're not at the starting node continue to count
        while(!(position == null)){
            count++;
            position = position.link; //Moves to preceding node
        }

        return count;
    }

    /**
     * isEmpty checks to see if the list is empty by looking at the current
     * head. If it's equal to null then we no that the list is empty.
     * 
     * @return boolean true if list is empty
     */
    public boolean isEmpty(){
        if(head == null){
            return true;
        }

        return false;
    }

    /**
     * insert will insert an object into a specified index position
     * as long as it's valid. There are three unique cases to look for:
     * one when size is zero, one when size is 1, and the other when
     * size is greater than one.
     * 
     * @param object next object to be added at index
     * @param int index where the node should be changed
     * @return Nothing
     * @exception caught when index outside of range
     */
    public void insert(Object next, int index){
        try{
            //check index
            this.insideRange(index);

            //if index check for conditions
            if(this.size() == 0 || index == this.size()){
                //empty list or index chosen to be new head so append
                append(next);
            }
            else if(this.size() == 1){
                //size is one and didn't append so change index 0
                head = new Node(next, head.link);
            }
            else{
                //didn't append and size is greater than 1 so find node
                //and change it
                Node position = head;

                //traverse list until index node is reached
                for(int i = this.size() - 1; i > index; i--){
                    //cycles through nodes by grabbing reference
                    position = position.link;
                }

                position.data = next;
            }
        }catch(LinkedListException e){
            //Index was outside of range so throw error message
            System.err.print("Failed to insert object: ");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Delete will delete the node at a given index. It operates the same
     * way that remove does, however it doesn't return the value at the
     * specified index: it deletes it.
     * 
     * @param int index of node to be deleted
     * @return Nothing
     */
    public void delete(int index){
        try{
            //check index range
            insideRange(index);

            if(this.size() == 0){
                //if empty list throw exception
                throw new LinkedListException("Can't delete from empty " +
                    " list");
            }
            else if(index == this.size()){
                //if index = size then it's an invalid index
                throw new LinkedListException("Outside of list range");
            }
            else if(this.size() == 1){
                //if size = 1 then we're creating empty list
                head = null;
            }
            else{
                //shift list to adjust for removed node
                shiftList(index);
            }
        }catch(LinkedListException e){
            System.err.println("Failed to delete node: " + e.getMessage());
        }
    }

    /**
     * insideRange will check to make sure a given index is within the
     * valid range. The valid range is greater than zero and less than
     * or equal to the size of the list.
     * 
     * @param int index to check
     * @return boolean true if inside valid range
     * @throws LinkedListException when outside of valid range
     */
    private boolean insideRange(int index) throws LinkedListException{
        //Can't be a negative index position
        if(index < 0){
            throw new LinkedListException("Index outside of list range -"
                + " must be greater than zero");
        }

        //Less than size because index = size would append to end of list
        if(index > this.size()){
            throw new LinkedListException("Index outside of list range -"
                + " must be less than size");
        }

        return true;
    }

    /**
     * Remove will extract the data from the node at a specified index
     * and return it. It will then shift manipulate thelist to account for 
     * the removed node.
     * 
     * @param int index of node to remove
     * @return Object data at specified node
     * @exception when given invalid index
     */
    public Object remove(int index){
        Object retVal = null;
        try{
            //check index range
            insideRange(index);

            if(this.size() == 0){
                //if empty list throw exception
                throw new LinkedListException("Can't remove from empty " +
                    " list");
            }
            else if(index == this.size()){
                //if index = size then it's an invalid index
                throw new LinkedListException("Outside of list range");
            }
            else if(this.size() == 1){
                retVal = head.data;
                head = null;
            }
            else{
                //Set position to head
                Node position = head;

                //Traverse list until index position is reached
                for(int i = this.size() - 1; i > index; i--){
                    position = position.link;
                }
                retVal = position.data;

                //shift list to adjust for removed node
                shiftList(index);
            }
        }catch(LinkedListException e){
            System.err.println("Failed to remove node: " + e.getMessage());
            retVal = null;
        }finally{
            return retVal;
        }

    }

    /**
     * shiftList will shift a list from a specified index by traversing
     * the list until it reaches the node that links to the index node.
     * It then sets it's link to index's node which removes the index 
     * node from the linked list.
     * 
     * @param int index of node to shift from
     * @return none
     */
    private void shiftList(int index){
        if(index == this.size() - 1){
            head = head.link;
        }else{
            //Set position to head
            Node position = head;

            //Traverse through until reach node linking to index
            for(int i = this.size() - 1; i > index + 1; i--){
                position = position.link;
            }

            position.link = position.link.link;
        }
    }

    /**
     * toString will enumerate the list and return it as a string with
     * values separated by white space.
     * 
     * @return String enumeration of list
     */
    @Override
    public String toString(){
        String retVal = "";
        Node position = head;

        //Traverses list while not at tail
        while(position != null){
            retVal += position.data + " ";
            position = position.link;
        }

        return retVal;
    }

    public int indexOf(Object target){
        int retVal = -1;
        if(this.size() == 0){
            return retVal;
        }else{
            Node position = head;
            for(int i = this.size(); i > 0; i--){
                if(position.data == target){
                    retVal = i - 1;
                    return retVal;
                }
                position = position.link;
            }
        }
        return retVal;
    }

    public static void main(String[] args){
        List empty = new List();
        List one = new List();
        List multiple = new List();

        one.append(5);
        multiple.append(10);
        multiple.append(20);
        multiple.append(30);

        System.out.println("Empty:"+empty);
        System.out.println("One:"+one);
        System.out.println("Multiple:"+ multiple);	

        one.delete(0);
        multiple.delete(1);
        System.out.println("One (upon delete):"+one);
        System.out.println("Multiple (upon delete):"+ multiple);

        one.insert(600, 0);
        multiple.insert(400, 2);
        System.out.println("One (on insert):"+one);
        System.out.println("Multiple(on insert):"+ multiple);
        
        List a = new List();
        List b = new List();
        
        //Size, Append, Insert Tests
        System.out.println("\n Size/Removal/Insert Tests \n");
        assert(a.size() == 0): "Size should be zero";
        assert(!(a.size() != 0)): "Size should be zero";
        assert(a.isEmpty()): "List should be empty";

        a.append(1);
        assert(a.size() == 1): "Size should be one";
        assert(a.size() >= 0): "Size should be greater than zero";
        assert(!a.isEmpty()): "List shouldn't be empty";

        a.insert(2,1);
        assert(a.size() == 2): "Size should be after insert";
        a.insert(1,-1);
        assert(a.size() == 2): "Insert OOB: Size shouldn't change.";
        a.insert(1,3);
        assert(a.size() == 2): "Insert OOB: Size shouldn't change.";

        a.append(3);
        a.append(4);

        a.insert(4,2);
        assert(a.size() == 4): "Size should be 4";

        //Removal Tests
        System.out.println("\n Removal Tests \n");
        assert((int) a.remove(2) == 4): "Index 2 should equal 4";
        assert(a.size() == 3);
        assert(a.remove(-1) == null): "Removal should return null";
        a.remove(2);

        //Checking empty list removal
        b.remove(0);

        int i = a.size();

        while(!a.isEmpty()){
            a.remove(i--);
            System.out.println(a.toString());
        }

        //Delete Tests
        System.out.println("\n Delete Tests \n");
        for(int j = 0; j < 10; j++){
            a.append(j);
        }
        System.out.println(a.toString());
        a.delete(3);
        a.delete(0);
        a.delete(-1);
        System.out.println(a.toString());
        assert(a.size() == 8): "Size should be 8";
        
        //indexOfTests
        System.out.println("\n indexOf Tests \n");
        assert(a.indexOf(-4) == -1): "index of not found should be -1";
        assert(a.indexOf(5) == 3): "index of 5 should be 3";
        assert(a.indexOf(9) == 7): "index of 9 should be 7";
        assert(a.indexOf("") == -1): "index of not found should be -1";
        assert(a.indexOf(1) == 0): "index of 1 should be 0";
    }
}
