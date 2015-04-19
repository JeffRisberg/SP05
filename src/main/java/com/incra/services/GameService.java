package com.incra.services;

import com.incra.models.Game;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The <i>GameService</i> handles the JPA-based updating of Game entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class GameService {

    @PersistenceContext
    private EntityManager em;

    public List<Game> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Game> criteria = cb.createQuery(Game.class);
        criteria.from(Game.class);

        return em.createQuery(criteria).getResultList();
    }

    public Game findEntityById(int id) {
        return em.find(Game.class, id);
    }

    public Game findEntityByName(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Game> criteria = builder.createQuery(Game.class);
        Root<Game> root = criteria.from(Game.class);

        Path<String> rootName = root.get("name");
        criteria.where(builder.equal(rootName, name));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Game site) {
        if (site.getId() == null || site.getId() == 0) {
            em.persist(site);
        } else {
            em.merge(site);
        }
    }

    public void delete(Game site) {
        this.delete(site.getId());
    }

    public void delete(int siteId) {
        Game existingSite = this.findEntityById(siteId);
        if (null != existingSite) {
            em.remove(existingSite);
        }
    }
}
