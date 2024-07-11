package com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbm;

/**
 * The node is an actual component of the {@link RedBlackMap Red-Black Map}.
 * Also it is used to get the {@link Color color}, {@code key} and {@code value}
 * in a public visibility.
 * 
 * <P><i>Structure of the Node class</i><pre>
 *  public class Node&lt;K, V&gt; {
 *  default:
 *      K key;
 *      V value;
 *      Color color;
 *      Node&lt;K, V&gt; parent, left, right;
 *  private:
 *      Constructor(...);
 *  default:
 *      static&lt;K, V&gt; Node&lt;K, V&gt; newRoot(K, V);
 *      static&lt;K, V&gt; Node&lt;K, V&gt; newNode(K, V, Node&lt;K, V&gt; parent);
 *      static&lt;K, V&gt; boolean isBlack(Node&lt;K, V&gt; node)
 *      static&lt;K, V&gt; boolean isRed(Node&lt;K, V&gt; node)
 *      Node&lt;K, V&gt; sibling();
 *      Node&lt;K, V&gt; grandpa();
 *      Node&lt;K, V&gt; uncle();
 *      void rotateLeft();
 *      void rotateRight();
 *  public:
 *      K getKey();
 *      V getValue();
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
 *  <li>The {@code key and value} are {@code NOT NULL}.</li>
 *  <li>The {@code color} is {@code BLACK}.</li>
 *  <li>The {@code parent} is {@code NULL} and each of the {@code children}
 *      can be {@code LEAF} or {@code NOT NULL}.</li>
 * </ul>
 * 
 * <P><i>Leaf Property</i><br>
 * It's {@code NULL} but the color is {@code BLACK}.
 * 
 * <P><i>Node Properties</i><ul>
 *  <li>The {@code key and value} are {@code NOT NULL}.</li>
 *  <li>The {@code color} is {@code BLACK} or {@code RED}.</li>
 *  <li>The {@code parent} is {@code NOT NULL} and each of the {@code children}
 *      can be {@code LEAF} or {@code NOT NULL}.</li>
 * </ul>
 * 
 * @author druyaned
 * @param <K> the type of key maintained by the node
 * @param <V> the type of value maintained by the node
 */
public class Node<K, V> {
    
    K key;
    V value;
    Color color;
    Node<K, V> parent, left, right;
    
    private Node(K key, V value, Color color, Node<K, V> parent) {
        this.key = key;
        this.value = value;
        this.color = color;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
    
    static<K, V> Node<K, V> newRoot(K key, V value) {
        return new Node<>(key, value, Color.BLACK, null);
    }
    
    static<K, V> Node<K, V> newNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, Color.RED, parent);
    }
    
    static<K, V> boolean isBlack(Node<K, V> node) {
        return node == null || node.color == Color.BLACK;
    }
    
    static<K, V> boolean isRed(Node<K, V> node) {
        return node != null && node.color == Color.RED;
    }
    
    Node<K, V> sibling() {
        return parent.left == this ? parent.right : parent.left;
    }
    
    Node<K, V> grandpa() {
        return parent.parent;
    }
    
    Node<K, V> uncle() {
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
     * Returns key maintained by the node.
     * @return key maintained by the node
     */
    public K getKey() {
        return key;
    }
    
    /**
     * Returns the value maintained by the node.
     * @return the value maintained by the node
     */
    public V getValue() {
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
