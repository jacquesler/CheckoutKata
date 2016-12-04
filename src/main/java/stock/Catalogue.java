package stock;
import validation.Validate;

import java.util.*;

public final class Catalogue {

    public final static StockItem A = new StockItem("A");
    public final static StockItem B = new StockItem("B");
    public final static StockItem C = new StockItem("C");
    public final static StockItem D = new StockItem("D");

    public Map<String, StockItem> stock = new HashMap<>();

    public Catalogue(){
        stock.put("A", A);
        stock.put("B", B);
        stock.put("C", C);
        stock.put("D", D);
    }

    public StockItem getStockItem(String input){
        String toUpper = input.toUpperCase();
        Validate.whenNot(stock.containsKey(toUpper))
                .throwElementDoesNotExistException(String.format("%s does not exist in the catalogue", input));
        return stock.get(toUpper);
    }
}
