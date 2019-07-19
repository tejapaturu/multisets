import java.io.PrintStream;

public class BstMultiset<T> extends Multiset<T>
{
	BSTNode<T> root; //The binary search tree has a root that refers to the child nodes.

	/* Constructor which sets the root to null*/
	public BstMultiset()
	{
		root = null;
	} // end of BstMultiset()

	/* Adds an item to the binary search tree */
	public void add(T item)
	{
		//Creates a node from the item provided
		BSTNode<T> newNode = new BSTNode<T>(item);
		boolean test = false;

		//If root is null, i.e. an empty bst then makes the item the root
		if(root == null)
		{
			root = newNode;
			root.setInstances(root.getInstances() + 1);
		}
		//if tree is not null
		else
		{
			test = true;
		}

		BSTNode<T> currentNode = root;
		BSTNode<T> parentNode = null;

		// If the bst is not null
		while(test == true)
		{
			parentNode = currentNode;

			// the value of the item is compared with the current node to figure out where to add
			if(item.toString().compareTo(currentNode.getElement().toString()) < 0)
			{
				currentNode = currentNode.getLeftChild();

				//If left node is null, add the item to the left node.
				if(currentNode == null)
				{
					parentNode.setLeftChild(newNode);
					parentNode.getLeftChild().setInstances(parentNode.getLeftChild().getInstances() + 1);
					test = false;
				}
			}

			//If item already exits, it's instance is increased
			else if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
			{
				currentNode.setInstances(currentNode.getInstances() + 1);
				test = false;
			}

			else
			{
				currentNode = currentNode.getRightChild();

				//If right child is empty, make the item the right child.
				if(currentNode == null)
				{
					parentNode.setRightChild(newNode);
					parentNode.getRightChild().setInstances(parentNode.getRightChild().getInstances() + 1);
					test = false;
				}
			}
		}
	} // end of add()

	// Method to search for the given item in the list and return instances.
	public int search(T item)
	{
		BSTNode<T> currentNode = root;
		int instance = 0; //If there is no item in the list return 0.

		//Iteraters until the end and compares each element to the item and gets instance.
		while(currentNode != null)
		{

			//If item is found, gets instance and breaks the loop
			if(currentNode.getElement().equals(item))
			{
				instance+= currentNode.getInstances();
				break;
			}

			if(item.toString().compareTo(currentNode.getElement().toString()) <0)
			{
				currentNode = currentNode.getLeftChild();
			}

			else
			{
				currentNode = currentNode.getRightChild();
			}
		}

		return instance;
	} // end of search()

	//Method to remove one instance of the given item in the binary search tree.
	public void removeOne(T item)
	{
		BSTNode<T> currentNode = root;
		BSTNode<T> parentNode = null;

		boolean test = true;

		if(currentNode == null)
		{
			test = false;
		}

		//Loop through the tree until the item is found from root to bottom in order.
		while(test == true)
		{
			parentNode = currentNode;

			//When item has less value than the parent
			if(item.toString().compareTo(currentNode.getElement().toString()) < 0)
			{
				currentNode = currentNode.getLeftChild();

				if(currentNode == null)
				{
					test = false;
					break;
				}

				//When the node is  founf break from the loop
				if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
				{
					break;
				}
			}

			//When item has the same value as the parent, break the loop
			else if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
			{
				break;
			}

			else
			{
				currentNode = currentNode.getRightChild();

				if(currentNode == null)
				{
					test = false;
					break;
				}

				if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
				{
					break;
				}
			}
		}

		//If node is found
		if(test == true)
		{
			if(currentNode.getInstances() > 1)
			{
				test = false;
				currentNode.setInstances(currentNode.getInstances() - 1);
			}
			else
			{
				//If there are no children, delete the node.
				if(currentNode.getLeftChild() == null && currentNode.getRightChild() == null)
				{

					if(parentNode == currentNode)
					{
						root = null;
					}
					else if(parentNode.getRightChild() == currentNode)
					{
						parentNode.setRightChild(null);
					}
					else if(parentNode.getLeftChild() == currentNode)
					{
						parentNode.setLeftChild(null);
					}
				}

				//If it only has right child, set the right child as parent and delete the node.
				else if(currentNode.getLeftChild() == null)
				{
					if(parentNode == currentNode)
					{
						root = parentNode.getRightChild();
					}
					else if(parentNode.getRightChild() == currentNode)
					{
						parentNode.setRightChild(currentNode.getRightChild());
					}
					else
					{
						parentNode.setLeftChild(currentNode.getRightChild());
					}
				}

				//If it only has left child, set the left child as parent and delete the node.
				else if(currentNode.getRightChild() == null)
				{
					if(parentNode == currentNode)
					{
						root = parentNode.getLeftChild();
					}
					else if(parentNode.getLeftChild() == currentNode)
					{
						parentNode.setLeftChild(currentNode.getLeftChild());
					}
					else
					{
						parentNode.setRightChild(currentNode.getLeftChild());
					}
				}

				//If it has both children, use helper class to get the appropriate child node to make it as parent cell.
				else
				{
					BSTNode<T> minimumRightNode = getLeastRightChildNode(currentNode);

					if(parentNode == currentNode)
					{
						minimumRightNode.setLeftChild(root.getLeftChild());
						minimumRightNode.setRightChild(root.getRightChild());
						root = minimumRightNode;
					}
					else if(parentNode.getLeftChild() == currentNode)
					{
						minimumRightNode.setLeftChild(currentNode.getLeftChild());
						minimumRightNode.setRightChild(currentNode.getRightChild());
						parentNode.setLeftChild(minimumRightNode);
					}
					else
					{
						minimumRightNode.setLeftChild(currentNode.getLeftChild());
						minimumRightNode.setRightChild(currentNode.getRightChild());
						parentNode.setRightChild(minimumRightNode);
					}
				}
			}
		}
	} // end of removeOne()

