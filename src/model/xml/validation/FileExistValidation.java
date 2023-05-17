package model.xml.validation;

import java.io.File;

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
