package uk.gumtree.exercise;


import uk.gumtree.exercise.exception.IllegalAddressBookException;
import uk.gumtree.exercise.model.Gender;
import uk.gumtree.exercise.model.Person;
import uk.gumtree.exercise.util.AddressBookUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *  This class handles Address Book operations
 */
public class AddressBookService {

    private final List<Person> people;

    public AddressBookService(String fileName) throws IllegalAddressBookException {
        Objects.requireNonNull(fileName, "fileName  must be present");
        this.people = AddressBookUtil.loadAddressBook(fileName);
    }

    public long getNoOfMales(){
        return people.stream().filter(p->p.getGender()== Gender.MALE).count();

    }

    /**
     * If there are more than one person with same age and are oldest, the system will pick the first oldest person.
     * @return oldest person
     */
    public Optional<Person> getOldestPerson(){
        return people.stream().min(Comparator.comparing(Person::getDob));
    }

}
