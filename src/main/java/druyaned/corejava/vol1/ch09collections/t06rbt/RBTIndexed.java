package druyaned.corejava.vol1.ch09collections.t06rbt;

// find "int value" and replace it by any other type, e.g. "String value"
public class RBTIndexed {
    
    private Node root;
    private int size;
    private final java.util.function.IntBinaryOperator comp;
    
    public RBTIndexed(java.util.function.IntBinaryOperator comp) {
        this.comp = comp;
    }
    
    public RBTIndexed() {
        this.comp = Integer::compare;
    }
    
    public Node root() {
        return root;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public java.util.function.IntBinaryOperator getComparator() {
        return comp;
    }
    
    public Node get(int value) {
        for (Node node = root; node != null; ) {
            int comparison = comp.applyAsInt(node.value, value);
            if (comparison < 0)
                node = node.right;
            else if (comparison > 0)
                node = node.left;
            else
                return node;
        }
        return null;
    }
    
    public Node nodeAt(int index) {
        throwIfBadIndex(index);
        Node node = root;
        int curr = node.leftCount;
        while (index != curr) {
            if (index < curr) {
                node = node.left;
                curr += node.leftCount - node.parent.leftCount;
            } else { // curr < index
                node = node.right;
                curr += 1 + node.leftCount;
            }
        }
        return node;
    }
    
    private void throwIfBadIndex(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException("size=" + size
                    + " index=" + index);
        }
    }
    
    public IndexedNode getIndexedNode(int value) {
        int index = 0;
        for (Node node = root; node != null; ) {
            int comparison = comp.applyAsInt(node.value, value);
            if (comparison < 0) {
                index += 1 + node.leftCount;
                node = node.right;
            } else if (comparison > 0)
                node = node.left;
            else
                return new IndexedNode(node, index + node.leftCount);
        }
        return null;
    }
    
    public void forEach(java.util.function.Consumer<Node> action) {
        Node[] stack = new Node[size];
        int stackSize = 0;
        for (Node node = root; node != null; ) {
            stack[stackSize++] = node;
            node = node.left;
        }
        while (stackSize > 0) {
            Node node = stack[--stackSize];
            action.accept(node);
            node = node.right;
            while (node != null) {
                stack[stackSize++] = node;
                node = node.left;
            }
        }
    }
    
    public void forEachIndexed(java.util.function.Consumer<IndexedNode> action) {
        Node[] stack = new Node[size];
        int stackSize = 0;
        for (Node node = root; node != null; ) {
            stack[stackSize++] = node;
            node = node.left;
        }
        int index = 0;
        while (stackSize > 0) {
            Node node = stack[--stackSize];
            action.accept(new IndexedNode(node, index++));
            node = node.right;
            while (node != null) {
                stack[stackSize++] = node;
                node = node.left;
            }
        }
    }
    
    public boolean add(int value) {
        if (size == 0) {
            root = Node.newRoot(value);
            size++;
            return true;
        }
        Node node = root, parent;
        int comparison;
        do {
            parent = node;
            comparison = comp.applyAsInt(node.value, value);
            if (comparison < 0)
                node = node.right;
            else if (comparison > 0)
                node = node.left;
            else
                return false;
        } while (node != null);
        // update left and right counts
        for (node = root; node != null; ) {
            if (comp.applyAsInt(node.value, value) < 0) {
                node.rightCount++;
                node = node.right;
            } else {
                node.leftCount++;
                node = node.left;
            }
        }
        if (comparison < 0) {
            parent.right = Node.newNode(value, parent);
            insertionBalance(parent.right);
        } else {
            parent.left = Node.newNode(value, parent);
            insertionBalance(parent.left);
        }
        size++;
        return true;
    }
    
    private void insertionBalance(Node node) {
        // cases 1, 2
        while (node.parent != null && node.parent.color == Color.RED) {
            // case 3: recolor G, P and U
            Node u = node.uncle();
            if (u != null && u.color == Color.RED) {
            // && node.parent.color == Color.RED
                node.parent.color = Color.BLACK;
                u.color = Color.BLACK;
                Node g = node.grandpa();
                g.color = Color.RED;
                node = g;
                continue; // go to the case 1 relative to G
            }
            // case 4: rotate P to prepare for the case 5
            Node g = node.grandpa();
            if (node == node.parent.right && g.left == node.parent) {
                node.parent.rotateLeft();
                node = node.left;
            } else if (node == node.parent.left && g.right == node.parent) {
                node.parent.rotateRight();
                node = node.right;
            }
            // case 5: rotate G, recolor G and P
            g = node.grandpa();
            node.parent.color = Color.BLACK;
            g.color = Color.RED;
            if (node == node.parent.left && node.parent == g.left)
                g.rotateRight();
            else
                g.rotateLeft();
            if (node.parent.parent == null)
                root = node.parent;
            return;
        }
        root.color = Color.BLACK;
    }
    
    public boolean remove(int value) {
        Node found = get(value);
        if (found == null)
            return false;
        // update left and right counts
        int comparison;
        found = root;
        do {
            comparison = comp.applyAsInt(found.value, value);
            if (comparison < 0) {
                found.rightCount--;
                found = found.right;
            }
            if (comparison > 0) {
                found.leftCount--;
                found = found.left;
            }
        } while (comparison != 0);
        Node limit = found; // l==null && r==null
        if (found.left != null && found.right == null) {
            found.leftCount--;
            limit = found.left;
            while (limit.right != null) {
                limit.rightCount--;
                limit = limit.right;
            }
        }
        // (l==null && r!=null) || (l!=null && r!=null)
        else if (found.right != null) {
            found.rightCount--;
            limit = found.right;
            while (limit.left != null) {
                limit.leftCount--;
                limit = limit.left;
            }
        }
        found.value = limit.value;
        deleteLimit(limit);
        size--;
        return true;
    }
    
