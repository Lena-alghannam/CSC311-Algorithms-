import java.util.LinkedList;
import java.util.Queue;

class OrganizationTree {
    private Employee root;  // The root of the tree (the top-level manager)

    // Variables to track the overall optimal combination
    private Employee optimalEmployee1;
    private Employee optimalEmployee2;
    private int highestOverallSkillLevel;
    private int highestSkillRange;  

    public OrganizationTree() {
        this.root = null;
        this.highestOverallSkillLevel = 0;  
        this.highestSkillRange = 0;  
    }

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

    // Method to find an employee by ID
    public Employee findEmployee(int id) {
        if (root == null) return null;
        return findEmployeeRecursive(root, id);
    }

    // Recursive helper to find an employee by ID
    private Employee findEmployeeRecursive(Employee current, int id) {
        if (current == null) return null;
        if (current.getId() == id) return current;
        Employee leftResult = findEmployeeRecursive(current.left, id);
        if (leftResult != null) return leftResult;
        return findEmployeeRecursive(current.right, id);
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

    // Method to find all valid combinations of employees using BFS
    public void findAllCombinations() {
        if (root == null) return;  // If the tree is empty, return
        outerBFS();  // Call the outer BFS method

        // After processing all nodes, print the overall optimal combination
        if (optimalEmployee1 != null && optimalEmployee2 != null) {
            System.out.println("\n\nOptimal Combination Based on Total Skill Level");
            System.out.println("Among the valid combinations: The combination " + optimalEmployee1.getName() + ", " + optimalEmployee2.getName() +
                " has the highest total skill level of " + highestOverallSkillLevel);
            System.out.println("\n\nOptimal Team: " + optimalEmployee1.getName() + ", " + optimalEmployee2.getName());
            System.out.println("Total Skill Level: " + highestOverallSkillLevel);
            System.out.println("Skill Difference (broad skill set): "
                + Math.max(optimalEmployee1.getSkillLevel(), optimalEmployee2.getSkillLevel()) + " - "
                + Math.min(optimalEmployee1.getSkillLevel(), optimalEmployee2.getSkillLevel()) + " = "
                + highestSkillRange);
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

            // Call the inner BFS for combinations with the current node
            innerBFS(selectedNode);  // Find valid combinations for this selected node

            // Enqueue all subordinates for the next outer iteration
            Employee subordinate = selectedNode.left;  // Get the first subordinate
            while (subordinate != null) {
                outerQueue.offer(subordinate);  // Enqueue the subordinate
                subordinate = subordinate.right;  
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
                int skillRange = calculateSkillRange(selectedNode, current);  // Calculate the skill range (difference)
                System.out.println(selectedNode.getName() + ", " + current.getName() +
                    " Skill Level = " + selectedNode.getSkillLevel() + " + " + current.getSkillLevel() +
                    " = " + skillLevel + " (Skill Difference = " + skillRange + ")");

                // Update the optimal combination if necessary
                if (skillLevel > highestOverallSkillLevel || 
                    (skillLevel == highestOverallSkillLevel && skillRange > highestSkillRange)) {
                    highestOverallSkillLevel = skillLevel;
                    highestSkillRange = skillRange;  
                    optimalEmployee1 = selectedNode;
                    optimalEmployee2 = current;
                }
            }

            // Enqueue subordinates for the current employee
            Employee subordinate = current.left;
            while (subordinate != null) {
                innerQueue.offer(subordinate);  // Enqueue subordinates
                subordinate = subordinate.right;  
            }
        }
    }

    private int calculateSkillLevel(Employee emp1, Employee emp2) {
        return emp1.getSkillLevel() + emp2.getSkillLevel();  // Sum the skill levels
    }

    private int calculateSkillRange(Employee emp1, Employee emp2) {
        return Math.abs(emp1.getSkillLevel() - emp2.getSkillLevel());  // Calculate the absolute difference
    }

    public void printTree(Employee employee, String indent) {//do we need it?
        if (employee == null) return;
        System.out.println(indent + employee);

        // Print the first subordinate (left child)
        printTree(employee.left, indent + "  ");
        // Print the next sibling (right child)
        printTree(employee.right, indent);
    }

    public Employee getRoot() {
        return root;
    }
}
