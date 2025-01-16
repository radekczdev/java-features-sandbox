package dev.modulith.inventory;

import dev.modulith.order.OrderManagement;
import dev.modulith.order.api.Api;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagement {
  //  InternalExposed internalExposed;
  OrderManagement orderManagement;
  Api api;
}
