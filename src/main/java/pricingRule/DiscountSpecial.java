package pricingRule;

public class DiscountSpecial extends SpecialDecorator {

    private int specialsThreshold;
    private double discount;

    public DiscountSpecial(PricingRule pricingRule, int specialsThreshold, double discount) {
        super(pricingRule);

        this.specialsThreshold = specialsThreshold;
        this.discount = discount;
    }

    @Override
    public double calculateSubTotal(int amount) {
        double total = pricingRule.calculateSubTotal(amount);
        total -= (amount/specialsThreshold)*discount;
        return total;
    }
}
