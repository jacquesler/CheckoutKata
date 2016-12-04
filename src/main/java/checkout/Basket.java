package checkout;

import pricingRule.PricingRule;
import pricingRule.StockPricingRules;
import stock.StockItem;

import java.util.HashMap;
import java.util.Map;


public class Basket {

    private Map<StockItem, Integer> order;
    private StockPricingRules specials;

    public static Basket startTransaction(StockPricingRules specials){
        return new Basket(specials);
    }

    private Basket(StockPricingRules specials) {
        this.specials = specials;
        order = new HashMap<>();
    }

    /**
     * Stock items scanned and added to the basket
     *
     * @param stockItem which has been bought
     * @return the basket so more items can be scanned or checkout called
     */
    public Basket scan(StockItem stockItem) {
        order.merge(stockItem, 1, (integer, integer2) -> integer + integer2);
        return this;
    }

    /**
     * After finishing scanning items checkout is called to end the transaction
     *
     * @return The total of all scanned items
     */
    public double checkOut() {
        double total = 0;

        for (StockItem item : order.keySet()) {
            PricingRule pricingRule = specials.getPricingRule(item);
            total += pricingRule.calculateSubTotal(order.get(item));
        }
        return total;
    }
}
