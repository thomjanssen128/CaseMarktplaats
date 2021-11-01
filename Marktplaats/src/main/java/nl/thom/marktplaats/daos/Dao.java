package nl.thom.marktplaats.daos;

import nl.thom.marktplaats.domain.*;
import nl.thom.marktplaats.domain.Identifiable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Dao<E extends Identifiable<I>, I> { // E is an entity, I is a type of id

    @Inject
    protected EntityManager em;

    public List<E> findAll() {  // SELECT ...
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList(); // JPQL Java Persistence Query Language
    }

    public E find(I id) {
        return em.find(E(), id); // SELECT .. WHERE id = ..
    }

    public void save(E p) {
        em.getTransaction().begin();
        em.persist(p); // INSERT == persist
        em.getTransaction().commit();
    }

    public E update(E e) {
        em.getTransaction().begin();
        E mergedE = em.merge(e);// UPDATE ...
        em.getTransaction().commit();
        return mergedE;
    }

    public void remove(E e) {
        em.getTransaction().begin();
        em.remove(find(e.getId())); // DELETE ...
        em.getTransaction().commit();
    }

    /**
     * @return a class instance of the first generic type parameter (E) of this Dao,
     * e.g. for PersonDao<Person>, it returns Employee.class.
     */
    @SuppressWarnings("unchecked")
    private Class<E> E() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }

    private String typeSimple() {return E().getSimpleName();}

}
