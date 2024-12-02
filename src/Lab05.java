public class Lab05 {
    public static void main(String[] args) {
        Staff a = new Staff("L", 20);
        Sword b = new Sword("B", 20);
        Shied s = new Shied("S", 20);
        Shoes nike = new Shoes("Nike", 50);
        wizard A = new wizard("Arthur");
        knight B = new knight("B");
        A.takeDamage(B,B.skill(1));
        B.showState();
        B.equip(a);
        B.equip(nike);
        B.equip(s);
        B.showState();
        B.remove(1);
        B.remove(2);
        B.remove(1);
        B.remove(3);
        B.showState();

    }
    }

