public interface Character {
     String getName();
     double skill(int select);
     void takeDamage(Character attacker,double damage);
     void showSkill();
     double NormalATK();
     void upLevel();
     void remove(int slot); //1 2 3
     void showState();
     void equip(Accessories item);
}
