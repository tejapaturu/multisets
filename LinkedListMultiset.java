import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{

	/* The linkedlist has a head which refers to the next element, and the next element refers to the
	element after that,the tail is of the linked list refers to null that's how we know the multiset ends.*/
	private Node<T> head;
	private int length;

	/* Constructor which sets the head to null and length 0 */
	public LinkedListMultiset()
	{
		head = null;
		length = 0;
	} // end of LinkedListMultiset()

	/* Adds an item to the linked list */
	public void add(T item) {

		/* If head is null, then sets the item to the first element. */
		if(head == null)
		{
			head = new Node<T>(item);
			length = 1;
		}

		else
		{
			Node<T> currentNode = head;

			//Goes through the entire list to get to the tail.
			while(currentNode.getNextNode() != null)
			{
				currentNode = currentNode.getNextNode();
			}

			//Adds the item after tail.
			currentNode.setNextNode(new Node<T>(item));
		}
		length++;
	} // end of add()

	// Method to search for the given item in the list and return instances.
	public int search(T item)
	{
		Node<T> currentNode = head;
		int instance = 0; //If there is no item in the list return 0.

		//Iteraters until the tail and compares each element to the item and increases instance.
		while(currentNode != null)
		{
			if(currentNode.getElement().equals(item))
			{
				instance++;
			}
			currentNode = currentNode.getNextNode();
		}

		return instance;
	} // end of add()

	//Method to remove one instance of the given item in the linkedlist.
	public void removeOne(T item)
	{
		Node<T> currentNode = head;
		Node<T> previousNode = null;

		//Loops until tail, i.e. the end of the list.
		while(currentNode != null)
		{
			//If the currentnode matches thge given item.
			if(currentNode.getElement().equals(item))
			{
				//If the item given is the head of the list, makes the node next to head the head.
				if(currentNode.getElement().equals(head.getElement()))
				{
					head = head.getNextNode();
				}

				/* If the  currentNode is anything other than head then changes the reference of
				the previous node to the node after the item needed to be removed. */
				else
				{
					previousNode.setNextNode(currentNode.getNextNode());
				}

				length--;
				//breaks the loop so only one instance will be removed
				break;
			}

			/* If the item doesn't match the current node, changes current node to the next node
			and chnages the previous node to the current node. */
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
	} // end of removeOne()

	//Method to remove all instances of the given item in the linkedlist.
	/* Implemented in a similar way to removeOne, here the loop does break after the
	first instance is removed. */
	public void removeAll(T item)
	{
		Node<T> currentNode = head;
		Node<T> previousNode = null;

		while(currentNode != null)
		{
			if(currentNode.getElement().equals(item))
			{
				if(currentNode.getElement().equals(head.getElement()))
				{
					head = head.getNextNode();
				}

				else
				{
					previousNode.setNextNode(currentNode.getNextNode());
				}

				length--;
			}

			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
	} // end of removeAll()


	//Method to print all the items.
	public void print(PrintStream out)
	{

		Node<T> currentNode = head;
		Node<T> previousNode = currentNode;
		int instance;

		//Checks every node in the liost.
		for(int i=0; i<length; i++)
		{
			if(checkForDuplicates(previousNode) != false)
			{
				T temporaryElement = previousNode.getElement();
				currentNode = head;
				instance = 0;

				// While the end of the list isn't reached
				while(currentNode != null)
				{
					//If currentNode's element is equal to temporaryElement then increase instance by 1;
					if(currentNode.getElement().equals(temporaryElement))
					{
						instance++;
					}
					currentNode = currentNode.getNextNode();
				}

				out.println(previousNode.getElement() + printDelim + instance);
				previousNode = previousNode.getNextNode();
			}

			else
			{
				previousNode = previousNode.getNextNode();
			}
		}
	} // end of print()

	//A helper class to print()
	//Checks if there are any duplicates in the linked list of the provided node.
	private boolean checkForDuplicates(Node<T> checkNode)
	{
		Node<T> currentNode = head;

		//returns false if the element in checkNode is the second occurence in the list.
		while(checkNode != currentNode)
		{
			if(checkNode.getElement().equals(currentNode.getElement()))
			{
				return false;
			}

			currentNode = currentNode.getNextNode();
		}
		//returns true if there is no element similar to the one in checknode, from the list
		// before the position of checknode.
		return true;
	}

} // end of class LinkedListMultiset
