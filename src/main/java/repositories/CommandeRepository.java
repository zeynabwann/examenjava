package repositories;

import com.example.entities.Article;
import com.example.entities.Client;
import com.example.entities.Commande;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class CommandeRepository {
    private final SessionFactory sessionFactory;

    public CommandeRepository() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Commande creerCommande(Client client) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Commande commande = new Commande();
            commande.setClient(client);
            commande.setArticle(client);


            transaction.commit();
            return commande;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean ajoutArticleCommande(String libelle, int prix, int quantite,int montant) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Commande commande = session.get(Commande.class, commande);
            Article article = session.get(Article.class, article);

            if (commande == null || article == null) {
                return false;
            }

            if (article.getQuantite() < quantite) {
                return false; 
            }

            Commande commande= new Commande();
            commande.setArticle(article);
            commande.setPrix(article.getPrix());

            commande.getCommande().add(Commande);
            article.setQuantiteEnStock(article.getQuantiteEnStock() - quantite);

            session.save(commande);
            session.update(article);


            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public boolean SupprimerArticle(Long commandeId, Long articleId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Commande commande = session.get(Commande.class, commandeId);
            Article article = session.get(Article.class, article);

            if (commande == null || article == null) {
                return false;
            }

            Commande Commande = commande.getCommande().stream()
                    .filter(l -> l.getArticle().equals(article))
                    .findFirst()
                    .orElse(null);

            if (commande == null) {
                return false; 
            }

            commande.getCommande().remove(commande);
            article.setQuantiteEnStock(article.getQuantite() + commande.getQuantite());

            session.delete(commande);
            session.update(article);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    public List<Commande> rechercheclient(Client client) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            List<Commande> commandes = session.createQuery("SeLECT* FROM client", Commande.class)
                    .setParameter("client", client)
                    .getResultList();
            return commandes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) session.close();
        }
    }
}
