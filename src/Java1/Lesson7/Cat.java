package Java1.Lesson7;

public class Cat {
    private String name;
    private  int appetite;
    private boolean well_fed;
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.well_fed = false;
    }
    public void eat(Plate p) {
        if (well_fed == false && p.getFood() >= appetite) {
            p.decreaseFood(appetite);
            well_fed = true;
            System.out.println("Кот " + name + " наелся, съел " + appetite);
        } else checkFood(p);
    }
    private void checkFood(Plate p) {
        if (p.getFood() < appetite && p.getFood() > 0) System.out.println("В тарелке еды меньше, чем аппетит кота " + name + " : " + appetite);
        else if (p.getFood() == 0) System.out.println("Тарелка пуста, для кота " + name + " еды не хватило :(");
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean getWell_fed() {
        return well_fed;
    }
}
