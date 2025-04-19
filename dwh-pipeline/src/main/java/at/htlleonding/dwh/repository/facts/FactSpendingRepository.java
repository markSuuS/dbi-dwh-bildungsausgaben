package at.htlleonding.dwh.repository.facts;

import at.htlleonding.dwh.entity.facts.FactSpending;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FactSpendingRepository implements PanacheRepository<FactSpending> {
}
