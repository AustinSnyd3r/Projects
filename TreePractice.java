
public class TreePractice{

    /**
    * Determine the number of nodes based on their number of children.
    Iterative
    */
    private static int[] problem1Iterative(Node root){
        int zeroChild = 0;
        int oneChild = 0;
        int twoChild = 0;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);

        //go until the stack is empty, aka tree gone through
        while(!stack.empty()){
            Node temp = stack.pop();
            //push the right and left children and add to twochild
            if(temp.right != null && temp.left != null){
            stack.push(temp.right);
            stack.push(temp.left);
            twoChild++;
            }
            //push the left child and add to oneChild
            if(temp.left != null && temp.right == null){
            stack.push(temp.left);
            oneChild++;
            }
            //push the right child and add to oneChild
            if(temp.right != null && temp.left == null){
            stack.push(temp.right);
            oneChild++;
            }
            //if there are no children add to zero children.
            if(temp.right == null && temp.left == null){
            zeroChild++;
            }
        }

        return new int[] {
        zeroChild, // nodes with 0 children
        oneChild, // nodes with 1 child
        twoChild // nodes with 2 children
        };
    } 

        /**
        *Determine the number of nodes based on their number of children.
        Recursive
        */
        private static int[] problem1Recursive(Node root){
            //Array to hold the count of nodes with 0, 1, and 2 children
            int[] childCounts = new int[3];

            //if the root is null return array with all zeros
            if (root == null){
                return childCounts;
            }
            //recurse down left then right
            int[] left = problem1Recursive(root.left);
            int[] right = problem1Recursive(root.right);
            //count the nodes that have zero children
            if (root.left == null && root.right == null){
                childCounts[0] = 1;
            }
            childCounts[0] += left[0] + right[0];
            //count nodes with 1 child
            if ((root.left != null && root.right == null) || (root.left == null && root.right != null)){
                childCounts[1] = 1;
            }
            childCounts[1] += left[1] + right[1];
            //count nodes with 2 children
            if (root.left != null && root.right != null){
                childCounts[2] = 1;
            }
            childCounts[2] += left[2] + right[2];

            //return the array of children counts
            return childCounts;
        }

        /**
        * Determine the maximum distance from the root to a leaf.
        Iterative
        */
        private static int problem2Iterative(Node root){
            //empty tree or just root node, return 0 since counting by edges.
            if(root == null || root.left == null && root.right == null){
                return 0;
            }
            // Use two stacks, one for nodes, one for depth
            Stack<Node> nodeStack = new Stack<>();
            Stack<Integer> depthStack = new Stack<>();
            int maxDepth = 0;

            //pushes initial values to stacks.
            depthStack.push(0);
            nodeStack.push(root);

            while(!nodeStack.empty()){
                //assigns the temp and tempDepth for each iteration
                Node temp = nodeStack.pop();
                int tempDepth = depthStack.pop();
                //if its a leaf node, check if max needs to be updated using math.max
            if(temp.left == null && temp.right == null){
                maxDepth = Math.max(maxDepth, tempDepth);
            }
            //pushes same number twice for root with 2 children, depth is kept track of
            //when popping back up the tree by this
            if(temp.right != null){
                nodeStack.push(temp.right);
                //depth of this edge to stack
                depthStack.push(tempDepth + 1);
            }
            if(temp.left != null){
                nodeStack.push(temp.left);
                //depth of edge to stack
                depthStack.push(tempDepth + 1);
            }
            }
            //return the max depth in edges
            return maxDepth;
        }

        /**
        *Determine the maximum distance from the root to a leaf. Recursive
        */
        private static int problem2Recursive(Node root){
            //base case if root is null return
            if(root == null){
                //return -1 because counting by edges, root is at 0.
            return -1;
            }
            //goes all the way to the left, then recurse to right,
            //when base it will return back up and go right again
            int left = problem2Recursive(root.left);
            int right = problem2Recursive(root.right);
            //returns the larger path
            if(right > left){
                return (right+1);
            }
            else{
                return (left+1);
            }
        }
    }