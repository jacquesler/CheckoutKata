package pricingRule;

public abstract class SpecialDecorator extends PricingRule {

    protected PricingRule pricingRule;

    protected SpecialDecorator(PricingRule pricingRule)
    {
        super(pricingRule.getStockItem());
        this.pricingRule = pricingRule;
    }


}
