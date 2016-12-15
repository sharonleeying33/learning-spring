
package org.packt.Spring.chapter5.JDBC.model;

/**
 *
 *
 */
public class Employee {
    
    private int id;
    
    private String name;

    private int age;
    
    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int empId) {
        this.id = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
        
}
