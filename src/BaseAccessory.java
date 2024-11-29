// BaseAccessory Class
public abstract class BaseAccessory implements Accessory {
    private String accessoryName;
    protected BaseRPGChar owner;
    protected int level;
    protected double weight;

    public BaseAccessory(String accessoryName) {
        this.accessoryName = accessoryName;
        this.level = 1;
    }

    @Override
    public void setOwner(BaseRPGChar owner) {
        this.owner = owner;
        if (this.owner != null) {
            // Remove weight effect from previous owner
            this.owner.speed += this.weight;
        }
        if (owner != null) {
            // Apply weight effect to the new owner
            owner.speed -= this.weight;
        }
    }

    @Override
    public String getAccessoryName() {
        return accessoryName;
    }

    public static class PhysicalAccessory extends BaseAccessory {
        public PhysicalAccessory(String accessoryName) {
            super(accessoryName);
        }

        @Override
        public void upgrade() {
            level++;
            owner.Def += 10;
        }
    }

    public static class MagicalAccessory extends BaseAccessory {
        public MagicalAccessory(String accessoryName) {
            super(accessoryName);
        }

        @Override
        public void upgrade() {
            owner.MP += 5;
        }
    }

    public static class Gauntlets extends PhysicalAccessory {
        public Gauntlets(String accessoryName) {
            super(accessoryName);
            weight = 5;
        }
        @Override
        public void upgrade() {
            owner.Atk += 10 + (0.5 * level);
            System.out.println(getAccessoryName() + " is upgraded !!!");
        }
    }

    public static class Boots extends PhysicalAccessory {
        public Boots(String accessoryName) {
            super(accessoryName);
            weight = 2;
        }

        @Override
        public void upgrade() {
            owner.speed += 5 + (0.5 * level);
            System.out.println("Boots is upgraded !!!");
        }
    }

    public static class Ring extends MagicalAccessory {
        public Ring(String accessoryName) {
            super(accessoryName);
            weight = 0.5;
        }

        @Override
        public void upgrade() {
            owner.HP += 5 + (0.5 * level);
            System.out.println(getAccessoryName() + " is upgraded !!!");
        }
    }
}