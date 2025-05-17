package com.example.demo.serialization_deserialization_ex;


import java.io.*;

class Address {

    private String city;
    private String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

class Cust implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private transient Address address;

    public Cust(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(address.getCity());
        oos.writeObject(address.getState());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        String city = (String) ois.readObject();
        String state = (String) ois.readObject();
        this.address = new Address(city, state);
    }

    @Override
    public String toString() {
        return "Cust{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

public class SerializableCustEx {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cust.ser"))) {
            Address address = new Address("asansol", "wb");
            oos.writeObject(new Cust("amit", address));
            System.out.println("Object serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cust.ser"))) {
            Cust c = (Cust) ois.readObject();
            System.out.println("Object de-serialized.");
            System.out.println(c);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