	//Method to remove all instances of the given item in the binary search tree.
	/* Implemented in a similar way to removeOne, here the loop does break after the
	first instance is removed. */
	public void removeAll(T item)
	{

		BSTNode<T> currentNode = root;
		BSTNode<T> parentNode = null;

		boolean test = true;

		// if root isn't null, i.e. not a empty tree
		if(currentNode != null)
		{
			while(test == true)
			{
				parentNode = currentNode;

				if(item.toString().compareTo(currentNode.getElement().toString()) < 0)
				{
					currentNode = currentNode.getLeftChild();

					if(currentNode == null)
					{
						test = false;
						break;
					}

					if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
					{
						break;
					}
				}

				else if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
				{
					break;
				}

				else
				{
					currentNode = currentNode.getRightChild();

					if(currentNode == null)
					{
						test = false;
						break;
					}

					if(item.toString().compareTo(currentNode.getElement().toString()) == 0)
					{
						break;
					}
				}
			}
			

			if(test == true)
			{

				if(currentNode.getLeftChild() == null && currentNode.getRightChild() == null)
				{

					if(parentNode == currentNode)
					{
						root = null;
					}
					else if(parentNode.getRightChild() == currentNode)
					{
						parentNode.setRightChild(null);
					}
					else if(parentNode.getLeftChild() == currentNode)
					{
						parentNode.setLeftChild(null);
					}
				}

				else if(currentNode.getLeftChild() == null)
				{
					if(parentNode == currentNode)
					{
						root = parentNode.getRightChild();
					}
					else if(parentNode.getRightChild() == currentNode)
					{
						parentNode.setRightChild(currentNode.getRightChild());
					}
					else
					{
						parentNode.setLeftChild(currentNode.getRightChild());
					}
				}

				else if(currentNode.getRightChild() == null)
				{
					if(parentNode == currentNode)
					{
						root = parentNode.getLeftChild();
					}
					else if(parentNode.getLeftChild() == currentNode)
					{
						parentNode.setLeftChild(currentNode.getLeftChild());
					}
					else
					{
						parentNode.setRightChild(currentNode.getLeftChild());
					}
				}

				else
				{
					BSTNode<T> minimumRightNode = getLeastRightChildNode(currentNode);

					if(parentNode == currentNode)
					{
						minimumRightNode.setLeftChild(root.getLeftChild());
						minimumRightNode.setRightChild(root.getRightChild());
						root = minimumRightNode;
					}
					else if(parentNode.getLeftChild() == currentNode)
					{
						minimumRightNode.setLeftChild(currentNode.getLeftChild());
						minimumRightNode.setRightChild(currentNode.getRightChild());
						parentNode.setLeftChild(minimumRightNode);
					}
					else
					{
						minimumRightNode.setLeftChild(currentNode.getLeftChild());
						minimumRightNode.setRightChild(currentNode.getRightChild());
						parentNode.setRightChild(minimumRightNode);
					}
				}
			}
		}
	} // end of removeAll()

	//Method to print all the items.
	public void print(PrintStream out)
	{
		BSTNode<T> currentNode = root;

		printInOrder(currentNode, out);
	} // end of print()

	// Helper method which calls itself to print tree in order
	private void printInOrder(BSTNode<T> currentNode, PrintStream out)
	{
		int instance = 0;

		while(currentNode != null)
		{
			if(instance != 0)
			{
				break;
			}

			// prints left child first
			printInOrder(currentNode.getLeftChild(), out);

			// Occurrences of current node
			instance += currentNode.getInstances();

			out.println(currentNode.getElement() + printDelim + instance);

			// prints the right child
			printInOrder(currentNode.getRightChild(), out);
		}
	}

	//gets the least child node of the node that is to be deleted.
	private BSTNode<T> getLeastRightChildNode(BSTNode<T> currentNode)
	{
		BSTNode<T> parentNode = currentNode;
		currentNode = currentNode.getRightChild();

		// This loop keeps on getting the left child until it reaches the least left child
		while(currentNode != null)
		{
			if(currentNode.getLeftChild() != null)
			{
				parentNode = currentNode;
				currentNode = currentNode.getLeftChild();
			}
			else
			{
				if(parentNode == root)
				{
					parentNode.setRightChild(null);
					break;
				}
				parentNode.setLeftChild(currentNode.getLeftChild());
				break;
			}
		}

		return currentNode;
	}

} // end of class BstMultiset
