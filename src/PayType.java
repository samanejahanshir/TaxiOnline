public enum PayType {
    BYCATCH("ByCatch"),
    BYACCOUNT("ByAccount");
    private String name;

    PayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
