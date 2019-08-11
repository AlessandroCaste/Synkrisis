package core.exporting;

import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;

class Properties {

    private ArrayListMultimap<String, ExtensionText> filesPerFormat;

    Properties() {
        filesPerFormat =  ArrayListMultimap.create();
    }

    public void add(String modelChecker, ExtensionText specification) {
        filesPerFormat.put(modelChecker.toLowerCase(),specification);
    }

    boolean isEmpty() {
        return filesPerFormat.isEmpty();
    }

    String get(String format, String extension) {
        ArrayList<ExtensionText> extensionTexts = new ArrayList<>(filesPerFormat.get(format.toLowerCase()));
        boolean found = false;
        String result = "";
        for(int i = 0; i < extensionTexts.size() && !found; i++)
            if(extensionTexts.get(i).checkExtension(extension)) {
                found = true;
                result = extensionTexts.get(i).getText();
            }
        return result;
    }

    ArrayList<ExtensionText> get(String format) {
        return new ArrayList<>(filesPerFormat.get(format.toLowerCase()));
    }

}
