package br.com.builders.clients.service;

import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void testCreateClient(){
        Faker f = new Faker(new Locale("pt-BR"));

        RequestClientDto client = RequestClientDto.builder()
                .cpf("44444444444")
                .email(f.internet().emailAddress())
                .name(f.name().fullName())
                .address(f.address().fullAddress())
                .phoneNumber(f.phoneNumber().cellPhone())
                .birthDate(f.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .build();

        ClientDto clientSaved = clientService.save(client);

        Assertions.assertNotNull(clientSaved);
        Assertions.assertNotNull(clientSaved.getId());
        Assertions.assertEquals(client.getName(), clientSaved.getName());
        Assertions.assertEquals(client.getEmail(), clientSaved.getEmail());
        Assertions.assertEquals(client.getCpf(), clientSaved.getCpf());
        Assertions.assertEquals(client.getEmail(), clientSaved.getEmail());
        Assertions.assertEquals(client.getAddress(), clientSaved.getAddress());
        Assertions.assertEquals(client.getPhoneNumber(), clientSaved.getPhoneNumber());

        //clientService.delete(clientSaved.getId());
    }

    @Test
    public void testFindClientById() {
        Faker f = new Faker(new Locale("pt-BR"));

        RequestClientDto client = RequestClientDto.builder()
                .cpf("11111111111")
                .email(f.internet().emailAddress())
                .name(f.name().fullName())
                .address(f.address().fullAddress())
                .phoneNumber(f.phoneNumber().cellPhone())
                .build();

        ClientDto clientSaved = clientService.save(client);

        Optional<ClientDto> clientReturned = clientService.findById(clientSaved.getId());

        Assertions.assertEquals(clientReturned.isPresent(), true);
        Assertions.assertEquals(clientReturned.get().getId(), clientSaved.getId());

        clientService.delete(clientSaved.getId());
    }
}
