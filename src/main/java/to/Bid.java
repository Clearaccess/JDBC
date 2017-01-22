package to;

/**
 * Created by Aleksandr_Vaniukov on 1/17/2017.
 */
public class Bid implements Comparable<Bid> {
    private long bidId;
    private long bidderId;
    private long itemId;
    private double bid;

    public Bid() {
    }

    public Bid(long bidId, long bidderId, long itemId, double bid) {
        this.bidId = bidId;
        this.bidderId = bidderId;
        this.itemId = itemId;
        this.bid = bid;
    }

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }

    public long getBidderId() {
        return bidderId;
    }

    public void setBidderId(long bidderId) {
        this.bidderId = bidderId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid1 = (Bid) o;

        if (getBidId() != bid1.getBidId()) return false;
        if (getBidderId() != bid1.getBidderId()) return false;
        if (getItemId() != bid1.getItemId()) return false;
        return Double.compare(bid1.getBid(), getBid()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getBidId() ^ (getBidId() >>> 32));
        result = 31 * result + (int) (getBidderId() ^ (getBidderId() >>> 32));
        result = 31 * result + (int) (getItemId() ^ (getItemId() >>> 32));
        temp = Double.doubleToLongBits(getBid());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public int compareTo(Bid o) {
        if (this.getBid() == o.getBid()) {
            return 0;
        }
        if (this.getBid() > o.getBid()) {
            return 1;
        } else {
            return -1;
        }
    }
}
