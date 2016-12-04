import checkout.Basket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pricingRule.PricingRule;
import pricingRule.PricingRuleFactory;
import pricingRule.StockPricingRules;
import stock.Catalogue;


public class BasketTest {

    private StockPricingRules pricingRules;

    @Before
    public void setup() {
        PricingRuleFactory factory = PricingRuleFactory.Instance;

        PricingRule aPrice =  factory.createUnderlyingPrice(Catalogue.A, 50);
        PricingRule aPriceWithSpecial = factory.createDiscountSpecial(aPrice, 3, 20);

        PricingRule bPrice = factory.createUnderlyingPrice(Catalogue.B, 30);
        PricingRule bPriceWithSpecial = factory.createDiscountSpecial(bPrice, 2, 15);

        PricingRule cRetailPrice = factory.createUnderlyingPrice(Catalogue.C, 20);
        PricingRule dRetailPrice = factory.createUnderlyingPrice(Catalogue.D, 15);

        pricingRules = new StockPricingRules();
        pricingRules.add(aPriceWithSpecial);
        pricingRules.add(bPriceWithSpecial);
        pricingRules.add(cRetailPrice);
        pricingRules.add(dRetailPrice);
    }

    @Test
    public void testSingleAGivesPriceOf50() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(50, total, 0);
    }

    @Test
    public void testAAGivesPriceOf100() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(100, total, 0);
    }

    @Test
    public void testAAAGivesPriceOf130() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(130, total, 0);
    }

    @Test
    public void testFourAsGivesPriceOf180() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(180, total, 0);
    }

    @Test
    public void testFiveAsGivesPriceOf230() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(230, total, 0);
    }

    @Test
    public void testSixAsGivesPriceOf260() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(260, total, 0);
    }

    @Test
    public void testABGivesPriceOf80() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.B);
        double total = basket.checkOut();
        Assert.assertEquals(80, total, 0);
    }

    @Test
    public void testABAAGivesPriceOf160() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.B);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        double total = basket.checkOut();
        Assert.assertEquals(160, total, 0);
    }

    @Test
    public void testBABGivesPriceOf95() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.B);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.B);
        double total = basket.checkOut();
        Assert.assertEquals(95, total, 0);
    }

    @Test
    public void testABAABGivesPriceOf175() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.B);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.B);
        double total = basket.checkOut();
        Assert.assertEquals(175, total, 0);
    }

    @Test
    public void testABCDGivesPriceOf115() {
        Basket basket = Basket.startTransaction(pricingRules);
        basket.scan(Catalogue.A);
        basket.scan(Catalogue.B);
        basket.scan(Catalogue.C);
        basket.scan(Catalogue.D);
        double total = basket.checkOut();
        Assert.assertEquals(115, total, 0);
    }
}
