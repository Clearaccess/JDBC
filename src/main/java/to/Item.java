package to;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class Item {
    private long itemId;
    private long sellerId;
    private String title;
    private String description;
    private double startPrice;
    private long timeLeft;
    private Date startBiddingDate;
    private boolean buyItNow;
    private double bidIncrement;

    public Item() {
    }

    public Item(long itemId, long sellerId, String title, String description, double startPrice, long timeLeft, Date startBiddingDate, boolean buyItNow, double bidIncrement) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.timeLeft = timeLeft;
        this.startBiddingDate = startBiddingDate;
        this.buyItNow = buyItNow;
        this.bidIncrement = bidIncrement;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Date getStartBiddingDate() {
        return startBiddingDate;
    }

    public void setStartBiddingDate(Date startBiddingDate) {
        this.startBiddingDate = startBiddingDate;
    }

    public boolean isBuyItNow() {
        return buyItNow;
    }

    public void setBuyItNow(boolean buyItNow) {
        this.buyItNow = buyItNow;
    }

    public double getBidIncrement() {
        return bidIncrement;
    }

    public void setBidIncrement(double bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (getItemId() != item.getItemId()) return false;
        if (getSellerId() != item.getSellerId()) return false;
        if (Double.compare(item.getStartPrice(), getStartPrice()) != 0) return false;
        if (getTimeLeft() != item.getTimeLeft()) return false;
        if (isBuyItNow() != item.isBuyItNow()) return false;
        if (Double.compare(item.getBidIncrement(), getBidIncrement()) != 0) return false;
        if (!getTitle().equals(item.getTitle())) return false;
        if (!getDescription().equals(item.getDescription())) return false;
        return getStartBiddingDate().equals(item.getStartBiddingDate());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getItemId() ^ (getItemId() >>> 32));
        result = 31 * result + (int) (getSellerId() ^ (getSellerId() >>> 32));
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getDescription().hashCode();
        temp = Double.doubleToLongBits(getStartPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (getTimeLeft() ^ (getTimeLeft() >>> 32));
        result = 31 * result + getStartBiddingDate().hashCode();
        result = 31 * result + (isBuyItNow() ? 1 : 0);
        temp = Double.doubleToLongBits(getBidIncrement());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
