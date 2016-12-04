package pricingRule;

import stock.StockItem;

public class DefaultPricingRule extends PricingRule {

    private double price;

    public DefaultPricingRule(StockItem stockItem, double price) {
        super(stockItem);
        this.price = price;
    }

    @Override
    public double calculateSubTotal(int amount)
    {
        return price * amount;
    }
}
