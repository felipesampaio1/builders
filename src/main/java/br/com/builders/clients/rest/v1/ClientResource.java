package br.com.builders.clients.rest.v1;

import br.com.builders.clients.exception.ClientException;
import br.com.builders.clients.service.ClientService;
import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import br.com.builders.clients.service.dto.UpdateClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Api(value = "REST API para Gereciamento do cadastro de clientes.")
@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class ClientResource {

    private ClientService clientService;

    @ApiOperation(value = "Retorna todos os clientes cadastrados da API.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clientes localizados e retornados.")
    })
    @GetMapping("v1/clients")
    public ResponseEntity<Page<ClientDto>> getAllClients(Pageable pageable){
        log.debug("Request for all clients.");

        return ResponseEntity.ok(clientService.findAll(pageable));
    }

    @ApiOperation(value = "Retorna o cliente com o respectivo id informado como parâmetro da url.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente localizado e retornado."),
            @ApiResponse(code = 204, message = "Cliente não foi localizado.")
    })
    @GetMapping("v1/client/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") Long id){
        log.debug("Request for client with id: {}", id);

        Optional<ClientDto> clientDto = clientService.findById(id);

        if (clientDto.isPresent())
            return new ResponseEntity<>(clientDto.get(), HttpStatus.OK);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Cria um novo cliente com os dados informados no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado corretamente."),
            @ApiResponse(code = 400, message = "Cliente não foi criado, verifique os parâmetros informados.")
    })
    @PostMapping("v1/client")
    public ResponseEntity<ClientDto> save(@RequestBody @Valid RequestClientDto requestClientDto){
        log.debug("Request to create a new client.");

        ClientDto clientDto = clientService.save(requestClientDto);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza todas as informações do cliente com o id informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado corretamente."),
            @ApiResponse(code = 400, message = "Cliente não foi atualizado, verifique os parâmetros informados.")
    })
    @PutMapping("v1/client/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable("id") Long id, @RequestBody @Valid RequestClientDto requestClientDto){
        log.debug("Request to update client.");

        ClientDto oldClientDto = clientService.findById(id).orElseThrow(
                () -> new ClientException("The Client with this id was not found."));;


        ClientDto NewClientDto = clientService.update(oldClientDto, requestClientDto);
        return new ResponseEntity<>(NewClientDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza as informações do cliente com o id informado de forma parcial.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado corretamente."),
            @ApiResponse(code = 400, message = "Cliente não foi atualizado, verifique os parâmetros informados.")
    })
    @PatchMapping("/v1/client/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateClientDto updateClientDto){
        log.debug("Request to partial update client.");

        ClientDto clientDto = clientService.findById(id).orElseThrow(
                () -> new ClientException("The Client with this id was not found."));;

        ClientDto newClientDto = clientService.update(clientDto, updateClientDto);
        return new ResponseEntity<>(newClientDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta o cliente do id informado na url.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente excluído com sucesso.")
    })
    @DeleteMapping("v1/client/{id}")
    public void delete(@PathVariable @NotNull Long id){
        log.debug("Request to delete a client with id: " + id);

        clientService.delete(id);
    }


}
