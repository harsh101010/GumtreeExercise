package uk.gumtree.exercise;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gumtree.exercise.exception.IllegalAddressBookException;
import uk.gumtree.exercise.model.Gender;
import uk.gumtree.exercise.model.Person;
import uk.gumtree.exercise.util.AddressBookUtil;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


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

    private static Stream<Arguments> addressBookAgeDiffInDaysBetweenBillPaul() {
        return Stream.of(
                Arguments.of("addressbook.txt", 2862),
                Arguments.of("addressbookWithPersonWithSameAge.txt", 2862),
                Arguments.of("addressbookEmpty.txt", 0)
        );
    }

    private static Stream<Arguments> addressBookExceptionGenerator() {
        return Stream.of(
                Arguments.of( "addressbookInvalidDate.txt", "java.time.format.DateTimeParseException: Text '15/01/1985' could not be parsed, unparsed text found at index 8"),
                Arguments.of( "addressbookInvalidGender.txt", "uk.gumtree.exercise.exception.IllegalAddressBookException: invalid gender specified ")

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

    @ParameterizedTest
    @MethodSource("addressBookAgeDiffInDaysBetweenBillPaul")
    void shouldGetAgeDifferenceBetweenTwoPeople(String addressBookFileName, long expectedAgeDifferenceInDays) {
        addressBookService = new AddressBookService(addressBookFileName);
        long actualAgeDifferenceInDays = addressBookService.ageDifferenceBetweenTwoPeople("Bill", "Paul");
        AssertionsForClassTypes.assertThat(actualAgeDifferenceInDays).as("Age difference between Bill and Paul does not match").isEqualTo(expectedAgeDifferenceInDays);
    }

    @ParameterizedTest
    @MethodSource("addressBookExceptionGenerator")
    void shouldThrowExceptionForInvalidAddressBook(String addressBookFileName, String expectedErrorMessage){
        Throwable thrown = catchThrowable(()-> AddressBookUtil.loadAddressBook(addressBookFileName));

        assertThat(thrown)
                .isInstanceOf(IllegalAddressBookException.class)
                .hasMessageContaining(expectedErrorMessage);
    }
}

