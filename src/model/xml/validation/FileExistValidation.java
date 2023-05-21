package model.xml.validation;

import java.io.File;

/**
 * Class that verify if a file exists.
 */
public class FileExistValidation extends AbstractValidation {

    @Override
    public boolean isValid() {
        File file = new File(getFileName());
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist.\n");
        }

        return super.isValid();
    }
}
