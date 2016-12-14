package com.bloodbank.frontend.controllers;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table_Data_Model extends CommonMethods {

    private final StringProperty name;
    private final StringProperty blood_group;
    private final StringProperty area;
    private final StringProperty id;
    private final StringProperty contact;

    /**
     * Default constructor.
     */
    public Table_Data_Model() {
        this(null, null, null, null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Table_Data_Model(String name, String blood_group, String area, String id,String contact) {
        this.name = new SimpleStringProperty(name);
        this.blood_group = new SimpleStringProperty(blood_group);
        this.area = new SimpleStringProperty(area);
        this.id = new SimpleStringProperty(id);
        this.contact = new SimpleStringProperty(contact);

        // Some initial dummy data, just for convenient testing.
//        this.street = new SimpleStringProperty("some street");
//        this.postalCode = new SimpleIntegerProperty(1234);
//        this.city = new SimpleStringProperty("some city");
//        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getBloodGroup() {
        return blood_group.get();
    }

    public void setBloodGroup(String blood_group) {
        this.blood_group.set(blood_group);
    }
    
    public String getArea() {
        return area.get();
    }

    public void setArea(String area) {
        this.area.set(area);
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }
    

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty bloodGroupProperty() {
        return blood_group;
    }
    
    public StringProperty areaProperty() {
        return area;
    }
    
    public StringProperty idProperty() {
        return id;
    }
    
    public StringProperty contactProperty() {
        return contact;
    }
}