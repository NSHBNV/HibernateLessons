package ru.nshbnv;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.nshbnv.model.*;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            session.beginTransaction();

            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(movieToRemove);
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
        }


    }
}
