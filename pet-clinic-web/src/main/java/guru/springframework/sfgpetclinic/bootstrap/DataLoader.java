package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.PetTypeService;
import guru.springframework.sfgpetclinic.service.SpecialtiesService;
import guru.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtiesService specialtiesService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtiesService specialtiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtiesService = specialtiesService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count=petTypeService.findAll().size();
        if (count == 0){
            loadData();
        }




    }

    private void loadData() {
        //        creating types of pets
        PetType dog=new PetType();
        dog.setName("Dog");
        PetType saveDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        cat.setName("Just Cat");
        PetType saveCatPetType=petTypeService.save(cat);

//      adding spec
        Specialty radiology =new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology=specialtiesService.save(radiology);

        Specialty surgery=new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery=specialtiesService.save(surgery);

        Specialty dentistry=new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry=specialtiesService.save(dentistry);


//        creating and setting new owners
        Owner owner1=new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickelrel");
        owner1.setCity("Miami");
        owner1.setTelephone("123123312231");

        Pet mikesPet=new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);


        Owner owner2=new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenane");
        owner2.setAddress("123 Brickelrel");
        owner2.setCity("Miami");
        owner2.setTelephone("123123312231");

        Pet fionaCat=new Pet();
        fionaCat.setName("Just Cat");
        fionaCat.setOwner(owner2);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setPetType(saveCatPetType);
        owner2.getPets().add(fionaCat);


        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1=new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Milan");
        vet2.setLastName("Krkobabic");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vet's ....");
    }
}
