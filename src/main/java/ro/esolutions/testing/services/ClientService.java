package ro.esolutions.testing.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.esolutions.testing.entities.Client;
import ro.esolutions.testing.models.ClientModel;
import ro.esolutions.testing.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    @NonNull
    ClientRepository clientRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void saveOrUpdateClient(final ClientModel clientModel) {
        Client clientToBeSaved = clientRepository.findById(clientModel.getId())
                .map(clientEntity -> {
                    clientEntity.setName(clientModel.getName());
                    clientEntity.setType(clientModel.getType());
                    return clientEntity;
                })
                .orElseGet(() -> Client.builder()
                        .id(clientModel.getId())
                        .name(clientModel.getName())
                        .type(clientModel.getType())
                        .isActive(true)
                        .build()
                );
        clientRepository.save(clientToBeSaved);
    }

    public void saveOrUpdateClient2(final ClientModel clientModel){
        Optional<Client> clientToBeSavedOptional = clientRepository.findById(clientModel.getId());

        if(clientToBeSavedOptional.isPresent()){
            Client existingClient = clientToBeSavedOptional.get();
            existingClient.setName(clientModel.getName());
            existingClient.setType(clientModel.getType());

            clientRepository.save(existingClient);

        }else{
            Client newClient = Client.builder()
                    .id(clientModel.getId())
                    .name(clientModel.getName())
                    .type(clientModel.getType())
                    .isActive(true)
                    .build();

            clientRepository.save(newClient);
        }

    }

    public List<ClientModel> getNewClients(){

        List<Client> newClients = clientRepository.findAll();
        List<ClientModel> clientsToReturn = new ArrayList<>();



        for(Client c : newClients){
            if(c.getType() == Client.Type.NEW){
                ClientModel model = new ClientModel(c.getId(),c.getName(),c.getType());
                clientsToReturn.add(model);
            }
        }

        return clientsToReturn;
    }

    //getNewClients
    // 0 param
    //find all
    //filtrez dupa type new
    //test in care verificam ce se intampla daca findall intoarce o lista goala/5 clienti si doar 2 sunt noi/ 2 clienti, doar 1 new/ 2 clienti amandoi new
    //1 client si nu se potriveste
    //nu intoarce un client, intoarce client model (lista client model)
}
