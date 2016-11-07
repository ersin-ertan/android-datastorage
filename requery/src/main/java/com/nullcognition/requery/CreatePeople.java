package com.nullcognition.requery;

import com.nullcognition.requery.model.AddressEntity;
import com.nullcognition.requery.model.Person;
import com.nullcognition.requery.model.PersonEntity;
import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Callable;
import rx.Observable;

/**
 * Created by mms on 11/6/16.
 */

class CreatePeople implements Callable<Observable<Iterable<Person>>> {

  private final SingleEntityStore<Persistable> data;

  CreatePeople(SingleEntityStore<Persistable> data) {
    this.data = data;
  }

  @Override public Observable<Iterable<Person>> call() {

    String[] firstNames = new String[] {
        "Alice", "Bob", "Carol", "Chloe", "Dan", "Emily", "Emma", "Eric", "Eva", "Frank", "Gary",
        "Helen", "Jack", "James", "Jane", "Kevin", "Laura", "Leon", "Lilly", "Mary", "Maria", "Mia",
        "Nick", "Oliver", "Olivia", "Patrick", "Robert", "Stan", "Vivian", "Wesley", "Zoe"
    };

    String[] lastNames = new String[] {
        "Hall", "Hill", "Smith", "Lee", "Jones", "Taylor", "Williams", "Jackson", "Stone", "Brown",
        "Thomas", "Clark", "Lewis", "Miller", "Walker", "Fox", "Robinson", "Wilson", "Cook",
        "Carter", "Cooper", "Martin"
    };

    Random random = new Random();

    final Set<Person> people = new TreeSet<>(new Comparator<Person>() {
      @Override public int compare(Person lhs, Person rhs) {
        return lhs.getName().compareTo(rhs.getName());
      }
    });
    // creating many people (but only with unique names)
    for (int i = 0; i < 3000; i++) {
      PersonEntity person = new PersonEntity();
      String first = firstNames[random.nextInt(firstNames.length)];
      String last = lastNames[random.nextInt(lastNames.length)];
      person.setName(first + " " + last);
      person.setUUID(UUID.randomUUID());
      person.setEmail(Character.toLowerCase(first.charAt(0)) +
          last.toLowerCase() + "@gmail.com");
      AddressEntity address = new AddressEntity();
      address.setLine1("123 Market St");
      address.setZip("94105");
      address.setCity("San Francisco");
      address.setState("CA");
      address.setCountry("US");
      person.setAddress(address);
      people.add(person);
    }
    return data.insert(people).toObservable();
  }
}