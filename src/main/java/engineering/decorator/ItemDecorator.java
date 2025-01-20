package engineering.decorator;

//decorator
public abstract class ItemDecorator extends MenuItem{

    //aggregazione
    private MenuItem item;

    public ItemDecorator(MenuItem item){
        this.item = item;
    }


    @Override
    public String getName(){
        String resultsFromRedirection = this.item.getName();
        return resultsFromRedirection;
    }

    @Override
    public double getPrice(){
        Double resultsFromRedirection = this.item.getPrice();
        return resultsFromRedirection;
    }

    @Override
    public double getCalories(){
        Double resultsFromRedirection = this.item.getCalories();
        return resultsFromRedirection;
    }

    @Override
    public double getCaffeine(){
        Double resultsFromRedirection = this.item.getCaffeine();
        return resultsFromRedirection;
    }

    @Override
    public String getDescription(){
        String resultsFromRedirection = this.item.getDescription();
        return resultsFromRedirection;
    }

    @Override
    public String getImage(){
        String resultsFromRedirection = this.item.getImage();
        return resultsFromRedirection;
    }


}
