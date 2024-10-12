class Employee {
    int ParentID;             // Employee's unique ID
    int id;                   // Employee's unique ID
    String name;              // Employee's name
    int skillLevel;           // Employee's skill level
    Employee parent;          // Reference to the parent (supervisor)
    Employee left;            // Reference to the left child (first subordinate)
    Employee right;           // Reference to the right sibling (next subordinate of the same parent)

    // Constructor to initialize the employee node
    public Employee(int ParentID, String name, int id, int skillLevel) {
        this.ParentID = ParentID;
        this.id = id;
        this.name = name;
        this.skillLevel = skillLevel;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public Employee() {///do we need this?
        this.ParentID = -1;
        this.id = -1;
        this.name = "";
        this.skillLevel = -1;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int parentID) {
        ParentID = parentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Employee getParent() {
        return parent;
    }

    public void setParent(Employee parent) {
        this.parent = parent;
    }

    public Employee getLeft() {
        return left;
    }

    public void setLeft(Employee left) {
        this.left = left;
    }

    public Employee getRight() {
        return right;
    }

    public void setRight(Employee right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Employee [ID=" + id + ", Name=" + name + ", SkillLevel=" + skillLevel + "]";
    }
}
