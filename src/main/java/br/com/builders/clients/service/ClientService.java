package br.com.builders.clients.service;

import br.com.builders.clients.domain.Client;
import br.com.builders.clients.repository.ClientRepository;
import br.com.builders.clients.service.dto.ClientDto;
import br.com.builders.clients.service.dto.RequestClientDto;
import br.com.builders.clients.service.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto save(RequestClientDto requestClientDto) {
        Client client = clientRepository.save(clientMapper.toEntity(requestClientDto));
        ;

        return clientMapper.toDto(client);
    }

    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toDto);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The client with id " + id + " does not exist."));
        clientRepository.delete(client);
    }
}
