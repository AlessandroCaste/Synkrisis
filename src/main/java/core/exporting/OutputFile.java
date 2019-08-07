package core.exporting;

class OutputFile {

    private String extension;
    private String text;

    OutputFile(String extension,String text) {
        this.extension = extension;
        this.text = text;
    }

    boolean checkExtension(String extension) {
        return this.extension.equalsIgnoreCase(extension);
    }

    String getExtension() { return extension; }

    String getText() {
        return text;
    }

}
