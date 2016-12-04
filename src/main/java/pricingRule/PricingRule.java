package pricingRule;

import stock.StockItem;

public abstract class PricingRule {

    private StockItem stockItem;

    protected PricingRule(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    /**
     *
     * @return Stock item pricing rule is working on
     */
    public StockItem getStockItem() {
        return stockItem;
    }

    /**
     * Calculate the subtotal of a given stock item
     *
     * @param amount of items bought
     * @return subtotal of stock items bought
     */
    public abstract double calculateSubTotal(int amount);
}
