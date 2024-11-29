public class Lab05 {
    public static void main(String[] args) {
        // Create characters
        BaseRPGChar.Knight knight = new BaseRPGChar.Knight("Arthur");
        BaseRPGChar.Archer archer = new BaseRPGChar.Archer("Legolas");
        BaseRPGChar.Mage mage = new BaseRPGChar.Mage("Gandalf");
        BaseRPGChar.Healer healer= new BaseRPGChar.Healer("Florence");

        // Create accessories
        BaseAccessory.Gauntlets gauntlets = new BaseAccessory.Gauntlets("Steel Gauntlets");
        BaseAccessory.Boots boots = new BaseAccessory.Boots("Speedy Boots");
        BaseAccessory.Ring ring = new BaseAccessory.Ring("Magic Ring");

        knight.showStats();

        // Equip and show stats
        knight.equip(gauntlets);

        knight.showStats();
        gauntlets.upgrade();
        gauntlets.upgrade();
        gauntlets.upgrade();
        knight.showStats();

        System.out.println(" ");

        archer.showStats();
        knight.physicalAttack(archer);
        archer.showStats();
        healer.showStats();
        healer.heal(archer);
        knight.slash(archer);
        healer.heal(archer);
        healer.heal(archer);
        healer.heal(archer);
        archer.showStats();
        mage.showStats();
        mage.castSpell(healer);
        healer.showStats();
        mage.lightningBolt(healer);
        mage.lightningBolt(healer);

        System.out.println(" ");

        mage.showStats();
        archer.shoot(mage);

    }
}
