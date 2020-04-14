package lt.vu.persistence;

import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class TeamsDAO {

    @Inject
    private EntityManager em;

    public List<Team> loadAll() {
        return em.createNamedQuery("Team.findAll", Team.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Team team){
        this.em.persist(team);
    }

    public Team findOne(Integer id) {
        return em.find(Team.class, id);
    }

    public void deleteOne(Integer id){
        Team tmp_team = findOne(id);
        em.remove(tmp_team);
        System.out.println("Object was deleted from database: ID = " + tmp_team.getId().toString());
    }

    //public List<Team> findLike(String sample){ return em.(Team.class, sample);}
}
