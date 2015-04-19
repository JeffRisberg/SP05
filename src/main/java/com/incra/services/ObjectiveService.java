package com.incra.services;

import com.incra.models.Objective;
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
 * The <i>ObjectiveService</i> handles the JPA-based updating of Objective entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class ObjectiveService {

    @PersistenceContext
    private EntityManager em;

    public List<Objective> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Objective> criteria = cb.createQuery(Objective.class);
        criteria.from(Objective.class);

        return em.createQuery(criteria).getResultList();
    }

    public Objective findEntityById(int id) {
        return em.find(Objective.class, id);
    }

    public Objective findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Objective> criteria = builder.createQuery(Objective.class);
        Root<Objective> root = criteria.from(Objective.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Objective box) {
        if (box.getId() == null || box.getId() == 0) {
            em.persist(box);
        } else {
            em.merge(box);
        }
    }

    public void delete(Objective box) {
        this.delete(box.getId());
    }

    public void delete(int boxId) {
        Objective existingBox = this.findEntityById(boxId);
        if (null != existingBox) {
            em.remove(existingBox);
        }
    }

    public List<Objective> findEntityListBySite(Game site) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Objective> criteria = builder.createQuery(Objective.class);
        Root<Objective> root = criteria.from(Objective.class);

        Path<Game> rootSite = root.get("site");
        criteria.where(builder.equal(rootSite, site));

        return em.createQuery(criteria).getResultList();
    }
}
