package service;


import repositories.ClientRepository;
import com.example.entities.Client;
import java.util.Optional;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Méthode pour trouver un client par son numéro
    public Optional<Client> findClientByNumero(String numero) {
        return clientRepository.rechercheclientparnumero(numero);
    }

   
}

