package main;

import pricingRule.StockPricingRules;
import repository.CatalogueRepository;
import repository.DefaultCatalogueRepository;
import repository.DefaultPricingRuleRepository;
import repository.PricingRuleRepository;
import stock.Catalogue;

public class Main {

    public static void main(String[] args){
        CatalogueRepository catalogueRepository = new DefaultCatalogueRepository();
        Catalogue catalogue = catalogueRepository.load();

        PricingRuleRepository pricingRuleRepository = new DefaultPricingRuleRepository();
        StockPricingRules pricingRules = pricingRuleRepository.load();

        BasketConsoleUI basketUI = new BasketConsoleUI(catalogue, pricingRules);
        basketUI.renderUI();
    }
}
