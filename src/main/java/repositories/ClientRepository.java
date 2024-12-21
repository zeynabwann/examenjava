package repositories;
import com.example.entities.Article;

import com.example.entities.Client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class ClientRepository {
    private final SessionFactory sessionFactory;

    public ClientRepository() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Optional<Client> rechercheclientparnumero(String numero) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Client client = session.createQuery("FROM Client WHERE numero = :numero", Client.class)
                                    .setParameter("numero", numero)
                                    .uniqueResult();

            transaction.commit();
            return Optional.ofNullable(client);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null) session.close();
        }
    }

    
      
}
