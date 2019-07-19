public class BSTNode<T>
	{
		private BSTNode<T> leftChild;
        private BSTNode<T> rightChild;

        private T element;
		private int instances;

		BSTNode(T element)
		{
			this.element = element;
			instances = 0;
			leftChild = null;
			rightChild = null;
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}

		public BSTNode<T> getLeftChild() {
			return leftChild;
		}

		public BSTNode<T> getRightChild() {
			return rightChild;
		}

		public void setLeftChild(BSTNode<T> leftChild) {
			this.leftChild = leftChild;
		}

		public void setRightChild(BSTNode<T> rightChild) {
			this.rightChild = rightChild;
		}

		public int getInstances() {
			return instances;
		}

		public void setInstances(int instances) {
			this.instances = instances;
		}
	}
