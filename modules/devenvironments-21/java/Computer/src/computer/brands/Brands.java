package computer.brands;

public enum Brands {
    APPLE("Apple"),
    NONE("Unknown"),
    DELL("Dell");

    private final String brandName;

    private Brands(String brandName){
        this.brandName = brandName;
    }

    public String getBrandName(){
        return this.brandName;
    }

}
