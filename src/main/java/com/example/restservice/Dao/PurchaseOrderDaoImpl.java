package com.example.restservice.Dao;

import com.example.restservice.models.PurchaseOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseOrderDaoImpl implements Dao<PurchaseOrder> {

	private HashMap<String, PurchaseOrder> PurchaseOrders = null;

	public PurchaseOrderDaoImpl() {
		PurchaseOrders = new HashMap<String, PurchaseOrder>();
		PurchaseOrders.put("PO01", new PurchaseOrder());
		PurchaseOrders.put("PO02", new PurchaseOrder());
	}

	/* methods */

	private String generateKey() {
		return "fakeKey";
	}

	/* setters and getters */
	@Override
	public PurchaseOrder get(String PurchaseOrderNumber) {
		return PurchaseOrders.get(PurchaseOrderNumber);
	}

	@Override
	public List<PurchaseOrder> getAll() {
		List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();
		for (Map.Entry<String, PurchaseOrder> entry : PurchaseOrders.entrySet()) {
			purchaseOrderList.add(entry.getValue());
		}

		return purchaseOrderList;
	}

	@Override
	public void save(PurchaseOrder purchaseOrder) {
		String fakeAutoGenerateId = this.generateKey();
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
