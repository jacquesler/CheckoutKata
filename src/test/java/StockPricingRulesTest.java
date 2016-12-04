import org.junit.Assert;
import org.junit.Test;
import pricingRule.*;
import stock.Catalogue;

public class StockPricingRulesTest {

    @Test
    public void testSubTotalForGivenStockItemIsCalculated(){
        StockPricingRules specials = new StockPricingRules();
        specials.add(PricingRuleFactory.Instance.createUnderlyingPrice(Catalogue.A, 50));
        specials.add(PricingRuleFactory.Instance.createUnderlyingPrice(Catalogue.B, 20));

        PricingRule pricingRule = specials.getPricingRule(Catalogue.A);
        Assert.assertEquals(150, pricingRule.calculateSubTotal(3), 0);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionThrownWhenNoPricingRuleExistsForStockItem(){
        StockPricingRules pricingRules = new StockPricingRules();
        PricingRule pricingRule = pricingRules.getPricingRule(Catalogue.A);
        Assert.assertEquals(150, pricingRule.calculateSubTotal(3), 0);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionThrownWhenAddingSpecialForSameStockItemTwice(){
        StockPricingRules specials = new StockPricingRules();
        specials.add(PricingRuleFactory.Instance.createUnderlyingPrice(Catalogue.A, 20));
        specials.add(PricingRuleFactory.Instance.createUnderlyingPrice(Catalogue.A, 20));
    }

    @Test
    public void testDefaultPricingRule(){
        DefaultPricingRule defaultPricingRule = new DefaultPricingRule(Catalogue.B, 20);
        double result = defaultPricingRule.calculateSubTotal(2);
        Assert.assertEquals(40, result, 0);
    }

    @Test
    public void testDefaultPricingRuleCalculatesWrappedInSpecial(){
        PricingRule defaultPricingRule = new DefaultPricingRule(Catalogue.B, 30);
        SpecialDecorator special = new DiscountSpecial(defaultPricingRule, 2, 15);
        double result = special.calculateSubTotal(2);
        Assert.assertEquals(45, result, 0);
    }
}
