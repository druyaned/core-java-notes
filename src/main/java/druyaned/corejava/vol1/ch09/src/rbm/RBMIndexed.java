package druyaned.corejava.vol1.ch09.src.rbm;

// find "int key" and replace it by any other type, e.g. "String key"
public class RBMIndexed {
    
    private Node root;
    private int size;
    private final java.util.function.IntBinaryOperator comp;
    
    public RBMIndexed(java.util.function.IntBinaryOperator comp) {
        this.comp = comp;
    }
    
    public RBMIndexed() {
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
    
    public java.util.function.IntBinaryOperator comparator() {
        return comp;
    }
    
    public Node getNode(int key) {
        for (Node node = root; node != null; ) {
            int comparison = comp.applyAsInt(node.key, key);
            if (comparison < 0)
                node = node.right;
            else if (comparison > 0)
                node = node.left;
            else
                return node;
        }
        return null;
    }
    
    public IndexedNode getIndexedNode(int key) {
        int index = 0;
        for (Node node = root; node != null; ) {
            int comparison = comp.applyAsInt(node.key, key);
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
    
    public Node lastNode() {
        if (size == 0)
            throw new java.util.NoSuchElementException("map is empty");
        Node node = root;
        while (node.right != null)
            node = node.right;
        return node;
    }
    
    public Node higherNode(int key) {
        Node prev = null;
        for (Node node = root; node != null; ) {
            if (comp.applyAsInt(node.key, key) <= 0)
                node = node.right;
            else {
                prev = node;
                node = node.left;
            }
        }
        return prev;
    }
    
    public void forEach(java.util.function.Consumer<Node> action) {
        Object[] stack = new Object[size];
        int stackSize = 0;
        for (Node node = root; node != null; ) {
            stack[stackSize++] = node;
            node = node.left;
        }
        while (stackSize > 0) {
            Node node = (Node)stack[--stackSize];
            action.accept(node);
            node = node.right;
            while (node != null) {
                stack[stackSize++] = node;
                node = node.left;
            }
        }
    }
    
    public void forEachIndexed(java.util.function.Consumer<IndexedNode> action) {
        Object[] stack = new Object[size];
        int stackSize = 0;
        for (Node node = root; node != null; ) {
            stack[stackSize++] = node;
            node = node.left;
        }
        int index = 0;
        while (stackSize > 0) {
            Node node = (Node)stack[--stackSize];
            action.accept(new IndexedNode(node, index++));
            node = node.right;
            while (node != null) {
                stack[stackSize++] = node;
                node = node.left;
            }
        }
    }
    
    public String put(int key, String value) {
        if (size == 0) {
            root = Node.newRoot(key, value);
            size++;
            return null;
        }
        Node node = root, parent;
        int comparison;
        do {
            parent = node;
            comparison = comp.applyAsInt(node.key, key);
            if (comparison < 0)
                node = node.right;
            else if (comparison > 0)
                node = node.left;
            else {
                String prevVal = node.value;
                node.value = value;
                return prevVal;
            }
        } while (node != null);
        // update left and right counts
        for (node = root; node != null; ) {
            if (comp.applyAsInt(node.key, key) < 0) {
                node.rightCount++;
                node = node.right;
            } else {
                node.leftCount++;
                node = node.left;
            }
        }
        if (comparison < 0) {
            parent.right = Node.newNode(key, value, parent);
            insertionBalance(parent.right);
        } else {
            parent.left = Node.newNode(key, value, parent);
            insertionBalance(parent.left);
        }
        size++;
        return null;
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
    
    public String remove(int key) {
        Node found = getNode(key);
        if (found == null)
            return null;
        // update left and right counts
        int comparison;
        found = root;
        do {
            comparison = comp.applyAsInt(found.key, key);
            if (comparison < 0) {
                found.rightCount--;
                found = found.right;
            }
            if (comparison > 0) {
                found.leftCount--;
                found = found.left;
            }
        } while (comparison != 0);
        String removedValue = found.value;
        Node limit = found; // l==null && r==null
        if (found.left != null && found.right == null) {
            found.leftCount--;
            limit = found.left;
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
        found.key = limit.key;
        found.value = limit.value;
        deleteLimit(limit);
        size--;
        return removedValue;
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
        private int key;
        private String value;
        private Color color;
        private Node parent, left, right;
        private int leftCount, rightCount;
        private Node(int key, String value, Color color, Node parent) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.leftCount = 0;
            this.rightCount = 0;
        }
        private static Node newRoot(int key, String value) {
            return new Node(key, value, Color.BLACK, null);
        }
        private static Node newNode(int key, String value, Node parent) {
            return new Node(key, value, Color.RED, parent);
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
            return parent.parent.left == parent ? parent.parent.right : parent.parent.left;
        }
        /**
         * Left rotation of the node.
         * <u>Important Note</u>: {@code N != null} and {@code R != null}.
         * 
         * <P><i>Schema</i>
         * <pre>
         *    N              R   
         *   / \            / \  
         *  L   R    -->   N   RR
         *     / \        / \    
         *    RL  RR     L   RL  
         * </pre>
         */
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
        /**
         * Right rotation of the node.
         * <u>Important Note</u>: {@code N != null} and {@code L != null}.
         * 
         * <P><i>Schema</i>
         * <pre>
         *       N              L    
         *      / \            / \   
         *     L   R   -->   LL   N  
         *    / \                / \ 
         *  LL   LR            LR   R
         * </pre>
         * @param n the node relative to which the rotation is performed
         */
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
        public int key() {
            return key;
        }
        public String value() {
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
    }
    
}
