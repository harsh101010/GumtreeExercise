# gumtree exercise

## Assumptions regarding implementation

1. Since DoB is in dd/MM/yy format, by default date of year is assumed it to be in 21st century (i.e 2020 ). 
If DoB is after today's date,  then system assumes the year of birth in last century(i.e.1983) 
2. If there are more than one oldest person, then system picks up the first oldest person and returns it
3. When calculating age difference between Bill and Paul, if for there are multiple people with these names, system will pick first two persons with these names and calculate the age difference
4. If addressbook data format for date or Gender is wrong then IllegalAddressBook Exception will be thrown