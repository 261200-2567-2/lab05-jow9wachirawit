public class wizard implements Character {
    private String name;
    private double Hp = 70, max_Hp = 70;
    private double Mp = 120, max_Mp = 120;
    private int level = 1;
    private double magic_power = 25, physical_power = 5;
    private double agi = 10, dex = 10, def = 5;
    private boolean alive = true;
    private Accessories equipment[] = {null, null, null};//0: sword or staff | 1: shied | 2: shoes

    public wizard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public double skill(int select) {
        if (alive) {
            if (select == 1) {
                if (Mp >= 50) {
                    Mp -= 50;
                    System.out.println("Megumin explosion!!! (FireBold) ");
                    if (equipment[0] instanceof Staff) {
                        return (magic_power + equipment[0].getPower()) * dex * 10;
                    }
                    return magic_power * 10 * dex;
                } else {
                    System.out.println("Mana not enough");
                }
            } else if (select == 2) {
                if (Mp >= 25) {
                    System.out.println("LightningBold!!!");
                    if (equipment[2] instanceof Shoes && equipment[0] instanceof Staff) {
                        return (magic_power + equipment[2].getPower() + equipment[0].getPower() + agi * 3);
                    } else if (equipment[2] instanceof Shoes) {
                        return (magic_power + equipment[2].getPower() + agi * 3);
                    } else if (equipment[0] instanceof Staff) {
                        return (magic_power + equipment[0].getPower() + agi * 3);
                    }
                    return magic_power * 3 * agi;
                } else {
                    System.out.println("Mana not enough");
                }

            } else if (select == 3) {
                if (Mp >= 10) {
                    if (equipment[0] instanceof Staff) {
                        Hp += equipment[0].getPower() + magic_power;
                    }
                    Hp += magic_power;
                    if (Hp > max_Hp) Hp = max_Hp;
                } else {
                    System.out.println("Mana not enough");
                }
            }
        } else {
            System.out.println(this.name + " is Die");
        }
        return 0;
    }

    @Override
    public void takeDamage(Character attacker, double damage) {
        if (alive) {
            if (attacker != this) {
                if (equipment[1] instanceof Shied) {
                    def += equipment[1].getPower();
                }
                damage = damage - def;
                if(damage < 0)damage = 0;
                Hp -= damage;
                if (Hp < 0) Hp = 0;
                if (equipment[1] instanceof Shied) {
                    def -= equipment[1].getPower();
                }
                System.out.println("You got " + damage + " damage from " + attacker.getName());

            } else {
                System.out.println("You Can't hurt yourself " + this.name);
            }
        } else {
            System.out.println("You Can't Attack Body");
        }
        if (Hp <= 0) alive = false;
    }

    @Override
    public void showSkill() {
        if (alive) {
            System.out.println(this.name + " Have skill >>>>>");
            System.out.println(" -------------------------------------");
            System.out.println("| FireBold      : ATK   :  50 Mp : 1  |");
            System.out.println("| LightningBold : ATK   :  25 Mp : 2  |");
            System.out.println("| Heal          : HEAL  :  10 Mp : 3  |");
            System.out.println(" -------------------------------------");
        } else {
            System.out.println(this.name + " is Die");
        }
    }


    @Override
    public double NormalATK() {
        if (alive) {
            if ((equipment[0] instanceof Sword)) {
                return physical_power + (equipment[0].getPower() / 2);
            } else {
                return physical_power;
            }
        } else {
            System.out.println(this.name + " is Die");
        }
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

            magic_power += 1.5 * (level);
            physical_power += 1.25 * (level);

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

    @Override
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
            }
            else{
                System.out.println(slot+" It empty");
            }
        }
    }

    @Override
    public void showState() {
        if (alive) {
            System.out.println("Name: " + name + " Class: Wizard" + " Level: " + level);
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
