package pw.proj.letsmeet.generic;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class ModelBaseRepositoryImpl<T extends ModelBase> extends SimpleJpaRepository<T, Long> implements ModelBaseRepository<T, Long> {

    private EntityManager em;

    public ModelBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        em = entityManager;
    }
}