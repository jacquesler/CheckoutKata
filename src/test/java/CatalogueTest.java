import org.junit.Assert;
import org.junit.Test;
import stock.Catalogue;
import stock.StockItem;

public class CatalogueTest {

    @Test
    public void testCanGetStockItemFromSkuCode(){
        Catalogue catalogue = new Catalogue();
        StockItem result = catalogue.getStockItem("A");
        Assert.assertEquals(Catalogue.A, result);
    }

    @Test
    public void testCanGetStockItemFromLowerCaseSkuCode(){
        Catalogue catalogue = new Catalogue();
        StockItem result = catalogue.getStockItem("a");
        Assert.assertEquals(Catalogue.A, result);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionThrownWhenSkuCodeDoesNotExist(){
        Catalogue catalogue = new Catalogue();
        catalogue.getStockItem("e");
    }
}
