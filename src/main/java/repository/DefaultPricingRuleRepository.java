package repository;

import pricingRule.PricingRule;
import pricingRule.PricingRuleFactory;
import pricingRule.StockPricingRules;
import stock.Catalogue;

/**
 This class could read from some repository like a database, CSV file, rest service to build specials for each item in the catalogue
 Using dependency injection a new repository could be injected
 **/

public class DefaultPricingRuleRepository implements PricingRuleRepository{

    public StockPricingRules load(){
        PricingRuleFactory factory = PricingRuleFactory.Instance;

        PricingRule aPrice =  factory.createUnderlyingPrice(Catalogue.A, 50);
        PricingRule aPriceWithSpecial = factory.createDiscountSpecial(aPrice, 3, 20);

        PricingRule bPrice = factory.createUnderlyingPrice(Catalogue.B, 30);
        PricingRule bPriceWithSpecial = factory.createDiscountSpecial(bPrice, 2, 15);

        PricingRule cPrice = factory.createUnderlyingPrice(Catalogue.C, 20);
        PricingRule dPrice = factory.createUnderlyingPrice(Catalogue.D, 15);

        StockPricingRules pricingRules = new StockPricingRules();
        pricingRules.add(aPriceWithSpecial);
        pricingRules.add(bPriceWithSpecial);
        pricingRules.add(cPrice);
        pricingRules.add(dPrice);

        return pricingRules;
    }
}
