package com.prolificidea.templates.tsw.persistence.generic;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T find(Object id) {
        return this.entityManager.find(type, id);
    }

    public List<T> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x");
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    public List<T> findAll(int pageSize, int pageNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x");
        Query query = this.entityManager.createQuery(sb.toString());
        query.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<T> search(String property, String criteria) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x where x.");
        sb.append(property);
        sb.append(" like %");
        sb.append(criteria);
        sb.append("%");
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    public List<T> search(String property, String criteria, int pageSize, int pageNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x where x.");
        sb.append(property);
        sb.append(" like %");
        sb.append(criteria);
        sb.append("%");
        Query query = this.entityManager.createQuery(sb.toString());
        query.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    public long count() {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(x) from ");
        sb.append(type.getSimpleName());
        sb.append(" x");
        Query query = this.entityManager.createQuery(sb.toString());
        return (Long)query.getSingleResult();
    }

    public void delete(Object id) {
        this.entityManager.remove(this.entityManager.getReference(type, id));
    }

    public T create(T t) {
        this.entityManager.persist(t);
        this.entityManager.flush();
        return t;
    }

    public T update(T t) {
        this.entityManager.merge(t);
        this.entityManager.flush();
        return t;
    }
}
