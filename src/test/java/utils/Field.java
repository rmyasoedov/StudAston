package utils;

import org.openqa.selenium.By;

import java.util.List;

public class Field{
    public final String id;
    public final List<String> errors;
    public final String placeholder;

    public Field(String fieldId, String placeholder, List<String> errors){
        this.id = fieldId;
        this.placeholder = placeholder;
        this.errors = errors;
    }

    public By getField(){ return By.id(id); }
}