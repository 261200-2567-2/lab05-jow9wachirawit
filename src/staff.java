public class Staff implements Accessories{
    private String name;
    private double power;
    private int level = 1;
    private double weight = 1.7;
    private Character owner = null;

    public Staff(String name, double power) {
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
        return this.power;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void showState() {
        System.out.println("Slot 1 | Staff Name: " + name + " +magic_power : " + power + " Level: " + level + " Weight: " + weight + " Owner: " + owner.getName());
    }

    @Override
    public void setOwner(Character Owner) {
        this.owner = Owner;
    }

    @Override
    public Character getOwner() {
        return this.owner;
    }
}
