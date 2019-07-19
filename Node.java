public class Node<T>
{
        private T element;
        private Node<T> nextNode;

		public Node(T element)
		{
			this.element = element;
			nextNode = null;
		}

		public void setElement(T element)
		{
			this.element = element;
		}

		public T getElement()
		{
			return element;
        }

		public void setNextNode(Node<T> nextNode)
		{
			this.nextNode = nextNode;
		}


		public Node<T> getNextNode()
		{
			return nextNode;
		}
	}
