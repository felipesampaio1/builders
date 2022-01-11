package br.com.builders.clients.rest.v1;

import br.com.builders.clients.service.ClientService;
import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class ClientResource {

    private ClientService clientService;

    @GetMapping("v1/clients")
    public ResponseEntity<List<ClientDto>> getAllClients(Pageable pageable){
        log.debug("Request for all clients.");
        return new ResponseEntity<>(clientService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @PostMapping("v1/client")
    public ResponseEntity<ClientDto> save(@RequestBody @Valid RequestClientDto requestClientDto){
        log.debug("Request to create a new client.");

        ClientDto clientDto = clientService.save(requestClientDto);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping("v1/client")
    public ResponseEntity<ClientDto> update(@RequestBody @Valid RequestClientDto requestClientDto){
        log.debug("Request to update client.");

        ClientDto clientDto = clientService.save(requestClientDto);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @DeleteMapping("v1/client/{id}")
    public void delete(@PathVariable @NotNull Long id){
        log.debug("Request to delete a client with id: " + id);
        clientService.delete(id);
    }
}
