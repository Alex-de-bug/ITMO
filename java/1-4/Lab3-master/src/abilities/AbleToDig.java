package abilities;

import exceptions.FreePlaceNotFoundException;
import locations.Cave;

public interface AbleToDig {

    void dig(Cave cave) throws FreePlaceNotFoundException;
}
