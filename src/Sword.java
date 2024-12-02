public class Sword implements Accessories{
    private String name;
    private double power;
    private int level = 1;
    private double weight = 2.5;
    private Character owner = null;

    public Sword(String name, double power) {
        this.name = name;
        this.power = power;
    }

    @Override
    public void upgrade() {
        this.level++;
        this.power += 2.5 ;
    }

    @Override
    public double getPower() {
        return power;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void showState() {
        System.out.println("Slot 1 | Sword Name: " + name + " +physical_power : " + power + " Level: " + level + " Weight: " + weight + " Owner: " + owner.getName());
    }

    @Override
    public void setOwner(Character Owner) {
        this.owner = Owner;
    }

    @Override
    public Character getOwner() {
        return owner;
    }
}
