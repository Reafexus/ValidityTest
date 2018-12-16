package com.validity.businessLogic;

import java.util.ArrayList;
import java.util.List;

public class DuplicateDisplay {

    private List<String> duplicates;
    private List<String> singles;

    public void setDuplicates(List<String> duplicates){
        this.duplicates = duplicates;
    }

    public void setSingles(List<String> singles){
        this.singles = singles;
    }

    public List<String> getDuplicates(){
        return duplicates;
    }

    public List<String> getSingles(){
        return singles;
    }
}
