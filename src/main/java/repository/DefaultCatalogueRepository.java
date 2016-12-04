package repository;

import stock.Catalogue;


/**
 This class could read from some repository like a database, CSV file, rest service to build a catalogue of all stock items
 Using dependency injection a new repository could be injected
 **/

public class DefaultCatalogueRepository implements CatalogueRepository {

    @Override
    public Catalogue load() {
        return new Catalogue();
    }
}
