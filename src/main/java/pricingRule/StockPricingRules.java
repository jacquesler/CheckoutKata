package pricingRule;

import stock.StockItem;
import validation.Validate;

import java.util.HashMap;
import java.util.Map;

public final class StockPricingRules {

    private final Map<StockItem, PricingRule> pricingRules;

    public StockPricingRules() {
        this.pricingRules = new HashMap<>();
    }

    /**
     * Add pricing rules for each item in the catalogue that needs prices to be calculated on
     *
     * @param pricingRule
     */
    public void add(PricingRule pricingRule) {
        Validate.when(pricingRules.containsKey(pricingRule.getStockItem()))
                .throwElementDoesNotExistException(String.format("A special for %s has already been added",
                        pricingRule.getStockItem().getSkuCode()));

        pricingRules.put(pricingRule.getStockItem(), pricingRule);
    }

    /**
     * Return a pricing rule for a given stock item
     *
     * @param item
     */
    public PricingRule getPricingRule(StockItem item) {
        Validate.whenNot(pricingRules.containsKey(item))
                .throwElementDoesNotExistException(String.format("%s has no pricing rule associated with it",
                        item.getSkuCode()));

        return pricingRules.get(item);
    }
}
