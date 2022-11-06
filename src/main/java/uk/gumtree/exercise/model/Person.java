package uk.gumtree.exercise.model;

import lombok.Value;
import java.time.LocalDate;

@Value
public class Person {

    String name;
    Gender gender;
    LocalDate dob;

}
