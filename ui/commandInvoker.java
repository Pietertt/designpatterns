import commands.order;

import java.util.ArrayList;
import java.util.List;

public class commandInvoker {
    private List<order> orderList = new ArrayList<order>();
    private List<order> historyList = new ArrayList<order>();

    public void addOrder(order order) {
        orderList.add(order);
    }

    public void takeOrders(List<order> orders) {
        orderList.addAll(orders);
    }

    public void placeOrders(){

        for (order order : orderList) {
            order.execute();
            historyList.add(order);
        }
        orderList.clear();
    }
}
