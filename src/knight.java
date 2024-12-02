public class knight implements Character {
    private String name;
    private double Hp = 100, max_Hp = 100;
    private double Mp = 50, max_Mp = 50;
    private int level = 1;
    private double magic_power = 5, physical_power = 25;
    private double agi = 10, dex = 5, def = 10;
    private boolean isBlock = false;
    private boolean isCounter = false;
    private boolean alive = true;

    Accessories equipment[] = {null, null, null};//0: sword or staff | 1: shied | 2: shoes

    public knight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public double skill(int select) {
        if (alive) {
            if (select == 1) {
                if (Mp >= 20 && isBlock != true) {
                    Mp -= 20;
                    System.out.println(this.name + " BASH!!!");
                    if (equipment[0] instanceof Sword) {
                        return equipment[0].getPower() + physical_power;
                    }
                    return physical_power * 2;
                } else {
                    System.out.println("Mana not enough or You Are Block Now");
                }
                return 0;
            } else if (select == 2) {
                if (isBlock != true) {
                    if (Mp >= 50 && isCounter == false) {
                        System.out.println(this.name + " Counter Buff !!!");
                        isCounter = true;
                        return 0;
                    } else {
                        System.out.println("Mana not enough or Yor Are Counter Now");
                        return 0;
                    }
                }
                System.out.println("Yor are Block Now");
                return 0;
            } else {
                if (isBlock == false) {
                    System.out.println("Block!!!");
                    isBlock = true;
                } else if (isBlock == true) {
                    System.out.println("Un Block now");
                    isBlock = false;
                }
                return 0;
            }
        }
        System.out.println(this.name + " is down");
        return 0;
    }

    @Override
    public void takeDamage(Character attacker, double damage) {
        if (alive) {
            if (attacker != this) {
                if (equipment[2] != null) {
                    agi += equipment[2].getPower();
                }
                if (equipment[1] != null) {
                    def += equipment[1].getPower();
                }

                if (isCounter == true && attacker != this) {
                    damage -= def / 3;
                    isCounter = false;
                    attacker.takeDamage(this, damage / 1.25 + (agi / 5) + (dex / 2.5));
                }
                if (isBlock == true) {
                    damage -= def * 1.25;
                    isBlock = false;
                }
                damage = damage - def;
                if (damage < 0) damage = 0;

                Hp -= damage;
                if (Hp < 0) Hp = 0;
                System.out.println(this.name + " got " + damage + " damage form " + attacker.getName());
                if (equipment[2] != null) {
                    agi -= equipment[2].getPower();
                }
                if (equipment[1] != null) {
                    def -= equipment[1].getPower();
                }
            } else {
                System.out.println("You Can't hurt yourself " + this.name);
            }
        } else {
            System.out.println(this.name + " is Die");
        }
        if (Hp <= 0) alive = false;

    }

    @Override
    public void showSkill() {
        if (alive) {
            System.out.println(this.name + " Have skill >>>>>");
            System.out.println(" -----------------------------");
            System.out.println("| BASH    : ATK  : 20 Mp : 1  |");
            System.out.println("| Counter : Buff : 20 Mp : 2  |");
            System.out.println("| Block   : DEF  :  0 Mp : 3  |");
            System.out.println(" -----------------------------");
        } else {
            System.out.println(this.name + " is Die");
        }
    }

    @Override
    public double NormalATK() {
        if (alive) {
            if ((equipment[0] instanceof Sword)) {
                return physical_power + equipment[0].getPower();
            } else {
                return physical_power;
            }
        }
        System.out.println(this.name + " is Die");
        return 0;
    }

    @Override
    public void upLevel() {
        if (alive) {
            level++;
            max_Hp += 100 * level;
            max_Mp += 10 * level;

            Hp = max_Hp;
            Mp = max_Mp;

            magic_power += 1.25 * (level);
            physical_power += 1.5 * (level);

            def += 1.25 * (level);
            dex += 1.25 * (level);
            agi += 1.25 * (level);
            System.out.println(this.name + " levelUp!!!");
        } else {
            System.out.println(this.name + " is Die");
        }
    }

    @Override
    public void equip(Accessories item) {
        if (item.getOwner() == null) {
            if ((item instanceof Staff || item instanceof Sword) && equipment[0] == null) {
                equipment[0] = item;
                agi -= equipment[0].getWeight();
                equipment[0].setOwner(this);
            } else if (item instanceof Shied && equipment[1] == null) {
                equipment[1] = item;
                agi -= equipment[1].getWeight();
                equipment[1].setOwner(this);
            } else if (item instanceof Shoes && equipment[2] == null) {
                equipment[2] = item;
                equipment[2].setOwner(this);
            } else {
                System.out.println("You Can't equip this item");
            }
        } else {
            System.out.println("this item have Owner");
        }
        if (agi < 0) agi = 0;

    }


    @Override //fix it
    public void remove(int slot) {
        if (slot == 1 || slot == 2 || slot == 3) {
            if (slot == 1 && equipment[0] != null) {
                equipment[0].setOwner(null);
                agi += equipment[0].getWeight();
                equipment[0] = null;
            } else if (slot == 2 && equipment[1] != null) {
                equipment[1].setOwner(null);
                agi += equipment[1].getWeight();
                equipment[1] = null;
            } else if (slot == 3 && equipment[2] != null) {
                equipment[2].setOwner(null);
                equipment[2] = null;
            }else{
                System.out.println("It empty");
            }
        }

    }


    @Override
    public void showState() {
        if (alive) {
            System.out.println("Name: " + name + " Class: Kinght" + " Level: " + level);
            System.out.println("HP: " + Hp + "/" + max_Hp + " MP: " + Mp + "/" + max_Mp);
            System.out.println("Magic power: " + magic_power + " Physical power: " + physical_power);
            System.out.println("Agi: " + agi + " Dex: " + dex + " Def: " + def);
            if (equipment[0] != null) {
                equipment[0].showState();
            } else {
                System.out.println("Equipment slot 1 is Empty");
            }
            if (equipment[1] != null) {
                equipment[1].showState();
            } else {
                System.out.println("Equipment slot 2 is Empty");
            }
            if (equipment[2] != null) {
                equipment[2].showState();
            } else {
                System.out.println("Equipment slot 3 is Empty");
            }
            System.out.println("----------------------------");
        } else {
            System.out.println(this.name + " is Die");
        }
    }
}
