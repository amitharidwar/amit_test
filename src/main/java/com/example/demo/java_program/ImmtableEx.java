package com.example.demo.java_program;

import java.util.*;

final class Customer {
    
    private final String name;
    private final List<String> phoneList;
    private final Map<String, String> preferences;
    private final Set<String> emailSet;
    private final Address address;
    private final Date date;

    public Customer(String name, List<String> phoneList, Map<String, String> preferences, Set<String> emailSet, Address address, Date date) {
        this.name = name;
        this.phoneList = List.copyOf(phoneList);
        this.preferences = Map.copyOf(preferences);
        this.emailSet = Set.copyOf(emailSet);
        this.address = new Address(address);
        this.date = new Date(date.getTime()); // you can also use LocalDate present in java.time package
    }

    public String getName() {
        return name;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public Map<String, String> getPreferences() {
        return preferences;
    }

    public Set<String> getEmailSet() {
        return emailSet;
    }

    public Address getAddress() {
        return new Address(address);
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneList=" + phoneList +
                ", preferences=" + preferences +
                ", emailSet=" + emailSet +
                ", address=" + address +
                ", date=" + date +
                '}';
    }
}

class Address {
    private String city;
    private String state;

    public Address(Address address) {
        this.city = address.getCity();
        this.state = address.getState();
    }

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

public class ImmtableEx {
    public static void main(String[] args) {
        List<String> phoneList = new ArrayList<>(Arrays.asList("876534", "9810004"));
        Set<String> emailSet = new HashSet<>(Arrays.asList("amit@gmail.com", "sumit@gmail.com"));
        Map<String, String> preferencesMap = new HashMap<>();
        preferencesMap.put("language", "en");
        preferencesMap.put("currency", "USD");

        Address address = new Address("asansol", "wb");

        Date date = new Date();

        Customer customer = new Customer("amit", phoneList, preferencesMap, emailSet, address, date);
        System.out.println(customer);

        // customer.getEmailSet().add("raju@yahoo.co.in");  // will throw java.lang.UnsupportedOperationException
        // customer.getPhoneList().add("976539807");  // will throw java.lang.UnsupportedOperationException

        // customer.getPreferences().put("newspaper", "true"); // will throw java.lang.UnsupportedOperationException

        phoneList.add("1000000");
        preferencesMap.put("newspaper", "true"); // will work

        customer.getAddress().setCity("Burnpur");
        customer.getAddress().setState("jharkhand");

        address.setState("Delhi");
        address.setCity("Dwarka");

        date.setTime(0);

        System.out.println(customer);
    }
}

//Customer{name='amit', phoneList=[876534, 9810004], preferences={language=en, currency=USD}, emailSet=[sumit@gmail.com, amit@gmail.com], address=Address{city='asansol', state='wb'}, date=Tue Apr 29 10:36:37 IST 2025}
//Customer{name='amit', phoneList=[876534, 9810004], preferences={language=en, currency=USD}, emailSet=[sumit@gmail.com, amit@gmail.com], address=Address{city='asansol', state='wb'}, date=Tue Apr 29 10:36:37 IST 2025}