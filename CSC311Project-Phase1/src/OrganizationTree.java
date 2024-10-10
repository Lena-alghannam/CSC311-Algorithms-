class OrganizationTree {
    private Employee root;  // The root of the tree (the top-level manager)

    // Constructor to initialize the organization tree
    public OrganizationTree() {
        this.root = null;
    }

    // Method to add an employee to the tree
    public void addEmployee(int id, String name, int skillLevel, int parentId) {
        Employee newEmployee = new Employee(id, name, skillLevel);

        // If the employee is the root (parentId is 0), set it as the root
        if (parentId == 0) {
            if (root == null) {
                root = newEmployee;
            } else {
                System.out.println("Root already exists!");
            }
        } else {
            // Otherwise, find the parent and add the employee as its child
            Employee parent = findEmployee(root, parentId);
            if (parent != null) {
                addSubordinate(parent, newEmployee);
            } else {
                System.out.println("Parent with ID " + parentId + " not found.");
            }
        }
    }

    // Helper method to find an employee by ID in the tree (recursively)
    private Employee findEmployee(Employee current, int id) {
        if (current == null) {
            return null;
        }
        if (current.id == id) {
            return current;
        }

        // Search in the left child (first subordinate)
        Employee found = findEmployee(current.left, id);
        if (found == null) {
            // If not found, search in the right sibling (next subordinate of the same parent)
            found = findEmployee(current.right, id);
        }
        return found;
    }

    // Method to add a subordinate to a parent employee
    private void addSubordinate(Employee parent, Employee subordinate) {
        // If the parent has no left child (first subordinate), set it
        if (parent.left == null) {
            parent.left = subordinate;
        } else {
            // If the parent has subordinates, find the rightmost sibling and add the new subordinate there
            Employee current = parent.left;
            while (current.right != null) {
                current = current.right;
            }
            current.right = subordinate;
        }
        subordinate.parent = parent;
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
