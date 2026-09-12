package models;
public class Position {
    public enum PositionName {
        DEV, TEST, SCRUM_MASTER, PM
    };
    private int positionId;
    private PositionName positionName;
    public Position() {
    }
    
    public Position(int positionId, PositionName positionName) {
        this.positionId = positionId;
        this.positionName = positionName;
    }

    

    @Override
    public String toString() {
        return "Position [positionId=" + positionId + ", positionName=" + positionName + "]";
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public PositionName getPositionName() {
        return positionName;
    }

    public void setPositionName(PositionName positionName) {
        this.positionName = positionName;
    }

    
}
