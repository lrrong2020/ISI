package com.example.restservice.Dao;

import com.example.restservice.models.PurchaseOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseOrderDaoImpl implements Dao<PurchaseOrder> {

	private HashMap<Integer, PurchaseOrder> PurchaseOrders = null;

	public PurchaseOrderDaoImpl() {
		PurchaseOrders = new HashMap<Integer, PurchaseOrder>();
		PurchaseOrders.put(1000, new PurchaseOrder());
		PurchaseOrders.put(1001, new PurchaseOrder());
	}

	/* methods */

	private Integer generateKey() {
		return 0;
	}

	/* setters and getters */
	@Override
	public PurchaseOrder get(Integer PurchaseOrderNumber) {
		return PurchaseOrders.get(PurchaseOrderNumber);
	}

	@Override
	public List<PurchaseOrder> getAll() {
		List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
		for (Map.Entry<Integer, PurchaseOrder> entry : PurchaseOrders.entrySet()) {
			purchaseOrderList.add(entry.getValue());
		}

		return purchaseOrderList;
	}

	@Override
	public void save(PurchaseOrder purchaseOrder) {
		Integer fakeAutoGenerateId = this.generateKey();
		PurchaseOrders.put(fakeAutoGenerateId, purchaseOrder);
	}

	@Override
	public void update(PurchaseOrder purchaseOrder, String[] params) {
		PurchaseOrders.put(purchaseOrder.getPurchaseOrderNumber(), purchaseOrder);
	}

	@Override
	public void delete(PurchaseOrder purchaseOrder) {
		PurchaseOrders.remove(purchaseOrder.getPurchaseOrderNumber());
	}

}
