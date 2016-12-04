package repository;

import pricingRule.StockPricingRules;

public interface PricingRuleRepository {

    StockPricingRules load();
}
