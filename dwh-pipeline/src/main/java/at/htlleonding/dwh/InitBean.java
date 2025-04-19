package at.htlleonding.dwh;

import at.htlleonding.dwh.entity.dimensions.*;
import at.htlleonding.dwh.entity.facts.FactEducationSpending;
import at.htlleonding.dwh.entity.facts.FactSpending;
import at.htlleonding.dwh.repository.dimensions.DimInstitutionRepository;
import at.htlleonding.dwh.repository.dimensions.DimRegionRepository;
import at.htlleonding.dwh.repository.dimensions.DimTransactionRepository;
import at.htlleonding.dwh.repository.dimensions.DimYearRepository;
import at.htlleonding.dwh.repository.facts.FactEducationSpendingRepository;
import at.htlleonding.dwh.repository.facts.FactSpendingRepository;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class InitBean {
    @Inject
    DimYearRepository dimYearRepository;

    @Inject
    DimInstitutionRepository dimInstitutionRepository;

    @Inject
    DimRegionRepository dimRegionRepository;

    @Inject
    DimTransactionRepository dimTransactionRepository;

    @Inject
    FactSpendingRepository factSpendingRepository;

    @Inject
    FactEducationSpendingRepository factEducationSpendingRepository;

    @Transactional
    void init(@Observes StartupEvent ev) {
        insertDimYears();
        insertDimInstitutions();
        insertDimRegions();
        insertDimTransactions();
        insertFactSpendings();
    }

    //#region Insert Dimensions
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

                if(!parts[2].trim().isBlank()){
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
            }
        } catch (IOException e) {
            Log.error("Error during reading dim institutions file!");
        }
    }

    void insertDimRegions() {
        List<DimRegion> dimRegions = new ArrayList<>();

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("csv-data/dim-regions.csv")) {
            String[] content = new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            for(String line : Arrays.stream(content).skip(1).toList()){
                String[] parts = line.split(";");
                String code = parts[0];
                String regionDe = parts[1].split(" ")[0];
                String regionEn = parts[3].split(" ")[0];

                if(!dimRegions.stream().anyMatch(dr -> dr.getRegionDe().equals(regionDe))){
                    DimRegion dimRegion = new DimRegion();
                    dimRegion.addCode(code);
                    dimRegion.setRegionDe(regionDe);
                    dimRegion.setRegionEn(regionEn);
                    dimRegions.add(dimRegion);
                } else {
                    DimRegion result = dimRegions.stream().filter(dr -> dr.getRegionDe().equals(regionDe)).findFirst().get();
                    result.addCode(code);
                }
            }

            dimRegionRepository.persist(dimRegions);
        } catch (IOException e) {
            Log.error("Error during reading dim regions file!");
        }
    }

    void insertDimTransactions(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("csv-data/dim-transactions.csv")) {
            String[] content = new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            for(String line : Arrays.stream(content).skip(1).toList()){
                String[] parts = line.split(";");

                String code = parts[0];
                String transactionDe = parts[1];
                String transactionEn = parts[2];
                DimTransactionType dimTransaction = new DimTransactionType();
                dimTransaction.setCode(code);
                dimTransaction.setTransactionTypeDe(transactionDe);
                dimTransaction.setTransactionTypeEn(transactionEn);

                dimTransactionRepository.persist(dimTransaction);
            }
        } catch (IOException e) {
            Log.error("Error during reading dim transactions file!");
        }
    }
    //#endregion

    void insertFactSpendings(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("csv-data/fact-spendings.csv")) {
            String[] content = new String(is.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            for(String line : Arrays.stream(content).skip(1).toList()){
                String[] parts = line.split(";");
                int year = Integer.parseInt(parts[0].split("-")[1]);
                String regionCode = parts[1];
                String institutionCode = parts[2];

                List<FactSpending> factSpendings = new ArrayList<>();

                if(!parts[3].trim().equals("0")){
                    addFactSpending("F-T02", parts[3], factSpendings);
                }
                if(!parts[4].trim().equals("0")){
                    addFactSpending("F-T03", parts[4], factSpendings);
                }
                if(!parts[5].trim().equals("0")){
                    addFactSpending("F-T04", parts[5], factSpendings);
                }
                if(!parts[6].trim().equals("0")){
                    addFactSpending("F-T05", parts[6], factSpendings);
                }
                if(!parts[7].trim().equals("0")){
                    addFactSpending("F-T06", parts[7], factSpendings);
                }
                if(!parts[8].trim().equals("0")){
                    addFactSpending("F-T07", parts[8], factSpendings);
                }
                if(!parts[9].trim().equals("0")){
                    addFactSpending("F-T08", parts[9], factSpendings);
                }
                if(!parts[10].trim().equals("0")){
                    addFactSpending("F-T09", parts[10], factSpendings);
                }
                if(!parts[11].trim().equals("0")){
                    addFactSpending("F-T10", parts[11], factSpendings);
                }
                if(!parts[12].trim().equals("0")){
                    addFactSpending("F-T11", parts[12], factSpendings);
                }

                FactEducationSpending factEducationSpending = new FactEducationSpending();
                factEducationSpending.setYear(year);
                factEducationSpending.setInstitution(institutionCode);
                factEducationSpending.setRegionCode(regionCode);
                factEducationSpending.setFactSpendings(factSpendings);

                factSpendingRepository.persist(factSpendings);
                factEducationSpendingRepository.persist(factEducationSpending);
            }

        } catch (IOException e) {
            Log.error("Error during reading fact spendings file!");
        }
    }

    void addFactSpending(String transactionTypeCode, String amount, List<FactSpending> factSpendings){
        FactSpending factSpending = new FactSpending();
        factSpending.setAmount(Long.parseLong(amount.replace("\"", "").trim()));
        factSpending.setTransactionTypeCode(transactionTypeCode);
        factSpendings.add(factSpending);
    }
}
