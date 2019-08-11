package core.exporting;

class ExtensionText {

    private String extension;
    private String text;

    ExtensionText(String extension, String text) {
        this.extension = extension.toLowerCase();
        this.text = text;
    }

    boolean checkExtension(String extension) {
        return this.extension.equalsIgnoreCase(extension);
    }

    String getText() {
        return text;
    }

}
