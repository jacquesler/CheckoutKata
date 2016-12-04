package main;

import basket.Basket;
import pricingRule.StockPricingRules;
import stock.Catalogue;
import stock.StockItem;

import java.io.Console;
import java.util.Scanner;

public class BasketConsoleUI {

    private static final String EXIT_COMMAND = "EXIT";
    private static final String CHECKOUT_COMMAND = "CHECKOUT";
    private Console console;
    private StockPricingRules specials;
    private Catalogue catalogue;

    public BasketConsoleUI(Catalogue catalogue, StockPricingRules specials){
        this.catalogue = catalogue;
        console = getConsole();
        this.specials = specials;
    }

    private static Console getConsole(){
        return System.console();
    }

    public void renderUI(){
        int transactionNumber = 1;
        Basket basket = Basket.startTransaction(specials);
        renderStartTransaction(transactionNumber);

        try (Scanner scanInput = new Scanner(System.in)) {
            while (true) {

                output("Scan Item [A,B,C,D] or [Checkout]: ");
                String input = scanInput.nextLine();

                if (shutdown(input)) {
                    output("Exit!");
                    break;
                }

                if(checkOut(input)){
                    renderCheckout(basket);
                    basket = Basket.startTransaction(specials);
                    renderStartTransaction(++transactionNumber);
                    continue;
                }

                try {
                    basket.scan(getStockItem(input));
                    output(String.format("%s scanned", input));
                }
                catch (Exception ex){
                    renderError(ex);
                }
            }
        }
    }

    private void output(String output){
        if(console == null)
            System.out.println(output);
        else{
            console.printf(output);
            console.printf("\r\n");
        }
    }

    private boolean shutdown(String input) {
        return input.length() == EXIT_COMMAND.length() && input.toUpperCase().equals(EXIT_COMMAND);
    }

    private boolean checkOut(String input){
        return input.length() == CHECKOUT_COMMAND.length() && input.toUpperCase().equals(CHECKOUT_COMMAND);
    }

    private void renderCheckout(Basket basket){
        output("===============================");
        output(String.format("Total : %s", basket.checkOut()));
        output("===============================");
        output("");
    }

    private void renderStartTransaction(int transactionNumber){
        output(String.format("Start transaction %s", transactionNumber));
    }

    private StockItem getStockItem(String input){
        return catalogue.getStockItem(input);
    }

    private void renderError(Exception e) {
        output("The following error happened");
        output(e.getMessage());
        output("===============================");
    }
}
