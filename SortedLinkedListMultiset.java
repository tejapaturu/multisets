import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	//Created in a similar way to linked list, only this list is sorted when we add items to it.
	private Node<T> head;
	private int length;

	public SortedLinkedListMultiset()
	{
		head = null;
		length = 0;
	} // end of SortedLinkedListMultiset()

	/* Adds an item to the sortedLinkedList in a sorted order */
	public void add(T item)
	{
		Node<T> currentNode = head;
		Node<T> previousNode = null;
		Node<T> newNode = new Node<T>(item); // Created from the item provided, i.e. the node to be added.

		//loop to decide where to add the item.
		while(currentNode != null)
		{
			String addElement = item.toString();
			String currentElement = currentNode.getElement().toString();

			//Decided where to add the element, in sorted order.
			if(currentElement.compareTo(addElement)>0)
			{
				break;
			}

			/* Depending upon when the loop ends, assigns  previousNode to
			currentNode i.e. the node before the given item should be placed after.
			And sets currentNode to the next node i.e. the node which comes after the
			given item in sorted order.*/
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}

		//If head is null, i.e new sortedlinkedlist head becomes the new node.
		if(head == null)
		{
			head = newNode;
		}

		//If the given item is to be added as head, replaces head.
		else if(currentNode == head)
		{
			newNode.setNextNode(head);
			head = newNode;
		}

		//If any other position than the head.
		else
		{
			previousNode.setNextNode(newNode);
			newNode.setNextNode(currentNode);
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
			//If value is matched the incstance increments
			if(currentNode.getElement().equals(item))
			{
				instance++;
			}
			else if(instance != 0)
			{
				return instance;
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

	//Method to remove all instances of the given item in the sortedlinkedlist.
	/* Implemented in a similar way to removeOne, here the loop does break after the
	first instance is removed. */
	public void removeAll(T item)
	{
		Node<T> currentNode = head;
		Node<T> previousNode = null;
		int instance = 0;

		while(currentNode != null)
		{
			if(currentNode.getElement().equals(item))
			{
				instance++;

				if(currentNode.getElement().equals(head.getElement()))
				{
					head = head.getNextNode();
				}

				else
				{
					previousNode.setNextNode(currentNode.getNextNode());
				}
				currentNode = currentNode.getNextNode();
				length--;
			}

			else if(instance != 0)
			{
				break;
			}

			else
			{
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
			}
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

					else if(instance != 0)
					{
						break;
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
} // end of class SortedLinkedListMultiset
