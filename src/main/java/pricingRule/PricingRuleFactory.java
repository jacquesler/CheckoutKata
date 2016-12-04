package pricingRule;

import stock.StockItem;

public enum PricingRuleFactory
{
    Instance;

    /**
     *
     * @param stockItem
     * @param price of the underlying stock item
     * @return Pricing rule used to calculate the price of a stock item
     */
    public PricingRule createUnderlyingPrice(StockItem stockItem, double price){
        return new DefaultPricingRule(stockItem, price);
    }

    /**
     *
     * @param pricingRule
     * @param specialsThreshold the amount of items which need to be bought before special is activated
     * @param discount the discount of given for the special
     * @return pricing rule with a special
     */
    public PricingRule createDiscountSpecial(PricingRule pricingRule, int specialsThreshold, double discount){
        return new DiscountSpecial(pricingRule, specialsThreshold, discount);
    }
}
