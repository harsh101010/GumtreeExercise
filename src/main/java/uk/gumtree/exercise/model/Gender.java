package uk.gumtree.exercise.model;

import uk.gumtree.exercise.exception.IllegalAddressBookException;
import java.util.Arrays;

public enum Gender {

    MALE("Male"),
    FEMALE ("Female");

    final String genderAsString;

    Gender(String genderAsString){
        this.genderAsString = genderAsString;
    }

    public static Gender getGenderFromString(String genderAsString){
        return Arrays.stream(Gender.values()).filter(g -> g.genderAsString.equalsIgnoreCase(genderAsString))
                .findFirst()
                .orElseThrow(() -> new IllegalAddressBookException(String.format("invalid gender specified %s", genderAsString)));
    }
}
