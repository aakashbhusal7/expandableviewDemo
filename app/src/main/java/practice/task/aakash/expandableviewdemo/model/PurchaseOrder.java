package practice.task.aakash.expandableviewdemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseOrder {
    @SerializedName("ORDER_NO")
    @Expose
    private String orderNumber;
    @SerializedName("ITEM_EDESC")
    @Expose
    private String itemName;
    @SerializedName("QUANTITY")
    @Expose
    private Float quantity;
    @SerializedName("UNIT_PRICE")
    @Expose
    private Float unitPrice;
    @SerializedName("NET_TOTAL")
    @Expose
    private Float netTotal;
    @SerializedName("MU_CODE")
    @Expose
    private String muCode;

    public PurchaseOrder(String orderNumber, String itemName, Float quantity, Float unitPrice, Float netTotal, String muCode) {
        this.orderNumber = orderNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.netTotal = netTotal;
        this.muCode = muCode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Float netTotal) {
        this.netTotal = netTotal;
    }

    public String getMuCode() {
        return muCode;
    }

    public void setMuCode(String muCode) {
        this.muCode = muCode;
    }
    @Override
    public String toString() {
        return "PurchaseOder{" +
                "orderNumber= '"+orderNumber+'\'' +
                "title='" + itemName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unit='" + unitPrice + '\'' +
                ", total='" + netTotal + '\'' +
                '}';
    }
}
