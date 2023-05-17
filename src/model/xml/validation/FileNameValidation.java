package model.xml.validation;

public class FileNameValidation extends AbstractValidation {

    @Override
    public boolean isValid() {
        if (getFileName() == null || !getFileName().endsWith(".xml")) {
            throw new IllegalArgumentException("File name is invalid.\n");
        }

        return super.isValid();
    }
}
