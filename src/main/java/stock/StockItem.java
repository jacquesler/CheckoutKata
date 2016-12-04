package stock;

public final class StockItem {

    private final String skuCode;

    public StockItem(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "skuCode='" + skuCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockItem stockItem = (StockItem) o;

        return skuCode.equals(stockItem.skuCode);
    }

    @Override
    public int hashCode() {
        return skuCode.hashCode();
    }
}
