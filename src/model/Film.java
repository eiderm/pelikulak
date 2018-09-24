/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author aitor
 */

public class Film {
        
    private final SimpleStringProperty name;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty direc;
    
    public Film(String fName, String fGenre, String fDir) { 
        this.name = new SimpleStringProperty(fName);
        this.genre = new SimpleStringProperty(fGenre);
        this.direc = new SimpleStringProperty(fDir);
    }
    public String getFirstName() {
        return name.get();
    }
    public void setFirstName(String fName) {
        name.set(fName);
    }
    public String getLastName() {
        return genre.get();
    }
    public void setLastName(String fGenre) {
        genre.set(fGenre);
    }
    public String getEmail() {
        return direc.get();
    }
    public void setEmail(String fDir) {
        direc.set(fDir);
    }
}