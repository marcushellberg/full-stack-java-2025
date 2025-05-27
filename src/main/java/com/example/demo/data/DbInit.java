package com.example.demo.data;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DbInit implements ApplicationRunner {

    private final ContactRepository contactRepository;

    public DbInit(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // create 20 random first and last names, then use them to create 100 Contacts

        String[] firstNames = {"Emma", "Noah", "Olivia", "Liam", "Ava", "William", "Sophia", "Mason", "Isabella", "James", "Charlotte", "Benjamin", "Amelia", "Elijah", "Mia", "Lucas", "Harper", "Mason", "Evelyn", "Oliver"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson"};

        for (int i = 0; i < 100; i++) {
            String firstName = firstNames[(int) (Math.random() * firstNames.length)];
            String lastName = lastNames[(int) (Math.random() * lastNames.length)];
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
            String phone = "555-555-5555";
            LocalDate birthday = LocalDate.now().minusYears((int) (Math.random() * 50 + 18));
            Contact contact = new Contact(firstName + " " + lastName, email, phone, birthday);
            contactRepository.save(contact);
        }
    }
}
