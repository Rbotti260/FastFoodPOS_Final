package com.example.fastfoodpos_rev3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;


public class SomethingBST extends JPanel {
    private static final long serialVersionUID = 1L;
    private BSTree tree; // A binary tree to be displayed
    private JTextField jtfKey = new JTextField(5);
    private TreeView view = new TreeView();
    private JButton jbtInsert = new JButton("Insert");
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtSearch = new JButton("Search");
    private ArrayList<Integer> searchPath = new ArrayList<>();
    private int showSearchLength = -1;
    private Timer timer1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("orderFood");
        JApplet applet = new DisplayBST();
        frame.add(applet);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class DisplayBST extends JApplet {
        private static final long serialVersionUID = 1L;
        public DisplayBST() {
            add(new SomethingBST(new BSTree(new Integer[] {100, 29, 100, 45, 57, 67, 2, 101, 69})));
        }
    }

    /** Construct a view for a binary tree */
    public SomethingBST(BSTree tree) {
        this.tree = tree; // Set a binary tree to be displayed
        setUI();
    }

    /** Initialize UI for binary tree */
    private void setUI() {
        this.setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter a key: "));
        panel.add(jtfKey);
        panel.add(jbtInsert);
        panel.add(jbtDelete);
        panel.add(jbtSearch);
        add(panel, BorderLayout.SOUTH);

        jbtInsert.addActionListener(new ActionListener() {
            @Override
            // Process the Insert button event
            public void actionPerformed(ActionEvent e) {
                searchPath = null;
                showSearchLength = -1;
                int key = Integer.parseInt(jtfKey.getText());
                if (tree.search(key)) { // key is in the tree already
                    JOptionPane.showMessageDialog(null, key
                            + " is already in the tree");
                } else {
                    tree.insert(key); // Insert a new key
                }
                view.repaint();
            }
        });

        jbtDelete.addActionListener(new ActionListener() {
            @Override
            // Process the Delete button event
            public void actionPerformed(ActionEvent e) {
                searchPath = null;
                showSearchLength = -1;
                int key = Integer.parseInt(jtfKey.getText());
                if (!tree.search(key)) { // key is not in the tree
                    JOptionPane.showMessageDialog(null, key + " is not in the tree");
                } else {
                    tree.delete(key); // Delete a key
                }
                view.repaint();
            }
        });

        jbtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int key = Integer.parseInt(jtfKey.getText());
                searchPath = tree.searchPath(key);
                showSearchLength = 0;
                jtfKey.setEditable(false);
                jbtInsert.setEnabled(false);
                jbtDelete.setEnabled(false);
                jbtSearch.setEnabled(false);
                timer1.start();
            }
        });

        timer1 = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showSearchLength >= searchPath.size()) {
                    timer1.stop();
                    jtfKey.setEditable(true);
                    jbtInsert.setEnabled(true);
                    jbtDelete.setEnabled(true);
                    jbtSearch.setEnabled(true);
                    JOptionPane.showMessageDialog(null, jtfKey.getText() + " is not in the tree");
                    showSearchLength = -1;
                    view.repaint();
                } else {
                    showSearchLength++;
                    view.repaint();
                    if(Integer.parseInt(jtfKey.getText()) == searchPath.get(showSearchLength - 1)) {
                        timer1.stop();
                        jtfKey.setEditable(true);
                        jbtInsert.setEnabled(true);
                        jbtDelete.setEnabled(true);
                        jbtSearch.setEnabled(true);
                    }
                }
            }
        });
    }

    // Inner class TreeView for displaying a tree on a panel
    class TreeView extends JPanel {
        private static final long serialVersionUID = 1L;
        private int radius = 20; // Tree node radius
        private int vGap = 50; // Gap between two levels in a tree

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (tree.getRoot() != null) {
                // Display tree recursively
                displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4, 0);
            }
        }

        /** Display a subtree rooted at position (x, y) */
        private void displayTree(Graphics g, BSTree.TreeNode<Integer> root, int x,
                                 int y, int hGap, int lenght) {
            //System.out.println("lenght " + lenght);
            //System.out.println("showSearchLength " + showSearchLength);
            if(lenght < showSearchLength) {
                if((searchPath != null)&&(searchPath.contains(root.element))) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
                    g.setColor(Color.BLACK);
                }
            }

            g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
            g.drawString(root.element + "", x - 6, y + 4);

            if (root.left != null) {
                // Draw a line to the left node
                connectTwoCircles(g, x - hGap, y + vGap, x, y);
                // Draw the left subtree recursively
                displayTree(g, root.left, x - hGap, y + vGap, hGap / 2, lenght + 1);
            }

            if (root.right != null) {
                // Draw a line to the right node
                connectTwoCircles(g, x + hGap, y + vGap, x, y);
                // Draw the right subtree recursively
                displayTree(g, root.right, x + hGap, y + vGap, hGap / 2, lenght + 1);
            }
        }

        /** Connect two circles centered at (x1, y1) and (x2, y2) */
        private void connectTwoCircles(Graphics g, int x1, int y1, int x2,
                                       int y2) {
            double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
            int x11 = (int) (x1 - radius * (x1 - x2) / d);
            int y11 = (int) (y1 - radius * (y1 - y2) / d);
            int x21 = (int) (x2 + radius * (x1 - x2) / d);
            int y21 = (int) (y2 + radius * (y1 - y2) / d);
            g.drawLine(x11, y11, x21, y21);
        }
    }

    static class BSTree<E extends Comparable<E>> extends AbstractTree<E> {
        protected TreeNode<E> root;
        protected int size = 0;

        public void inorder2() {
            if(root == null) {
                return;
            }

            LinkedList<TreeNode<E>> list = new LinkedList<>();
            LinkedList<TreeNode<E>> stack = new LinkedList<>();
            stack.add(root);

            while (!stack.isEmpty()) {
                TreeNode<E> node = stack.getFirst();
                if ((node.left != null) && (!list.contains(node.left))) {
                    stack.push(node.left);
                } else {
                    stack.removeFirst();
                    list.add(node);
                    if (node.right != null) {
                        stack.addFirst(node.right);
                    }
                }
            }
            for (TreeNode<E> treeNode : list) {
                System.out.print(treeNode.element + " ");
            }
        }


        public boolean isFullBST() {
            return size == Math.round(Math.pow(2, height()) - 1);
        }

        /** Returns the height of the binary tree, i.e. */
        public int height() {
            return height(root);
        }

        public int height(TreeNode<E> node) {
            if(node == null) {
                return 0;
            } else {
                return 1 + Math.max(height(node.left), height(node.right));
            }
        }

        /** Create a default binary tree */
        public BSTree() {
        }

        /** Create a binary tree from an array */
        public BSTree(E[] objects) {
            for (int i = 0; i < objects.length; i++)
                insert(objects[i]);
        }


        /** Returns true if the element is in the tree */
        public ArrayList<E> searchPath(E e) {
            TreeNode<E> current = root; // Start from the root
            ArrayList<E> result = new ArrayList<>();
            while (current != null) {
                result.add(current.element);
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else {
                    return result;
                }
            }
            return result;
        }

        @Override
        /** Returns true if the element is in the tree */
        public boolean search(E e) {
            TreeNode<E> current = root; // Start from the root

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else
                    // element matches current.element
                    return true; // Element is found
            }

            return false;
        }

        @Override
        /** Insert element o into the binary tree.
         */
        public boolean insert(E e) {
            if (root == null)
                root = createNewNode(e); // Create a new root
            else {
                // Locate the parent node
                TreeNode<E> parent = null;
                TreeNode<E> current = root;
                while (current != null)
                    if (e.compareTo(current.element) < 0) {
                        parent = current;
                        current = current.left;
                    } else if (e.compareTo(current.element) > 0) {
                        parent = current;
                        current = current.right;
                    } else
                        return false; // Duplicate node not inserted

                // Create the new node and attach it to the parent node
                if (e.compareTo(parent.element) < 0)
                    parent.left = createNewNode(e);
                else
                    parent.right = createNewNode(e);
            }

            size++;
            return true; // Element inserted
        }

        protected TreeNode<E> createNewNode(E e) {
            return new TreeNode<E>(e);
        }

        @Override
        /** Inorder traversal from the root*/
        public void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        protected void inorder(TreeNode<E> root) {
            if (root == null)
                return;
            inorder(root.left);
            System.out.print(root.element + " ");
            inorder(root.right);
        }

        @Override
        /** Postorder traversal from the root */
        public void postorder() {
            postorder(root);
        }

        /** Postorder traversal from a subtree */
        protected void postorder(TreeNode<E> root) {
            if (root == null)
                return;
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.element + " ");
        }

        @Override
        /** Preorder traversal from the root */
        public void preorder() {
            preorder(root);
        }

        /** Preorder traversal from a subtree */
        protected void preorder(TreeNode<E> root) {
            if (root == null)
                return;
            System.out.print(root.element + " ");
            preorder(root.left);
            preorder(root.right);
        }

        /**
         * This inner class is static, because it does not access any instance
         * members defined in its outer class
         */
        public static class TreeNode<E extends Comparable<E>> {
            protected E element;
            protected TreeNode<E> left;
            protected TreeNode<E> right;

            public TreeNode(E e) {
                element = e;
            }
        }

        @Override
        /** Get the number of nodes in the tree */
        public int getSize() {
            return size;
        }

        /** Returns the root of the tree */
        public TreeNode<E> getRoot() {
            return root;
        }

        /** Returns a path from the root leading to the specified element */
        public java.util.ArrayList<TreeNode<E>> path(E e) {
            java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
            TreeNode<E> current = root; // Start from the root

            while (current != null) {
                list.add(current); // Add the node to the list
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else
                    break;
            }

            return list; // Return an array of nodes
        }

        @Override
        /** Delete an element from the binary tree.
         */
        public boolean delete(E e) {
            // Locate the node to be deleted and also locate its parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    break; // Element is in the tree pointed at by current
            }

            if (current == null)
                return false; // Element is not in the tree

            // Case 1: current has no left children
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) {
                    root = current.right;
                } else {
                    if (e.compareTo(parent.element) < 0)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                }
            } else {
                // Case 2: The current node has a left child
                // Locate the rightmost node in the left subtree of
                // the current node and also its parent
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // Keep going to the right
                }

                // Replace the element in current by the element in rightMost
                current.element = rightMost.element;

                // Eliminate rightmost node
                if (parentOfRightMost.right == rightMost)
                    parentOfRightMost.right = rightMost.left;
                else
                    // Special case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;
            }

            size--;
            return true; // Element inserted
        }

        @Override
        /** Obtain an iterator. Use inorder. */
        public java.util.Iterator<E> iterator() {
            return new InorderIterator();
        }

        // Inner class InorderIterator
        private class InorderIterator implements java.util.Iterator<E> {
            // Store the elements in a list
            private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
            private int current = 0; // Point to the current element in list

            public InorderIterator() {
                inorder(); // Traverse binary tree and store elements in list
            }

            /** Inorder traversal from the root */
            private void inorder() {
                inorder(root);
            }

            /** Inorder traversal from a subtree */
            private void inorder(TreeNode<E> root) {
                if (root == null)
                    return;
                inorder(root.left);
                list.add(root.element);
                inorder(root.right);
            }

            @Override
            /** More elements for traversing? */
            public boolean hasNext() {
                if (current < list.size())
                    return true;

                return false;
            }

            @Override
            /** Get the current element and move to the next */
            public E next() {
                return list.get(current++);
            }

            @Override
            /** Remove the current element */
            public void remove() {
                delete(list.get(current)); // Delete the current element
                list.clear(); // Clear the list
                inorder(); // Rebuild the list
            }
        }

        /** Remove all elements from the tree */
        public void clear() {
            root = null;
            size = 0;
        }
    }

    static abstract class AbstractTree<E> implements Tree<E> {
        @Override
        /** Inorder traversal from the root*/
        public void inorder() {
        }

        @Override
        /** Postorder traversal from the root */
        public void postorder() {
        }

        @Override
        /** Preorder traversal from the root */
        public void preorder() {
        }

        @Override
        /** Return true if the tree is empty */
        public boolean isEmpty() {
            return getSize() == 0;
        }

        @Override
        /** Return an iterator for the tree */
        public java.util.Iterator<E> iterator() {
            return null;
        }
    }

    interface Tree<E> extends Iterable<E> {
        /** Return true if the element is in the tree */
        public boolean search(E e);

        /**
         * Insert element o into the binary tree
         */
        public boolean insert(E e);

        /**
         * Delete the specified element from the tree
         */
        public boolean delete(E e);

        /** Inorder traversal from the root */
        public void inorder();

        /** Postorder traversal from the root */
        public void postorder();

        /** Preorder traversal from the root */
        public void preorder();

        /** Get the number of nodes in the tree */
        public int getSize();

        /** Return true if the tree is empty */
        public boolean isEmpty();

        public java.util.Iterator<E> iterator();
    }
}
