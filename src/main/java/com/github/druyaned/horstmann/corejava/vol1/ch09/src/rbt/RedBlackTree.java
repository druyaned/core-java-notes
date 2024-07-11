package com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbt;

import static com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbt.Color.BLACK;
import static com.github.druyaned.horstmann.corejava.vol1.ch09.src.rbt.Color.RED;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Red-Black Tree implementation. That's a self-balancing binary search tree
 * used in the {@link java.util.TreeMap TreeMap} as an example.
 * <a href=https://ru.wikipedia.org/wiki/Красно-чёрное_дерево>
 * Very good wiki-page</a>. The most interesting activities for the tree
 * are {@link #add insertion} and {@link #remove removal}.
 * The properties (balance) can be violated in case of the insertion or removal.
 * The balance is achieved through left- or right-rotation of the {@link Node}.
 * Decision of the rotation is made according to the color of the nearest nodes.
 * 
 * <P><i>Properties</i><ol>
 *  <li>Every node is either red or black.</li>
 *  <li>The root is basically black.</li>
 *  <li>All leafs are black.</li>
 *  <li>A red node has only black children (and parent).</li>
 *  <li>Every path from a given node to any of its descendant leafs
 *      goes through the same number of black nodes
 *      (the main property, should be remembered).</li>
 * </ol>
 * 
 * <P><i>Left-rotation of a node</i><pre>
 *    N              R   
 *   / \            / \  
 *  L   R    -->   N   RR
 *     / \        / \    
 *    RL  RR     L   RL  
 * </pre>
 * <i>Right-rotation of a node</i><pre>
 *       N              L    
 *      / \            / \   
 *     L   R   -->   LL   N  
 *    / \                / \ 
 *  LL   LR            LR   R
 * </pre>
 * 
 * <P><i>Structure of the RedBlackTree class</i><pre>
 *  public class RedBlackTree&lt;T&gt; {
 *  private:
 *      Node&lt;T&gt; root;
 *      int size;
 *      final Comparator&lt;T&gt; comp;
 *  public:
 *      Constructor(...);
 *      Node&lt;T&gt; getRoot();
 *      int size();
 *      Comparator&lt;T&gt; getComparator();
 *      boolean isEmpty();
 *      Node&lt;T&gt; get(T value);
 *      void forEach(Consumer&lt;Node&lt;T&gt;&gt; consumer);
 *      boolean add(T value);
 *      boolean remove(T value);
 *  }
 * </pre>
 * 
 * @author druyaned
 * @param <T> the type of value maintained by the tree
 * @see Node
 */
public class RedBlackTree<T> {
    
    private Node<T> root;
    private int size;
    private final Comparator<T> comp;
    
    /**
     * Creates a new Red-Black Tree with the given comparator to compare values.
     * @param comp to compare values
     */
    public RedBlackTree(Comparator<T> comp) {
        this.comp = comp;
    }
    
    /**
     * Returns root of the tree.
     * @return root of the tree
     */
    public Node<T> getRoot() {
        return root;
    }
    
    /**
     * Returns number of values of the tree.
     * @return number of values of the tree
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns comparator for the values in the tree.
     * @return comparator for the values in the tree
     */
    public Comparator<T> getComparator() {
        return comp;
    }
    
    /**
     * Returns {@code true} if the tree is empty, otherwise {@code false}.
     * @return {@code true} if the tree is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns found {@link Node node} of the value in this tree
     * or {@code null} if there is no such value. Complexity is O(log(n)).
     * 
     * @param value to found a node
     * @return found {@link Node node} of the value in this tree
     *      or {@code null} if there is no such value
     */
    public Node<T> get(T value) {
        if (root == null) {
            return null;
        }
        for (Node<T> node = root; node != null; ) {
            if (comp.compare(node.value, value) == 0) {
                return node;
            } else if (comp.compare(node.value, value) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }
    
    /**
     * Accepts the {@code action} for each {@link Node node} of the tree in ascending order.
     * 
     * <P><i>Execution</i>:<ol>
     *  <li>Create stack of nodes to accept the action in a correct order.</li>
     *  <li>Prepare the stack by going to the minimum during filling the stack.</li>
     *  <li>Loop<ol>
     *      <li>Extract the node and accept the action.</li>
     *      <li>Go to the right child and go to the minimum during filling the stack.</li>
     *  </ol></li>
     * </ol>
     * 
     * @param action to be accepted
     */
    public void forEach(Consumer<Node<T>> action) {
        if (root == null) {
            return;
        }
        Object[] stack = new Object[size];
        int stackSize = 0;
        for (Node<T> node = root; node != null; ) {
            stack[stackSize++] = node;
            node = node.left;
        }
        while (stackSize > 0) {
            Node<T> node = (Node<T>)stack[--stackSize];
            action.accept(node);
            node = node.right;
            while (node != null) {
                stack[stackSize++] = node;
                node = node.left;
            }
        }
    }
    
    /**
     * Inserts a node of the value into the tree if there hasn't been such node.
     * 
     * <P>A new node in the red-black tree is inserted into place of one of the leafs,
     * is colored red and 2 leafs are attached as his children.
     * What happens next depends on the color of the nearest nodes.
     * <i>Note</i>: a balance case can have a mirror analogue that the code
     * takes care of.
     * 
     * <P><i>Illustration Definitions</i><br>
     * b - black color;<br>
     * r - red color;<br>
     * N - current node;<br>
     * L - left child of the node;<br>
     * R - right child of the node;<br>
     * P - parent of the node;<br>
     * G - grandpa of the node;<br>
     * U - uncle of the node;<br>
     * S - sibling of the node;<br>
     * SL - left child of the node's sibling;<br>
     * SR - right child of the node's sibling;<br>
     * * - subtree with at least one black node;<br>
     * So 19(P b) means a parent of the current node with a black color and value=19;
     * (b) is just a leaf.
     * 
     * <P><i>Insertion Balance Execution Sequence</i><pre>
     *  if (case1) { return; }
     *  if (case2) { return; }
     *  if (case3) { case1(G); }
     *  else {
     *      case4;
     *      case5; // possible root change
     *      return;
     *  }
     * </pre>
     * 
     * <P><i>Case 1</i><br>
     * <b>N</b> is a root. It is colored black to meet the 2nd property.
     * And that's it. All other properties are not violated
     * (black node can have either red or black children).
     * 
     * <P><i>Case 2</i><br>
     * <u>Illustration</u><pre>
     *       |                           |       
     *     5(P b)                      8(P b)    
     *     /    \   [or mirror case]   /    \    
     *  2(N r)   *                    *    9(N r)
     *   / \                                / \  
     *  *   *                              *   * 
     * </pre>
     * <b>P</b> is black. Nothing should be changed. The property 5
     * is not violated cause there wasn't a red node before the insertion,
     * and a red node doesn't change the number of black nodes in the subtree.
     * <i>Note</i>: in the following cases <b>P</b> is red.
     * 
     * <P><i>Case 3</i><br>
     * <u>Illustration</u><pre>
     *          |                       |        
     *        7(G b)                  7(G r)     
     *        /    \                  /    \     
     *    5(P r)   9(U r)         5(P b)   9(U b)
     *     /  \     / \    -->     /  \     / \  
     *  3(N r) *   *   *        3(N r) *   *   * 
     *   / \                     / \             
     *  *   *                   *   *            
     * </pre>
     * <b>G</b> is black (property 4: red node definition), <b>P</b> is red and
     * <b>U</b> is red. The property 5 is not violated but the 4th is.
     * What is left to do? Recolor <b>G</b>, <b>P</b> and <b>U</b>.
     * The number of black nodes in subtrees is not changed. But the structure differs
     * so the property 4 can be violated. It's necessary to back to the case 1
     * relative to <b>G</b>.
     * 
     * <P><i>Case 4</i><br>
     * <u>Illustration</u><pre>
     *          |                          |         
     *        7(G b)                     7(G b)      
     *        /    \                     /    \      
     *  5(P r)      9(U b)         6(N r)      9(U b)
     *   /  \        / \    -->     /  \        / \  
     *  *    6(N r)              5(P r) *            
     *        / \                 / \                
     *       *   *               *   *               
     * </pre>
     * <b>G</b> is black (property 4: red node definition), <b>P</b> is red and
     * <b>U</b> is black. The property 5 is not violated but the 4th is.
     * Left-rotate <b>P</b> to move forward to the case 5.
     * <i>Note</i>: after the rotation the current Node becomes the old Parent.
     * 
     * <P><i>Case 5</i><br>
     * <u>Illustration</u><pre>
     *            |                        |            
     *          7(G b)                   6(P b)         
     *          /    \                   /    \         
     *    6(P r)      9(U b)       5(N r)      7(G r)   
     *     /  \        / \    -->   /  \        / \     
     *  5(N r) *                   *    *      *  9(U b)
     *   / \                                       / \  
     *  *   *                                           
     * </pre>
     * The continuation of the case 4.
     * Right-rotate <b>G</b>, recolor <b>G</b> and <b>P</b>.
     * All the properties are not violated.<br>
     * <i>Note</i>: after the rotations the root can be changed.
     * 
     * <P><i>Formulary Explanation of the Trick</i><pre>
     *  rr(b)b --> r(r)bb --> r(b)rb
     * </pre>
     * 
     * @param value to be inserted
     * @return {@code true} if the value is added,
     *      {@code false} if it has already been added
     */
    public boolean add(T value) {
        if (size == 0) {
            root = Node.newRoot(value);
            size++;
            return true;
        }
        Node<T> node = root, parent;
        int cmp;
        do {
            parent = node;
            cmp = comp.compare(node.value, value);
            if (cmp < 0) {
                node = node.right;
            } else if (cmp > 0) {
                node = node.left;
            } else {
                return false;
            }
        } while (node != null);
        if (cmp < 0) {
            parent.right = Node.newNode(value, parent);
            insertionBalance(parent.right);
        } else {
            parent.left = Node.newNode(value, parent);
            insertionBalance(parent.left);
        }
        size++;
        return true;
    }
    
    private void insertionBalance(Node<T> node) {
        while (node.parent != null && node.parent.color == RED) { // cases 1, 2
            // case 3: recolor G, P and U
            Node<T> u = node.uncle();
            if (u != null && u.color == RED) {
            // && node.parent.color == RED
                node.parent.color = BLACK;
                u.color = BLACK;
                Node<T> g = node.grandpa();
                g.color = RED;
                node = g;
                continue; // go to the case 1 relative to G
            }
            // case 4: rotate P to prepare for the case 5
            Node<T> g = node.grandpa();
            if (node == node.parent.right && g.left == node.parent) {
                node.parent.rotateLeft();
                node = node.left;
            } else if (node == node.parent.left && g.right == node.parent) {
                node.parent.rotateRight();
                node = node.right;
            }
            // case 5: rotate G, recolor G and P
            g = node.grandpa();
            node.parent.color = BLACK;
            g.color = RED;
            if (node == node.parent.left && node.parent == g.left) {
                g.rotateRight();
            } else {
                g.rotateLeft();
            }
            if (node.parent.parent == null) {
                root = node.parent;
            }
            return;
        }
        root.color = BLACK;
    }
    
    /**
     * Removes the value from this tree, if there has been any.
     * See the illustration definitions in the {@link #add insertion}.
     * 
     * <P><i>Main Removal Stages</i>:<ol>
     *  <li>find the node</li>
     *  <li>find the limit</li>
     *  <li>copy limit value into the found node</li>
     *  <li>remove the limit by replacing by its child</li>
     *  <li>balance cases</li>
     * </ol>
     * 
     * <P>After finding the node with the value the limit is being determined -
     * the lowest value of the right subtree or the highest in the left subtree.
     * The limit has at most one not-leaf-child (otherwise it's not the limit).
     * Then the values between the node with the given value and the limit are replaced.
     * 
     * <P>Let the limit (min or max) node be <b>M</b> and his not-leaf-child
     * or any leaf-child be <b>C</b>. At this moment there could be some simple cases.<br>
     * <u>Simple Case 1</u><pre>
     *     |              |   
     *   4(M r)          (C b)
     *    /  \      -->       
     *  (b)  (C b)            
     * </pre>
     * If <b>M</b> is red it is replaced by <b>C</b> which can be only a leaf
     * (otherwise the 5th or 4th properties are violated for the origin tree).
     * All properties are not violated after the replacement, so that's it.<br>
     * <u>Simple Case 2</u><pre>
     *       |              |    
     *     5(M b)         3(C b) 
     *      /  \           /  \  
     *  3(C r) (b)  -->  (b)  (b)
     *    / \                    
     *  (b) (b)                  
     * </pre>
     * If <b>M</b> is black and <b>C</b> is red <b>M</b> is replaced
     * by <b>C</b> and is being colored black. All properties are not violated.<br>
     * <u>Complicated Case 3</u><pre>
     *       |           |   
     *     4(M b)       (C b)
     *      / \    -->       
     *  (C b) (b)            
     * </pre>
     * <b>M</b> and <b>C</b> are black. After replacement by child
     * the property 5 is violated. That's why this case produces
     * the other 6 balance cases.<br>
     * <i>Important Notes</i><ol>
     *  <li>The children of <b>M</b> are both leafs (otherwise the property 5
     *      is violated for the origin tree).</li>
     *  <li>In the following balance cases <b>C</b> (which replaced <b>M</b>)
     *      is called as <b>N</b>.</li>
     *  <li>All balance cases are designed to make the tree valid for
     *      the 5th and all other properties.</li>
     *  <li>A balance case can have a mirror analogue that the code
     *      takes is care of.</li>
     * </ol>
     * 
     * <P><i>Removal Balance Execution Sequence</i><pre>
     *  if (case1) { return; };
     *  if (case2) { case1(P); }
     *  else {
     *      case3; // possible root change
     *      if (case4) { return; }
     *      case5;
     *      case6; // possible root change
     *      return;
     *  }
     * </pre>
     * 
     * <P><i>Removal Balance Formulary Notation</i><pre>
     *  case2: b(b)bb -> b(b)rb
     *  case3: b(b)rb* --> [b(r)b*](b)b* --> b(r)b*
     *  case4: b(r)bb --> b(b)rb
     *  case5: *r(b)b --> *(b)[*(r)b]
     *  case6: b(a)[*(b)r*] --> bb(a)b*
     * </pre>
     * 
     * <P><i>Case 1</i><br>
     * If <b>N</b> is a root all's done.<br>
     * 
     * <P><i>Case 2</i><br>
     * <u>Illustration</u><pre>
     *          |                            |             
     *        5(P b)                       5(P b)          
     *        /    \                       /    \          
     *  2(N b)      8(S b)      -->  2(N b)      8(S r)    
     *   /  \        / \              /  \         / \     
     *          (SL b) (SR b)                 (SL b) (SR b)
     *            / \   / \                     / \   / \  
     * </pre>
     * <b>N</b>, <b>P</b>, <b>S</b>, <b>SL</b> and <b>SR</b> are black.
     * Recolor <b>S</b> and go to the case 1 for <b>P</b>.
     * Now the property 5 is not violated for subtree of <b>S</b>.
     * But the number of black nodes in the right subtree of <b>P</b>
     * was changed, that's why the going to the case 1 is necessary.<br>
     * <i>Note</i>: <b>SL</b> and <b>SR</b> don't have values cause they
     * can be leafs.
     * 
     * <P><i>Case 3</i><br>
     * <u>Illustration</u><pre>
     *          |                                 |          
     *        5(P b)                            8(S b)       
     *        /    \                            /    \       
     *  3(N b)      8(S r)                5(P r)      9(SR b)
     *   /  \        / \        -->        /  \         / \  
     *         6(SL b) 9(SR b)        3(N b) 6(SL b)   *   * 
     *           / \     / \            / \    / \           
     *          *   *   *   *                 *   *          
     * </pre>
     * <b>N</b>, <b>P</b>, <b>SL</b> and <b>SR</b> are black and <b>S</b> is red.
     * Left-rotate <b>P</b>, recolor <b>P</b> and <b>S</b>. Before the rotation
     * the property 5 was violated so each of <b>SL</b> and <b>SR</b> had
     * at least 1 black child. This case is a preparation for the next changes.<br>
     * <i>Important Notes</i><ol>
     *  <li>The root can be changed.</li>
     *  <li>The left branch of the <b>P</b> ("256") is at the focus
     *      in the following cases (imagine its left-rotation).</li>
     *  <li>Before and after the rotation the right branch of <b>P</b> ("689")
     *      had and has at least 2 black nodes. The task is to make the left
     *      branch having the same amount of black nodes (property 5).</li>
     * </ol>
     * 
     * <P><i>Case 4</i><br>
     * <u>Illustration</u><pre>
     *          |                            |             
     *        5(P r)                       5(P b)          
     *        /    \                       /    \          
     *  2(N b)      8(S b)      -->  2(N b)      8(S r)    
     *   /  \        / \              /  \         / \     
     *          (SL b) (SR b)                 (SL b) (SR b)
     *           / \     / \                   / \     / \ 
     * </pre>
     * An another way or a continuance of the case 3 with the updated roles.
     * <b>P</b> is red and <b>N</b>, <b>S</b>, <b>SL</b> and <b>SR</b> are black.
     * Recolor <b>P</b> and <b>S</b>. The 5th and all other properties are
     * not violated. Otherwise go to the case 5.<br>
     * 
     * <P><i>Case 5</i><br>
     * <u>Illustration</u><pre>
     *         |                  |         
     *       4(S b)            3(SL b)      
     *       /    \             /    \      
     *  3(SL r)   (SR b)  -->  *   4(S r)   
     *     / \     / \              /  \    
     *    *   *                    *  (SR b)
     *                                 / \  
     * </pre>
     * An another continuance of the case 3 and a preparation for the case 6.
     * <b>S</b> and <b>SR</b> are black and <b>SL</b> is red.
     * Right-rotate <b>S</b>, recolor <b>SL</b> and <b>S</b>.
     * 
     * <P><i>Case 6</i><br>
     * <u>Illustration</u><pre>
     *       |                         |         
     *      4(P)                      6(S)       
     *     /    \                    /    \      
     *  3(N b) 6(S b)             4(P b)  8(SR b)
     *   / \    / \       -->      /  \     / \  
     *         * 8(SR r)       3(N b)  *   *   * 
     *            / \            / \             
     *           *   *                           
     * </pre>
     * A continuance of the case 5. <b>P</b> can be red or black,
     * <b>N</b> and <b>S</b> are black, <b>SR</b> is red.
     * Left-rotate <b>P</b>, recolor <b>S</b> as <b>P</b>,
     * recolor <b>P</b> and <b>SR</b> to black.
     * So the 5th and all others properties are not violated.<br>
     * <i>Note</i>: the root can be changed.
     * 
     * @param value to be removed
     * @return {@code true} if the value was found and it was removed,
     *      otherwise - {@code false}
     */
    public boolean remove(T value) {
        Node<T> found = get(value);
        if (found == null) {
            return false;
        }
        Node<T> limit = found; // l==null && r==null
        if (found.left != null && found.right == null) {
            limit = found.left;
        } else if (found.right != null) { // (l==null && r!=null) || (l!=null && r!=null)
            limit = found.right;
            while (limit.left != null) {
                limit = limit.left;
            }
        }
        found.value = limit.value;
        deleteLimit(limit);
        size--;
        return true;
    }
    
    private void deleteLimit(Node<T> limit) {
        if (limit.color == RED) { // simple case 1
            replaceLimitParent(limit, null);
            return;
        } // limit.color == BLACK
        Node<T> child = limit.left == null ? limit.right : limit.left;
        if (child != null) { // simple case 2
            child.color = BLACK;
            child.parent = limit.parent;
            replaceLimitParent(limit, child);
            return;
        } // child == null; complicated case 3
        removalBalance(limit);
        replaceLimitParent(limit, null);
    }
    
    private void replaceLimitParent(Node<T> limit, Node<T> child) {
        if (limit.parent != null) {
            if (limit.parent.left == limit) {
                limit.parent.left = child;
            } else {
                limit.parent.right = child;
            }
        } else {
            root = child;
        }
    }
    
    private void removalBalance(Node<T> node) {
        while (node.parent != null) {
            // case 2: recolor S and go to the case 1 for P
            Node<T> sibling = node.sibling();
            if (
                    node.parent.color == BLACK &&
                    sibling.color == BLACK &&
                    Node.isBlack(sibling.left) &&
                    Node.isBlack(sibling.right)
            ) {
                sibling.color = RED;
                node = node.parent;
                continue; // case1(node.parent)
            }
            // case 3: left-rotate P, recolor P and S
            if (sibling.color == RED) {
                node.parent.color = RED;
                sibling.color = BLACK;
                if (node.parent.left == node) {
                    node.parent.rotateLeft();
                } else {
                    node.parent.rotateRight();
                }
                if (sibling.parent == null) {
                    root = sibling;
                }
            }
            // case 4: recolor P and S
            sibling = node.sibling();
            if (
                    node.parent.color == RED &&
                    sibling.color == BLACK &&
                    Node.isBlack(sibling.left) &&
                    Node.isBlack(sibling.right)
            ) {
                node.parent.color = BLACK;
                sibling.color = RED;
                return;
            }
            // case 5: right-rotate S, recolor SL and S
            if (sibling.color == BLACK) {
                if (
                        node.parent.left == node &&
                        Node.isBlack(sibling.right) &&
                        Node.isRed(sibling.left)
                ) {
                    sibling.color = RED;
                    sibling.left.color = BLACK;
                    sibling.rotateRight();
                } else if (
                        node.parent.right == node &&
                        Node.isBlack(sibling.left) &&
                        Node.isRed(sibling.right)
                ) {
                    sibling.color = RED;
                    sibling.right.color = BLACK;
                    sibling.rotateLeft();
                }
            }
            // case 6: left-rotate P, recolor S as P, recolor P and SR to black
            sibling = node.sibling();
            sibling.color = node.parent.color;
            node.parent.color = BLACK;
            if (node.parent.left == node) {
                sibling.right.color = BLACK;
                node.parent.rotateLeft();
            } else {
                sibling.left.color = BLACK;
                node.parent.rotateRight();
            }
            if (sibling.parent == null) {
                root = sibling;
            }
            return;
        }
    }
    
}
