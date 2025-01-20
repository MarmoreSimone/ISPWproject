package engineering.decorator;

//decorator
public abstract class ItemDecorator extends MenuItem{

    //aggregazione
    private MenuItem item;

    protected ItemDecorator(MenuItem item){
        this.item = item;
    }


    @Override
    public String getName(){
        return this.item.getName();
    }

    @Override
    public double getPrice(){
        return this.item.getPrice();
    }

    @Override
    public double getCalories(){
        return this.item.getCalories();
    }

    @Override
    public double getCaffeine(){
        return this.item.getCaffeine();
    }

    @Override
    public String getDescription(){
        return this.item.getDescription();
    }

    @Override
    public String getImage(){
        return this.item.getImage();
    }


}
