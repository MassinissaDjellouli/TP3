package com.Repository;

import com.Models.Documents.Documents;
import com.Models.Documents.Livre;
import com.Models.Documents.Media;
import com.Models.Users.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class BiblioDAO implements BiblioDAOInterface {
    protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TP2");
    protected EntityManager entityManager;
    protected EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public <T> void save(T toSave) {
        throwIfNull(toSave);
        beginTransaction();
        entityManager.persist(toSave);
        finishTransaction();
    }

    @Override
    public <T> void delete(T toDelete) {
        beginTransaction();
        throwIfNull(toDelete);
        entityManager.remove(toDelete);
        finishTransaction();
    }

    @Override
    public <T> void merge(T toMerge){
        beginTransaction();
        entityManager.merge(toMerge);
        finishTransaction();
    }

    @Override
    public Documents findDocumentById(int id) {
        beginTransaction();
        Documents toReturn = entityManager.find(Documents.class,id);
        finishTransaction();
        return toReturn;
    }

    @Override
    public Set<Documents> findAllDocuments() {
        beginTransaction();
        TypedQuery<Documents> query = entityManager.createQuery("select d from Documents d",Documents.class );
        Set<Documents> toReturn = new HashSet<>(query.getResultList());
        finishTransaction();
        return toReturn;
    }

    @Override
    public Set<Livre> findAllLivre(){
        beginTransaction();
        TypedQuery<Livre> query = entityManager.createQuery("select l from Livre l", Livre.class );
        Set<Livre> toReturn = new HashSet<>(query.getResultList());
        finishTransaction();
        return toReturn;
    }

    @Override
    public Client findClientById(int id) {
        beginTransaction();
        Client toReturn = entityManager.find(Client.class,id);
        finishTransaction();
        return toReturn;
    }

    @Override
    public Client findClientByIdWEmprunts(int id) {
        beginTransaction();
        TypedQuery<Client> query =
                entityManager.createQuery("select c From Client c left join fetch c.emprunts ce ",Client.class);
        Client toReturn = query.getSingleResult();
        finishTransaction();
        return toReturn;
    }

    @Override
    public Set<Client> findAllClient() {
        beginTransaction();
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c",Client.class );
        Set<Client> toReturn = new HashSet<>(query.getResultList());
        finishTransaction();
        return toReturn;
    }

    @Override
    public Set<Client> findAllClientWEmprunts() {
        beginTransaction();
        TypedQuery<Client> query = entityManager.createQuery(
                "select c from Client c left join fetch c.emprunts ce where ce.client.clientNumber = c.clientNumber",Client.class );
        Set<Client> toReturn = new HashSet<>(query.getResultList());
        finishTransaction();
        return toReturn;
    }

    @Override
    public Set<Media> findAllMedia() {
        beginTransaction();
        TypedQuery<Media> query = entityManager.createQuery("select m from Media m", Media.class );
        Set<Media> toReturn = new HashSet<>(query.getResultList());
        finishTransaction();
        return toReturn;
    }

    protected void throwIfNull(Object o){
        if(o == null) throw new IllegalArgumentException();
    }

    protected void throwIfNotNull(Object o){
        if(o != null) throw new IllegalArgumentException();
    }

    protected void beginTransaction(){
        throwIfNotNull(entityManager);
        entityManager = getEntityManager();
        entityManager.getTransaction().begin();
    }

    protected void finishTransaction(){
        throwIfNull(entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager = null;
    }

    @Override
    public void handleException(String msg){
        if(entityManager != null){
            entityManager.getTransaction().rollback();
            entityManager.close();
            entityManager = null;
        }
            System.out.println(msg);

    }
}