    private void deleteLimit(Node limit) {
        if (limit.color == Color.RED) { // simple case 1
            replaceLimitParent(limit, null);
            return;
        } // limit.color == Color.BLACK
        Node child = limit.left == null ? limit.right : limit.left;
        if (child != null) { // simple case 2
            child.color = Color.BLACK;
            child.parent = limit.parent;
            replaceLimitParent(limit, child);
            return;
        } // child == null; complicated case 3
        removalBalance(limit);
        replaceLimitParent(limit, null);
    }
    
    private void replaceLimitParent(Node limit, Node child) {
        if (limit.parent != null) {
            if (limit.parent.left == limit)
                limit.parent.left = child;
            else
                limit.parent.right = child;
        } else
            root = child;
    }
    
    private void removalBalance(Node node) {
        while (node.parent != null) {
            // case 2: recolor S and go to the case 1 for P
            Node sibling = node.sibling();
            if (
                    node.parent.color == Color.BLACK &&
                    sibling.color == Color.BLACK &&
                    Node.isBlack(sibling.left) &&
                    Node.isBlack(sibling.right)
            ) {
                sibling.color = Color.RED;
                node = node.parent;
                continue; // case1(node.parent)
            }
            // case 3: left-rotate P, recolor P and S
            if (sibling.color == Color.RED) {
                node.parent.color = Color.RED;
                sibling.color = Color.BLACK;
                if (node.parent.left == node)
                    node.parent.rotateLeft();
                else
                    node.parent.rotateRight();
                if (sibling.parent == null)
                    root = sibling;
            }
            // case 4: recolor P and S
            sibling = node.sibling();
            if (
                    node.parent.color == Color.RED &&
                    sibling.color == Color.BLACK &&
                    Node.isBlack(sibling.left) &&
                    Node.isBlack(sibling.right)
            ) {
                node.parent.color = Color.BLACK;
                sibling.color = Color.RED;
                return;
            }
            // case 5: right-rotate S, recolor SL and S
            if (sibling.color == Color.BLACK) {
                if (
                        node.parent.left == node &&
                        Node.isBlack(sibling.right) &&
                        Node.isRed(sibling.left)
                ) {
                    sibling.color = Color.RED;
                    sibling.left.color = Color.BLACK;
                    sibling.rotateRight();
                } else if (
                        node.parent.right == node &&
                        Node.isBlack(sibling.left) &&
                        Node.isRed(sibling.right)
                ) {
                    sibling.color = Color.RED;
                    sibling.right.color = Color.BLACK;
                    sibling.rotateLeft();
                }
            }
            // case 6: left-rotate P, recolor S as P,
            // recolor P and SR to black
            sibling = node.sibling();
            sibling.color = node.parent.color;
            node.parent.color = Color.BLACK;
            if (node.parent.left == node) {
                sibling.right.color = Color.BLACK;
                node.parent.rotateLeft();
            } else {
                sibling.left.color = Color.BLACK;
                node.parent.rotateRight();
            }
            if (sibling.parent == null)
                root = sibling;
            return;
        }
    }
    
    public static enum Color { RED, BLACK }
    
    public static class Node {
        private int value;
        private Color color;
        private Node parent, left, right;
        private int leftCount, rightCount;
        private Node(int value, Color color, Node parent) {
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.leftCount = 0;
            this.rightCount = 0;
        }
        private static Node newRoot(int value) {
            return new Node(value, Color.BLACK, null);
        }
        private static Node newNode(int value, Node parent) {
            return new Node(value, Color.RED, parent);
        }
        private static boolean isBlack(Node node) {
            return node == null || node.color == Color.BLACK;
        }
        private static boolean isRed(Node node) {
            return node != null && node.color == Color.RED;
        }
        private Node sibling() {
            return parent.left == this ? parent.right : parent.left;
        }
        private Node grandpa() {
            return parent.parent;
        }
        private Node uncle() {
            return parent.parent.left == parent
                    ? parent.parent.right
                    : parent.parent.left;
        }
        private void rotateLeft() {
            Node r = right;
            r.parent = parent;
            if (parent != null) {
                if (parent.left == this)
                    parent.left = r;
                else
                    parent.right = r;
            }
            Node rl = r.left;
            right = rl;
            if (rl != null) {
                rl.parent = this;
                rightCount = 1 + rl.leftCount + rl.rightCount;
            } else
                rightCount = 0;
            parent = r;
            r.left = this;
            r.leftCount = 1 + leftCount + rightCount;
        }
        private void rotateRight() {
            Node l = left;
            l.parent = parent;
            if (parent != null) {
                if (parent.left == this)
                    parent.left = l;
                else
                    parent.right = l;
            }
            Node lr = l.right;
            left = lr;
            if (lr != null) {
                lr.parent = this;
                leftCount = 1 + lr.leftCount + lr.rightCount;
            } else
                leftCount = 0;
            parent = l;
            l.right = this;
            l.rightCount = 1 + leftCount + rightCount;
        }
        public int value() {
            return value;
        }
        public Color color() {
            return color;
        }
        public Node parent() {
            return parent;
        }
        public Node left() {
            return left;
        }
        public Node right() {
            return right;
        }
        public int leftCount() {
            return leftCount;
        }
        public int rightCount() {
            return rightCount;
        }
    }
    
    public static class IndexedNode {
        private final Node node;
        private final int index;
        public IndexedNode(Node node, int index) {
            this.node = node;
            this.index = index;
        }
        public Node node() {
            return node;
        }
        public int index() {
            return index;
        }
        @Override public String toString() {
            return "IndexedNode{node.value=" + node.value
                    + ", index=" + index
                    + '}';
        }
    }
    
}
