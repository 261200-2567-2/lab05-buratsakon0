// Accessory Interface
public interface Accessory {
    void setOwner(BaseRPGChar owner);
    void upgrade();
    String getAccessoryName();
}