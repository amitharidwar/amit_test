package com.example.demo.java_program;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Cust {
    private Integer id;
    private String name;

    public Cust(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cust cust)) return false;
        return Objects.equals(id, cust.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cust{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class HashMapTest {
    public static void main(String[] args) {

        Cust c = new Cust(11, "amit");

        Map<Cust, String> map = new HashMap<>();
        map.put(c, "Hello world");

//        c.setId(22);
//        System.out.println(map.get(c)); // null
//        System.out.println(map); // {Cust{id=22, name='amit'}=Hello world}


//        c.setName("sumit");
//        System.out.println(map.get(c)); // Hello world
//        System.out.println(map); // {Cust{id=22, name='amit'}=Hello world}


// hashCode() and equals() methods not overridden

        c.setId(22);
        System.out.println(map.get(c)); // Hello world
        System.out.println(map); // {Cust{id=22, name='amit'}=Hello world}


        c.setName("sumit");
        System.out.println(map.get(c)); // Hello world
        System.out.println(map); // {Cust{id=22, name='sumit'}=Hello world}



        Cust c1 = new Cust(100, "Raju");
        Cust c2 = new Cust(100, "Raju");


        Map<Cust, String> map1 = new HashMap<>();
        map1.put(c1, "Welcome");

       // hashCode() method not overridden
/*        System.out.println(c1.hashCode() + "----------------" + c2.hashCode()); // Both hashCode value would be different as by default mechanism object memory address conversion to Integer hashcode value
        System.out.println(map1.get(c1)); // Welcome
        System.out.println(map1.get(c2)); // null
        System.out.println(map1.get(new Cust(100, "Raju")));// null*/

       //hashCode() method  overridden
        System.out.println(c1.hashCode() + "----------------" + c2.hashCode()); // Both hashCode value would be same
        System.out.println(map1.get(c1)); // Welcome
        System.out.println(map1.get(c2)); // Welcome
        System.out.println(map1.get(new Cust(100, "Raju")));// Welcome




    }
}
