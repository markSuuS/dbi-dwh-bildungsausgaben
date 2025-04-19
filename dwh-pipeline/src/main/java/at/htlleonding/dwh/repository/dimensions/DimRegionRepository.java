package at.htlleonding.dwh.repository.dimensions;

import at.htlleonding.dwh.entity.dimensions.DimRegion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DimRegionRepository implements PanacheRepository<DimRegion> {
}
