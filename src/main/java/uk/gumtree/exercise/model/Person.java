package uk.gumtree.exercise.model;

import lombok.Value;
import java.time.LocalDate;

@Value
public class Person {

    String name;
    Gender gender;
    LocalDate dob;

    /**
     * Local Date can't be resolved correctly as system doesn't know yy refers to 1900 or 2000.
     * As a check, if DoB is after today's date, meaning it actually meant birth years in 1900s
     * @return new Person object with corrected birth year
     */
    public Person resolveActualBirthYear(){
        if (dob.isAfter(LocalDate.now())){
            return new Person(name, gender, dob.minusYears(100));
        }
        return this;
    }
}
