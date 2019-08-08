package core.exporting;

import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;

class Properties {

    private ArrayListMultimap<String,OutputFile> filesPerChecker;

    Properties() {
        filesPerChecker =  ArrayListMultimap.create();
    }

    public void add(String modelChecker, OutputFile specification) {
        filesPerChecker.put(modelChecker.toLowerCase(),specification);
    }

    boolean isEmpty() {
        return filesPerChecker.isEmpty();
    }

    String get(String checker, String extension) {
        ArrayList<OutputFile> outputFiles = new ArrayList<>(filesPerChecker.get(checker.toLowerCase()));
        boolean found = false;
        String result = "";
        for(int i = 0; i < outputFiles.size() && !found; i++)
            if(outputFiles.get(i).checkExtension(extension)) {
                found = true;
                result = outputFiles.get(i).getText();
            }
        return result;
    }

    ArrayList<OutputFile> get(String checker) {
        return new ArrayList<>(filesPerChecker.get(checker.toLowerCase()));
    }

}
