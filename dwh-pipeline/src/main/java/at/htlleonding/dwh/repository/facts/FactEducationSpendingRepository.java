package at.htlleonding.dwh.repository.facts;

import at.htlleonding.dwh.entity.facts.FactEducationSpending;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FactEducationSpendingRepository implements PanacheRepository<FactEducationSpending> {
}
