package com.ouadouch.ws;

import com.ouadouch.ws.entities.Contact;
import com.ouadouch.ws.repository.IContactRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactWS {

    public static void main(String[] args) {
        SpringApplication.run(ContactWS.class, args);
    }
    @Bean
    CommandLineRunner initDatabase(IContactRepo contactRepo) {

        return args -> {
            contactRepo.save(new Contact(
                    0,
                    "Hassan",
                    "ouadouch",
                    "hassanoua@gmail.com",
                    "07756656",
                    "tinghir",
                    "teacher"
            ));
            contactRepo.save(new Contact(
                    0,
                    "mustapha",
                    " ouadouch",
                    "ouadouch3@gmail.com",
                    "04444444",
                    "agadir",
                    "ingenieur"

            ));
        };
    }

}
