# Project Scope

## Functional Requirements

* (C1) The customer adds a product to his/her shopping cart by clicking a button in the product detail page. The quantity to buy is assumed to be 1. The items in  shopping cart are persisted across user sessions. Next time the customer logs in,  they can still see the items in the shopping cart.

* (C2) The customer can list the products in his/her shopping cart in a shopping  cart page. In this page, the entry for each product shows the product name, price and the quantity to buy. The page also shows the total order amount (i.e. how  much the customer has to pay in total) in the shopping cart. The customer can  click an item in the shopping cart to go to the product detail page of the entry.

* (C3) The customer can press a button in the shopping cart page to check out all  items in the shopping cart. This action creates a purchase order with a newly  allocated unique P.O. number, and clears the content of the cart. After checkout,  the system shows the purchase order detail page of the newly created purchase  order. (refer to requirement D3).

* (C5) The customer can remove an item from the shopping cart.

* (D1) The purchase tracking page lists the purchase orders that the customer  has placed. This page shows the following for each purchase order: the P.O.  number, the purchase date, the total order amount and the purchase order  status. The purchase orders are displayed in reverse chronological order of  purchase date. When the customer clicks an entry in the list, they can see the  detail in a purchase order detail page.

* (D2) The customer can filter the list of purchase orders in two ways. First, the  page only shows ‘current purchases’ with status ‘pending’ and ‘hold’. Second, the  page only shows ‘past purchases’ with status ‘shipped’ and ‘cancelled’.

* (D3) The purchase order detail page shows the P.O. number, the purchase date,  the customer name, the shipping address, the total order amount and the purchase order status. If the order is shipped, this page shows the shipment  date. If the order is cancelled, the page shows the order cancel date and who  (customer or vendor) cancelled the order. The page also includes, for each  product in the purchase order, the product name, the quantity, the unit price and  the subtotal.

  



# Plan Work

## WBS

### Initiating

* Collect Requirement from a number of end users

  

### Design

* Design the detail webpage for each product

* Design the shopping cart

* Design the purchase tracking webpage

* Design the detail webpage for the purchase order



### Implemetion

* code the shopping cart

* code product detail pages

* code purchase tracking pages

* code purchase order detail pages 



