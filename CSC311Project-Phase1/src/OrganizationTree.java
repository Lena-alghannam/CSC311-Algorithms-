class OrganizationTree {
    private Employee root;  // The root of the tree (the top-level manager)

    // Constructor to initialize the organization tree
    public OrganizationTree() {
        this.root = null;
    }

    // Method to add an employee to the tree
    public void addEmployee(Employee curr){

        // If the employee is the root (parentId is 0), set it as the root
        if (curr.getParentID() == 0) {
            if (root == null) {
                root = curr;
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


    // Method to add a subordinate to a parent employee //added during addEmployee
    public void addSubordinate(Employee parent, Employee curr) {
        // If the parent has no left child (first subordinate), set it
        if (parent.left == null) {
            parent.left = curr;
        } else {
            parent.right = curr;

            /*
            // If the parent has subordinates, find the rightmost sibling and add the new subordinate there
            Employee current = parent.left;
            while (current.right != null) {
                current = current.right;
            }
            current.right = subordinate;*/
        }
        curr.parent = parent;
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


    // Method to retrieve the root of the organization
    public Employee getRoot() {
        return root;
    }
}
