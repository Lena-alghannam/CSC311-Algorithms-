import java.util.LinkedList;
import java.util.Queue;

class OrganizationTree {
    private Employee root;  // The root of the tree (the top-level manager)

    // Variables to track the overall optimal combination
    private String optimalEmployee1;
    private String optimalEmployee2;
    private int highestOverallSkillLevel;

    // Constructor to initialize the organization tree
    public OrganizationTree() {
        this.root = null;
        this.highestOverallSkillLevel = 0;  // Initialize the highest skill level to 0
    }

    // Method to add an employee to the tree
    public void addEmployee(Employee curr) {
        // If the employee is the root (parentId is 0), set it as the root
        if (curr.getParentID() == 0) {
            if (root == null) {
                root = curr;  // Set the root if not already set
            } else {
                System.out.println("Root already exists!");
            }
        } else {
            // Otherwise, find the parent and add the employee as its child
            Employee parent = findEmployee(curr.getParentID());
            if (parent != null) {
                addSubordinate(parent, curr);
            } else {
                System.out.println("Parent with ID " + curr.getParentID() + " not found.");
            }
        }
    }

    public Employee findEmployee(int id) {
        if (root == null) return null;

        // Initialize a queue-like structure (stack) to iterate through the nodes
        Employee current = root;

        while (current != null) {
            // Check if the current node is the one we're looking for
            if (current.getId() == id) {
                return current;
            }

            // Traverse all subordinates (use the left child to go down subordinates)
            Employee subordinate = current.left;
            while (subordinate != null) {
                if (subordinate.id == id) {
                    return subordinate;
                }
                // Traverse deeper into subordinates' own subordinates
                Employee result = findEmployee(subordinate, id);
                if (result != null) {
                    return result;
                }
                subordinate = subordinate.right;  // Move to the next sibling
            }

            // Traverse the next sibling
            current = current.right;
        }

        // If not found, return null
        return null;
    }

    // Helper method to find an employee starting from a given node
    private Employee findEmployee(Employee node, int id) {
        if (node == null) return null;

        // If this is the node we're looking for, return it
        if (node.id == id) {
            return node;
        }

        // Traverse subordinates
        Employee subordinate = node.left;
        while (subordinate != null) {
            if (subordinate.id == id) {
                return subordinate;
            }
            subordinate = subordinate.right;  // Move to the next sibling
        }

        return null;
    }

    // Method to find all valid combinations of employees using BFS
    public void findAllCombinations() {
        if (root == null) return;  // If the tree is empty, return
        outerBFS();  // Call the outer BFS method

        // After processing all nodes, print the overall optimal combination
        if (optimalEmployee1 != null && optimalEmployee2 != null) {
            System.out.println("Overall Optimal Combination: " + optimalEmployee1 + ", " + optimalEmployee2 +
                ": Total Skill Level = " + highestOverallSkillLevel);
        } else {
            System.out.println("No valid combinations found.");
        }
    }

    // Outer BFS traversal method
    private void outerBFS() {
        // Initialize a queue for BFS
        Queue<Employee> outerQueue = new LinkedList<>();
        outerQueue.offer(root);  // Start with the root

        while (!outerQueue.isEmpty()) {
            Employee selectedNode = outerQueue.poll();  // Dequeue the front node
            System.out.println("Outer BFS current node: " + selectedNode);  // Print the current node

            // Call the inner BFS for combinations with the current node
            innerBFS(selectedNode);  // Find valid combinations for this selected node

            // Enqueue all subordinates for the next outer iteration
            Employee subordinate = selectedNode.left;  // Get the first subordinate
            while (subordinate != null) {
                outerQueue.offer(subordinate);  // Enqueue the subordinate
                subordinate = subordinate.right;  // Move to the next sibling
            }
        }
    }

    // Inner BFS traversal method to find combinations for a selected node
    private void innerBFS(Employee selectedNode) {
        Queue<Employee> innerQueue = new LinkedList<>();
        innerQueue.offer(root);  // Start from the root for combinations

        while (!innerQueue.isEmpty()) {
            Employee current = innerQueue.poll();  // Dequeue the front node

            // Skip if it's the same node or if one is the subordinate/supervisor of the other
            if (current.getId() != selectedNode.getId() 
            && current.getParentID() != selectedNode.getId() 
            && selectedNode.getParentID() != current.getId()
            && selectedNode.getId() < current.getId()) {
                
                // Calculate the skill level for this combination
                int skillLevel = calculateSkillLevel(selectedNode, current);
                System.out.println("Valid Combination: " + selectedNode.getName() + " and " + current.getName() +
                    ": Skill Level = " + skillLevel);

                // Update the optimal combination if necessary
                if (skillLevel > highestOverallSkillLevel) {
                    highestOverallSkillLevel = skillLevel;
                    optimalEmployee1 = selectedNode.getName();
                    optimalEmployee2 = current.getName();
                }
            }

            // Enqueue subordinates for the current employee
            Employee subordinate = current.left;
            while (subordinate != null) {
                innerQueue.offer(subordinate);  // Enqueue subordinates
                subordinate = subordinate.right;  // Move to the next sibling
            }
        }
    }

    // Method to calculate the total skill level of a combination
    private int calculateSkillLevel(Employee emp1, Employee emp2) {
        return emp1.getSkillLevel() + emp2.getSkillLevel();  // Sum the skill levels
    }

    // Method to add a subordinate to a parent employee
    public void addSubordinate(Employee parent, Employee curr) {
        if (parent.left == null) {
            parent.left = curr;  // Add as the first subordinate (left child)
        } else {
            // Find the rightmost sibling
            Employee sibling = parent.left;  
            while (sibling.right != null) {
                sibling = sibling.right;
            }
            sibling.right = curr;  // Add the new employee as the right sibling
        }
        curr.parent = parent;  // Set the parent reference
    }

    // Method to print the tree (pre-order traversal)
    public void printTree(Employee employee, String indent) {
        if (employee == null) return;
        System.out.println(indent + employee);

        // Print the first subordinate (left child)
        printTree(employee.left, indent + "");
        // Print the next sibling (right child)
        printTree(employee.right, indent);
    }

    // Method to retrieve the root of the organization
    public Employee getRoot() {
        return root;
    }
}
