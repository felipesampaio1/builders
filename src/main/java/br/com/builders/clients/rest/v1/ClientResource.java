package br.com.builders.clients.rest.v1;

import br.com.builders.clients.exception.ClientException;
import br.com.builders.clients.service.ClientService;
import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import br.com.builders.clients.service.dto.UpdateClientDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class ClientResource {

    private ClientService clientService;

    @GetMapping("v1/clients")
    public ResponseEntity<Page<ClientDto>> getAllClients(Pageable pageable){
        log.debug("Request for all clients.");

        return ResponseEntity.ok(clientService.findAll(pageable));
    }

    @GetMapping("v1/client/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id){
        log.debug("Request for client with id: {}", id);

        Optional<ClientDto> clientDto = clientService.findById(id);

        if (clientDto.isPresent())
            return new ResponseEntity<>(clientDto.get(), HttpStatus.OK);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("v1/client")
    public ResponseEntity<ClientDto> save(@RequestBody @Valid RequestClientDto requestClientDto){
        log.debug("Request to create a new client.");

        ClientDto clientDto = clientService.save(requestClientDto);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping("v1/client/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable("id") Long id, @RequestBody @Valid RequestClientDto requestClientDto){
        log.debug("Request to update client.");

        ClientDto oldClientDto = clientService.findById(id).orElseThrow(
                () -> new ClientException("The Client with this id was not found."));;


        ClientDto NewClientDto = clientService.update(oldClientDto, requestClientDto);
        return new ResponseEntity<>(NewClientDto, HttpStatus.OK);
    }

    @PatchMapping("/v1/client/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateClientDto updateClientDto){
        log.debug("Request to partial update client.");

        ClientDto clientDto = clientService.findById(id).orElseThrow(
                () -> new ClientException("The Client with this id was not found."));;

        ClientDto newClientDto = clientService.update(clientDto, updateClientDto);
        return new ResponseEntity<>(newClientDto, HttpStatus.OK);
    }

    @DeleteMapping("v1/client/{id}")
    public void delete(@PathVariable @NotNull Long id){
        log.debug("Request to delete a client with id: " + id);

        clientService.delete(id);
    }


}
