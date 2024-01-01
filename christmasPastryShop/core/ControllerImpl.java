package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

public class ControllerImpl implements Controller {
    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalIncome;
    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository=delicacyRepository;
        this.cocktailRepository=cocktailRepository;
        this.boothRepository=boothRepository;
        totalIncome=0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        for (Delicacy de:delicacyRepository.getAll()) {
            if (de.getName().equals(name)&&de.getClass().getSimpleName().equals(type)){
                throw new IllegalArgumentException(String.format("%s %s is already in the pastry shop!",type,name));
            }
        }

        Delicacy delicacy = null;
        if (type.equals("Gingerbread")){
            delicacy=new Gingerbread(name,price);
            
        } else if (type.equals("Stolen")) {
            delicacy=new Stolen(name,price);
            
        }
        this.delicacyRepository.add(delicacy);
        return String.format("Added delicacy %s - %s to the pastry shop!",name,type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        for (Cocktail co:cocktailRepository.getAll()) {
            if (co.getName().equals(name)){
                throw new IllegalArgumentException(String.format("%s %s is already in the pastry shop!",this.getClass().getSimpleName(),name));
            }
        }

        Cocktail cocktail = null;
        if (type.equals("MulledWine")){
            cocktail=new MulledWine(name,size,brand);

        } else if (type.equals("Hibernation")) {
            cocktail=new Hibernation(name,size,brand);

        }
        this.cocktailRepository.add(cocktail);
        return String.format("Added cocktail %s - %s to the pastry shop!",name,brand);
    }


    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        for (Booth bo:boothRepository.getAll()) {
            if (bo.getBoothNumber()==boothNumber){
                throw new IllegalArgumentException(String.format("Booth %d is already added to the pastry shop!",bo.getBoothNumber()));
            }
        }

        Booth booth = null;
        if (type.equals("OpenBooth")){
            booth=new OpenBooth(boothNumber,capacity);

        } else if (type.equals("PrivateBooth")) {
            booth=new PrivateBooth(boothNumber,capacity);

        }
        this.boothRepository.add(booth);
        return String.format("Added booth number %d in the pastry shop!",boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        for (Booth booth:boothRepository.getAll()) {
            if (!booth.isReserved()&&booth.getCapacity()>=numberOfPeople){
                booth.reserve(numberOfPeople);
                return String.format("Booth %d has been reserved for %d people!",booth.getBoothNumber(),numberOfPeople);
            }
        }
        return String.format("No available booth for %d people!",numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        for (Booth booth:boothRepository.getAll()) {
            if (booth.getBoothNumber()==boothNumber){
                double bill= booth.getBill();
                totalIncome+=bill;
                StringBuilder str=new StringBuilder();
                str.append(String.format("Booth: %d",boothNumber));
                str.append(System.lineSeparator());
                str.append(String.format("Bill: %.2f",bill));
                return str.toString();
            }
        }
        return null;
    }

    @Override
    public String getIncome() {
       return String.format("Income: %.2flv",totalIncome);
    }
}
