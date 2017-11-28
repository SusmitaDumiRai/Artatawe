package ProfileImage;

public enum PresetImage {
    IMAGE1(""),
    IMAGE2(""),
    IMAGE3(""),
    IMAGE4(""),
    IMAGE5("");

    private final String fileName;

    PresetImage(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String toString() {
        return "The file name for this preset image is: "
                + this.fileName;
    }
}
