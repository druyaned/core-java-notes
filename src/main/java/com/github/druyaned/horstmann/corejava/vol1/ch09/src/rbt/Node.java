package com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbt;

/**
 * The node is an actual component of the {@link RedBlackTree Red-Black Tree}.
 * Also it is used to get the {@link Color color} and {@code value} in a public visibility.
 * 
 * <P><i>Structure of the Node class</i><pre>
 *  public class Node&lt;T&gt; {
 *  default:
 *      T value;
 *      Color color;
 *      Node&lt;T&gt; parent, left, right;
 *  private:
 *      Constructor(...);
 *  default:
 *      static&lt;T&gt; Node&lt;T&gt; newRoot(T value);
 *      static&lt;T&gt; Node&lt;T&gt; newNode(T value, Node&lt;T&gt; parent);
 *      static&lt;T&gt; boolean isBlack(Node&lt;T&gt; node)
 *      static&lt;T&gt; boolean isRed(Node&lt;T&gt; node)
 *      Node&lt;T&gt; sibling();
 *      Node&lt;T&gt; grandpa();
 *      Node&lt;T&gt; uncle();
 *      void rotateLeft();
 *      void rotateRight();
 *  public:
 *      T getValue();
 *      Color getColor();
 *  }
 * </pre>
 * 
 * <P><i>Types of Node</i><ul>
 *  <li>root</li>
 *  <li>leaf</li>
 *  <li>node</li>
 * </ul>
 * 
 * <P><i>Root Properties</i><ul>
 *  <li>The {@code value} is {@code NOT NULL}.</li>
 *  <li>The {@code color} is {@code BLACK}.</li>
 *  <li>The {@code parent} is {@code NULL} and each of the {@code children}
 *      can be {@code LEAF} or {@code NOT NULL}.</li>
 * </ul>
 * 
 * <P><i>Leaf Property</i><br>
 * It's {@code NULL} but the color is {@code BLACK}.
 * 
 * <P><i>Node Properties</i><ul>
 *  <li>The {@code value} is {@code NOT NULL}.</li>
 *  <li>The {@code color} is {@code BLACK} or {@code RED}.</li>
 *  <li>The {@code parent} is {@code NOT NULL} and each of the {@code children}
 *      can be {@code LEAF} or {@code NOT NULL}.</li>
 * </ul>
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the node
 */
public class Node<T> {
    
    T value;
    Color color;
    Node<T> parent, left, right;
    
    private Node(T value, Color color, Node<T> parent) {
        this.value = value;
        this.color = color;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
    
    static<T> Node<T> newRoot(T value) {
        return new Node<>(value, Color.BLACK, null);
    }
    
    static<T> Node<T> newNode(T value, Node<T> parent) {
        return new Node<>(value, Color.RED, parent);
    }
    
    static<T> boolean isBlack(Node<T> node) {
        return node == null || node.color == Color.BLACK;
    }
    
    static<T> boolean isRed(Node<T> node) {
        return node != null && node.color == Color.RED;
    }
    
    Node<T> sibling() {
        return parent.left == this ? parent.right : parent.left;
    }
    
    Node<T> grandpa() {
        return parent.parent;
    }
    
    Node<T> uncle() {
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
    void rotateLeft() {
        Node r = right;
        r.parent = parent;
        if (parent != null) {
            if (parent.left == this) {
                parent.left = r;
            } else {
                parent.right = r;
            }
        }
        right = r.left;
        if (r.left != null) {
            r.left.parent = this;
        }
        parent = r;
        r.left = this;
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
    void rotateRight() {
        Node l = left;
        l.parent = parent;
        if (parent != null) {
            if (parent.left == this) {
                parent.left = l;
            } else {
                parent.right = l;
            }
        }
        left = l.right;
        if (l.right != null) {
            l.right.parent = this;
        }
        parent = l;
        l.right = this;
    }
    
    /**
     * Returns the value maintained by the node.
     * @return the value maintained by the node
     */
    public T getValue() {
        return value;
    }
    
    /**
     * Returns the color maintained by the node.
     * @return the color maintained by the node
     */
    public Color getColor() {
        return color;
    }
    
}
