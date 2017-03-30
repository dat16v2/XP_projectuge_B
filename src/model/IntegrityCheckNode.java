package model;

public class IntegrityCheckNode<N> {
    private N elementType;
    private String elementName;
    private boolean altered = false;
    private boolean newNode = false;


    public IntegrityCheckNode(N elementType, String elementName) {
        this.elementType = elementType;
        this.elementName = elementName;
    }

    public N getElementType() {
        return elementType;
    }

    public String getElementName() {
        return elementName;
    }

    public boolean isAltered() {
        return altered;
    }

    public boolean isNewNode() {
        return newNode;
    }

    public void setAltered(boolean altered) {
        this.altered = altered;
    }

    public void setNewNode(boolean newNode) {
        this.newNode = newNode;
    }

    public void setElement(N elementType) {
        this.elementType = elementType;
    }
}
