package at.htlleonding.dwh;

import at.htlleonding.dwh.entity.dimensions.DimInstitution;
import at.htlleonding.dwh.entity.dimensions.DimYear;
import at.htlleonding.dwh.repository.dimensions.DimInstitutionRepository;
import at.htlleonding.dwh.repository.dimensions.DimYearRepository;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@ApplicationScoped
public class InitBean {
    @Inject
    DimYearRepository dimYearRepository;

    @Inject
    DimInstitutionRepository dimInstitutionRepository;

    @Transactional
    void init(@Observes StartupEvent ev) {
        insertDimYears();
        insertDimInstitutions();
    }

    void insertDimYears(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("csv-data/dim-years.csv")) {
            String[] content = new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            for(String line : Arrays.stream(content).skip(1).toList()){
                String[] parts = line.split(";");
                String code = parts[0];
                int year = Integer.parseInt(parts[1]);

                DimYear dimYear = new DimYear();
                dimYear.setCode(code);
                dimYear.setYear(year);
                dimYearRepository.persist(dimYear);
            }
        } catch (IOException e) {
            Log.error("Error during reading dim years file!");
        }
    }

    void insertDimInstitutions(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("csv-data/dim-institution.csv")) {
            String[] content = new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            for(String line : Arrays.stream(content).skip(1).toList()){
                String[] parts = line.split(";");

                if(parts[2].trim().isBlank()){
                    continue;
                }

                String code1 = parts[0];
                String institutionDe = parts[1].replaceAll("<\\d+>", "").trim();
                String code2 = parts[2];
                String institutionEn = parts[3].replaceAll("<\\d+>", "").trim();

                DimInstitution dimInstitution = new DimInstitution();
                dimInstitution.setCode1(code1);
                dimInstitution.setInstitutionDe(institutionDe);
                dimInstitution.setCode2(code2);
                dimInstitution.setInstitutionEn(institutionEn);
                dimInstitutionRepository.persist(dimInstitution);
            }
        } catch (IOException e) {
            Log.error("Error during reading dim institutions file!");
        }
    }
}
