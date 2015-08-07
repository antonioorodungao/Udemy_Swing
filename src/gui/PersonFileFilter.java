package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Oro on 8/7/2015.
 */
public class PersonFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {

        if(f.isDirectory()){
            return true;
        }
        String name = f.getName();
        if("per".equals(Utils.getFileExtension(name))){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Person database files (*.per)";
    }
}
