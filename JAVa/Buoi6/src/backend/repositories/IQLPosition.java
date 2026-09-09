package backend.repositories;

import java.util.List;

import models.ConnectionDetail;
import models.Position;

public interface IQLPosition {
    List<Position> displayPosition(ConnectionDetail detail);
}
