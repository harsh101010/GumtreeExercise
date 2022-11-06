package uk.gumtree.exercise.util;

import uk.gumtree.exercise.exception.IllegalAddressBookException;
import uk.gumtree.exercise.model.Gender;
import uk.gumtree.exercise.model.Person;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;

public class AddressBookUtil {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    public static final String COMMA_SEPARATOR = ",";

    public static List<Person> loadAddressBook(String filename) throws IllegalAddressBookException {

        try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(filename).toURI()))) {
            return lines
                    .map(line -> line.split(COMMA_SEPARATOR, 3))
                    .map(strArray -> new Person(strArray[0].strip(),
                                            Gender.getGenderFromString(strArray[1].strip()),
                                            LocalDate.parse(strArray[2].strip(), dateTimeFormatter)))
                    .map(Person::resolveActualBirthYear)
                    .toList();
        } catch (IOException | URISyntaxException | NullPointerException | DateTimeParseException| IllegalAddressBookException e) {
            throw new IllegalAddressBookException(e);
        }
    }
}
