package uk.gumtree.exercise;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gumtree.exercise.model.Gender;
import uk.gumtree.exercise.model.Person;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class AddressBookServiceTest {

    AddressBookService addressBookService;

    private static Stream<Arguments> addressBookNoOfMales() {
        return Stream.of(
                Arguments.of("addressbook.txt", 3),
                Arguments.of("addressbookWithPersonWithSameAge.txt", 4),
                Arguments.of("addressbookEmpty.txt", 0)
        );
    }

    private static Stream<Arguments> addressBookOldestPerson() {
        return Stream.of(
                Arguments.of("addressbook.txt", new Person("Wes Jackson", Gender.MALE, LocalDate.of(1974, 8, 14))),
                Arguments.of("addressbookWithPersonWithSameAge.txt", new Person("Sarah Jackson", Gender.FEMALE, LocalDate.of(1974, 8, 14))),
                Arguments.of("addressbookEmpty.txt", null)
        );
    }

    @ParameterizedTest
    @MethodSource("addressBookNoOfMales")
    void shouldGetNoOfMalesFromAValidAddressBook(String addressBookFileName, long expectedNoOfMales) {
        addressBookService = new AddressBookService(addressBookFileName);
        long actualNoOfMales = addressBookService.getNoOfMales();
        assertThat(actualNoOfMales).as("No of males does not match").isEqualTo(expectedNoOfMales);
    }

    @ParameterizedTest
    @MethodSource("addressBookOldestPerson")
    void shouldGetOldestPersonFromAddressBook(String addressBookFileName, Person expectedOldestPerson) {
        addressBookService = new AddressBookService(addressBookFileName);
        Person actualOldestPerson = addressBookService.getOldestPerson().orElse(null);
        AssertionsForClassTypes.assertThat(actualOldestPerson).as("Oldest person does not match").isEqualTo(expectedOldestPerson);
    }

}