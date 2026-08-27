package models;
public class TypeQuestion {
    public enum TypeName {
        MULTIPLE_CHOICE, ESSAY
    };
    private int typeId;
    private TypeName typeName;
    public TypeQuestion() {
    }
    

    public TypeQuestion(int typeId, TypeName typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    

    @Override
    public String toString() {
        return "TypeQuestion [typeId=" + typeId + ", typeName=" + typeName + "]";
    }


    public int getTypeId() {
        return typeId;
    }


    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }


    public TypeName getTypeName() {
        return typeName;
    }


    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    
}
