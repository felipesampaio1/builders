package br.com.builders.clients.service;

import br.com.builders.clients.domain.Client;
import br.com.builders.clients.exception.ClientException;
import br.com.builders.clients.repository.ClientRepository;
import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import br.com.builders.clients.service.dto.UpdateClientDto;
import br.com.builders.clients.service.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto save(RequestClientDto requestClientDto) {
        Optional<Client> clientSaved = clientRepository.findByCpf(requestClientDto.getCpf());
        if (clientSaved.isPresent())
            throw new ClientException("The client with this CPF already saved.");

        Client client = clientRepository.save(clientMapper.toEntity(requestClientDto));

        ClientDto clientDto = clientMapper.toDto(client);

        ClientDto.calculateAge(clientDto);

        return clientDto;
    }

    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toDto).map(ClientDto::calculateAge);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The client with id " + id + " does not exist."));
        clientRepository.delete(client);
    }

    public Optional<ClientDto> findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto).map(ClientDto::calculateAge);
    }


    public ClientDto update(ClientDto clientDto, RequestClientDto requestClientDto){
        clientDto.setCpf(requestClientDto.getCpf());
        clientDto.setAddress(requestClientDto.getAddress());
        clientDto.setEmail(requestClientDto.getEmail());
        clientDto.setName(requestClientDto.getName());
        clientDto.setPhoneNumber(requestClientDto.getPhoneNumber());
        clientDto.setBirthDate(requestClientDto.getBirthDate());

        clientRepository.save(clientMapper.toEntity(clientDto));

        ClientDto.calculateAge(clientDto);

        return  clientDto;

    }

    public ClientDto update(ClientDto clientDto, UpdateClientDto updateClientDto) {

        if (updateClientDto.getName() != null) {
            if (updateClientDto.getName().isBlank())
                throw new ClientException("The Client name needs to be filled.");
            else clientDto.setName(updateClientDto.getName());
        }

        if (updateClientDto.getAddress() != null) {
            if (updateClientDto.getAddress().isBlank())
                throw new ClientException("The Client address needs to be filled.");
            else clientDto.setAddress(updateClientDto.getAddress());
        }

        if (updateClientDto.getEmail() != null)
            clientDto.setEmail(updateClientDto.getEmail());

        if (updateClientDto.getPhoneNumber() != null)
            clientDto.setPhoneNumber(updateClientDto.getPhoneNumber());

        if (updateClientDto.getBirthDate() != null)
            clientDto.setBirthDate(updateClientDto.getBirthDate());

        clientRepository.save(clientMapper.toEntity(clientDto));

        ClientDto.calculateAge(clientDto);

        return clientDto;
    }
}
