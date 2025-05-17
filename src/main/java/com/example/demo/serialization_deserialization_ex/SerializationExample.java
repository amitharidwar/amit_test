package com.example.demo.serialization_deserialization_ex;

import java.io.*;

class MyClass implements Serializable {
    int normalVariable;
    transient int transientVariable;
    static int staticVariable;

    // Constructor
    public MyClass(int normalVariable, int transientVariable, int staticVariable) {
        this.normalVariable = normalVariable;
        this.transientVariable = transientVariable;
        MyClass.staticVariable = staticVariable;
    }

    // Method to display values
    public void display() {
        System.out.println("Normal Variable: " + normalVariable);
        System.out.println("Transient Variable: " + transientVariable);
        System.out.println("Static Variable: " + staticVariable);
    }
}

public class SerializationExample {
    public static void main(String[] args) {
      //  MyClass obj = new MyClass(10, 20, 30);

        // Serialize the object
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.ser"))) {
//            out.writeObject(obj);
//            System.out.println("Object serialized.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Reset static variable to see the effect after deserialization
        //MyClass.staticVariable = 100;

        // Deserialize the object
        MyClass deserializedObj = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("object.ser"))) {
            deserializedObj = (MyClass) in.readObject();
            System.out.println("Object deserialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        MyClass.staticVariable = 10000;
        // Display values after deserialization
        System.out.println("Deserialized Object:");
        deserializedObj.display();
    }
}

/*
Object serialized.
Object deserialized.
Deserialized Object:
Normal Variable: 10
Transient Variable: 0
Static Variable: 30*/

//MyClass.staticVariable = 100; once this is present below would be output

/*Object serialized.
Object deserialized.
Deserialized Object:
Normal Variable: 10
Transient Variable: 0
Static Variable: 100*/


