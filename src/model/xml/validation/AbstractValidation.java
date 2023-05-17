package model.xml.validation;

public abstract class AbstractValidation {

    private AbstractValidation successor;
    private String fileName;

    public void setSuccessor(AbstractValidation successor) {
        this.successor = successor;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isValid() {
        if (this.successor != null) {
            this.successor.setFileName(this.fileName);
            return this.successor.isValid();
        }
        return true;
    }
}
