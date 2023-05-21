package model.xml.validation;

/**
 * Chain of responsability.
 */
public abstract class AbstractValidation {

    /**
     * The next object to validate in the chain.
     */
    private AbstractValidation successor;

    /**
     * The filename.
     */
    private String fileName;

    /**
     * Set the next object to validate.
     * @param successor
     */
    public void setSuccessor(AbstractValidation successor) {
        this.successor = successor;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Verify if the chain is valid and return true if there is no successor.
     * @return true if the chain is valid, false otherwise.
     */
    public boolean isValid() {
        if (this.successor != null) {
            this.successor.setFileName(this.fileName);
            return this.successor.isValid();
        }
        return true;
    }
}
