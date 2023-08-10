package com.ltp.globalsuperstore.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ltp.globalsuperstore.Item;

@Repository
public class SuperStoreRepository {
  List<Item> inventoryList = new ArrayList<>();

  public List<Item> getItems() {
    return inventoryList;
  }

  public Item getItem(int index) {
    return inventoryList.get(index);
  }

  public void addItems(Item item) {
    inventoryList.add(item);
  }

  public void updateItem(Item item, int index) {
    inventoryList.set(index, item);
  }
}
