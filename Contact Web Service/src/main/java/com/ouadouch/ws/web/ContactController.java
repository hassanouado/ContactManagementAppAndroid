//Authors : Abderrahim Ettounani
package com.ouadouch.ws.web;

import com.ouadouch.ws.entities.Contact;
import com.ouadouch.ws.repository.IContactRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ContactController {
    private final IContactRepo contactRepo;

    @GetMapping("/contacts")
    public List<Contact> getAllContact() {
        return contactRepo.findAll();
    }

    @PostMapping("/contact")
    Contact AddnewContact(@RequestBody Contact newContact) {
        return contactRepo.save(newContact);
    }

    @GetMapping("/contacts/{id}")
    Contact findoneContact(@PathVariable Long id) {
        return contactRepo.findById(id).isPresent() ? contactRepo.findById(id).get() : new Contact();
    }

    @DeleteMapping("/deleteContacts/{id}")
    void deleteContact(@PathVariable Long id) {
        contactRepo.deleteById(id);
    }

    @PutMapping("/contacts/{id}")
    Contact updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        return contactRepo.findById(id)
                .map(contact1 -> {
                    contact1.setFirstName(contact.getFirstName());
                    contact1.setPhone(contact.getPhone());
                    contact1.setLastName(contact.getLastName());
                    contact1.setEmail(contact.getEmail());
                    contact1.setVille(contact.getVille());
                    contact1.setJob(contact.getJob());
                    return contactRepo.save(contact1);
                })
                .orElseGet(() -> {
                    contact.setId(id);
                    return contactRepo.save(contact);
                });
    }

}
