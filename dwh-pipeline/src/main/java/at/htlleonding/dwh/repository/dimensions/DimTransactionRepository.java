package at.htlleonding.dwh.repository.dimensions;

import at.htlleonding.dwh.entity.dimensions.DimTransactionType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DimTransactionRepository implements PanacheRepository<DimTransactionType> {
}
