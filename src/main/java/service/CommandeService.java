package service;


import com.example.entities.Article;
import com.example.entities.Client;
import com.example.entities.Commande;
import repositories.CommandeRepository;

import java.util.List;
import java.util.Optional;

public class CommandeService {
    private final CommandeRepository commandeRepository;

    public CommandeService() {
        this.commandeRepository = new CommandeRepository();
    }

    public Commande creerCommande(Client client) {
        return commandeRepository.creerCommande(client);
    }

    public boolean ajouterArticleACommande(int commandeId, int articleId, int quantite) {
        return commandeRepository.ajoutArticleCommande(commandeId, articleId, quantite);
    }

    public boolean supprimerArticle(int commandeId, int articleId) {
        return commandeRepository.SupprimerArticle(commandeId, articleId);
    }

    public List<Commande> rechercherCommandesParClient(Client client) {
        return commandeRepository.rechercheclient(client);
    }
}
