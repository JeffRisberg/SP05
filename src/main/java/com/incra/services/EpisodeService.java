package com.incra.services;

import com.incra.models.Episode;
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
 * The <i>EpisodeService</i> handles the JPA-based updating of Episode entities.
 *
 * @author Jeffrey Risberg
 * @since February 2014
 */
@Transactional
@Repository
public class EpisodeService {

    @PersistenceContext
    private EntityManager em;

    public List<Episode> findEntityList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Episode> criteria = cb.createQuery(Episode.class);
        criteria.from(Episode.class);

        return em.createQuery(criteria).getResultList();
    }

    public Episode findEntityById(int id) {
        return em.find(Episode.class, id);
    }

    public Episode findEntityByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Episode> criteria = builder.createQuery(Episode.class);
        Root<Episode> root = criteria.from(Episode.class);

        Path<String> rootTitle = root.get("title");
        criteria.where(builder.equal(rootTitle, title));

        try {
            return em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(Episode box) {
        if (box.getId() == null || box.getId() == 0) {
            em.persist(box);
        } else {
            em.merge(box);
        }
    }

    public void delete(Episode box) {
        this.delete(box.getId());
    }

    public void delete(int boxId) {
        Episode existingBox = this.findEntityById(boxId);
        if (null != existingBox) {
            em.remove(existingBox);
        }
    }

    public List<Episode> findEntityListBySite(Game site) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Episode> criteria = builder.createQuery(Episode.class);
        Root<Episode> root = criteria.from(Episode.class);

        Path<Game> rootSite = root.get("site");
        criteria.where(builder.equal(rootSite, site));

        return em.createQuery(criteria).getResultList();
    }
}
