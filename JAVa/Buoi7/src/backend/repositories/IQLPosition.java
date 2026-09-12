package backend.repositories;

import java.util.List;

import models.Position;

public interface IQLPosition {
    List<Position> displayPosition();
    void insertPosition(int positionId, String positionName);
    void updatePosition(int positionId, String positionName);
    void deletePosition(int positionId);
}
