// BaseRPGChar Class

import static java.lang.Math.abs;

public class BaseRPGChar implements RPGCharacter {
    private String name;
    protected double MaxHP , MaxMP;
    protected double HP, MP, Atk, Def, speed;
    private BaseAccessory accessory;

    public BaseRPGChar(String name, double HP, double MP, double Atk, double Def, double speed) {
        this.name = name;
        this.MaxHP = HP;
        this.HP = MaxHP;
        this.MaxMP = MP;
        this.MP = MaxMP;
        this.Atk = Atk;
        this.Def = Def;
        this.speed = speed;
    }

    @Override
    public void equip(BaseAccessory accessory) {
        this.accessory = accessory;
        speed -= accessory.weight;
        if (accessory instanceof BaseAccessory.Gauntlets) {
            Atk += 10;
        }else if (accessory instanceof BaseAccessory.Boots){
            speed += 12;
        }else if (accessory instanceof BaseAccessory.Ring){
            HP += 10;
        }

        accessory.setOwner(this);
        System.out.println(name + " is equipping " + accessory.getAccessoryName() + " ***");
    }

    @Override
    public void showStats() {
        System.out.println(".........................................................");
        System.out.println("Status of " + name);
        System.out.println("HP: " + HP + ", MP: " + MP + ", Atk: " + Atk + ", Def: " + Def + ", Speed: " + speed);
        if (accessory != null) {
            System.out.println("Equipped Accessory: " + accessory.getAccessoryName());
        } else {
            System.out.println("No accessory equipped.");
        }
        System.out.println(".........................................................");
    }

    public static class PhysicalChar extends BaseRPGChar {
        public PhysicalChar(String name, double MaxHP, double MaxMP, double Atk, double Def, double speed) {
            super(name, MaxHP, MaxMP, Atk, Def, speed);
        }

        public void physicalAttack(BaseRPGChar target) {
            target.HP = target.HP - Atk;
            if (target != null) {
                if (target.HP <= 0) {
                    target.HP = 0;
                    System.out.println(target.getName() + " is DEAD");
                } else if (target == this) {
                    System.out.println("Can't attacking yourself");
                    return;
                }
                System.out.println(getName() + " is attacking " + target.getName() + " with " + Atk + " DMG");
            }else {
                System.out.println("Please input TARGET");
            }
        }
    }

    public static class MagicalChar extends BaseRPGChar {
        public MagicalChar(String name, double MaxHP, double MaxMP, double Atk, double Def, double speed) {
            super(name, MaxHP, MaxMP, Atk, Def, speed);
        }

        public void castSpell(BaseRPGChar target) {
            target.MP += 10;
            MP -= 10;
            if (target != null) {
                if (target.MP > target.MaxMP) {
                    target.MaxMP = target.MP;
                } else if (target == this) {
                    System.out.println("Casting yourself : " + getName());
                    return;
                }
                System.out.println(getName() + " is casting a spell! for " + target.getName());
            }else {
                System.out.println("Please input TARGET");
            }
        }
    }

    public static class Knight extends PhysicalChar {
        public Knight(String name) {
            super(name, 200, 50, 100, 150, 50);
        }

        public void slash(BaseRPGChar target) {
            target.HP -= abs(Atk*1.2 - target.Def);
            if (target != null) {
                if (target.HP <= 0) {
                    target.HP = 0;
                    System.out.println(target.getName() + " is DEAD");
                } else if (target == this) {
                    System.out.println("Can't slashing yourself");
                    return;
                }
                System.out.println(getName() + " is slashing " + target.getName() + " with " + abs(Atk * 1.2 - target.Def) + " DMG");
            }else {
                System.out.println("Please input TARGET");
            }
        }
    }

    public static class Archer extends PhysicalChar {
        public Archer(String name) {
            super(name, 150, 30, 120, 50, 75);
        }

        public void shoot(BaseRPGChar target) {
            target.HP -= abs(Atk*1.5 - target.Def);
            if (target != null) {
                if (target.HP <= 0) {
                    target.HP = 0;
                    System.out.println(target.getName() + " is DEAD");
                } else if (target == this) {
                    System.out.println("Can't shooting yourself");
                    return;
                }
                System.out.println(getName() + " is shooting!" + target.getName() + " with " + abs(Atk * 1.5 - target.Def) + " DMG");
            }else {
                System.out.println("Please input TARGET");
            }
        }
    }

    public static class Mage extends MagicalChar {
        public Mage(String name) {
            super(name, 120, 200, 80, 50, 60);
        }

        public void lightningBolt(BaseRPGChar target) {
            target.HP -= Atk;
            MP -= 5;
            if (target != null) {
                if (target.HP <= 0) {
                    target.HP = 0;
                    System.out.println(target.getName() + " is DEAD");
                    return;
                } else if (target == this) {
                    System.out.println("Can't lightning yourself");
                    return;
                }
                System.out.println(getName() + " casts Lightning Bolt!");
            }else{
                System.out.println("Please input TARGET");
            }
        }
    }

    public static class Healer extends MagicalChar {
        public Healer(String name) {
            super(name, 150, 175, 60, 65, 50);
        }

        public void heal(BaseRPGChar target) {
            if (target != null) {
                target.HP += 40;
                MP -= 20;
                if (target.HP > target.MaxHP) {
                    target.HP = target.MaxHP;
                } else if (target == this) {
                    System.out.println("Healing yourself : " + getName());
                    return;
                }
                System.out.println(getName() + " is healing " + target.getName());
            }else{
                System.out.println("Please input TARGET");
            }
        }
    }

    public String getName() {
        return name;
    }
}