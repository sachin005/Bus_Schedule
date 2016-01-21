package s.chavan.busschedule;

/**
 * Created by Sachin on 8/19/2015.
 */
public class Bus {
    private int number;
    private int routegrp;
    private String inbound;
    private String outbound;

    public Bus(int n, int r, String in, String out) {
        number = n;
        routegrp = r;
        inbound = in;
        outbound = out;
    }

    public String getOutbound() {
        return outbound;
    }

    public int getRoutegrp() {
        return routegrp;
    }

    public int getNumber() {
        return number;
    }

    public String getInbound() {
        return inbound;
    }
}
