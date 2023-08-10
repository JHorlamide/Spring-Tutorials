package com.ltp.globalsuperstore.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repository.SuperStoreRepository;

@Service
public class SuperStoreService {

  @Autowired
  SuperStoreRepository superStoreRepository;

  public List<Item> getItems() {
    return superStoreRepository.getItems();
  }

  public Item getItem(String id) {
    int index = getItemIndex(id);
    return notFound(id) ? new Item() : superStoreRepository.getItem(index);
  }

  public String submitItem(Item item) {
    String itemId = item.getId();
    int index = getItemIndex(itemId);

    String status = Constants.SUCCESS_STATUS;

    if (notFound(itemId)) {
      superStoreRepository.addItems(item);
    } else if (within5Days(item.getOrderDate(), superStoreRepository.getItem(index).getOrderDate())) {
      superStoreRepository.updateItem(item, index);
    } else {
      status = Constants.FAILED_STATUS;
    }

    return status;
  }

  private Integer getItemIndex(String id) {
    var inventoryList = superStoreRepository.getItems();

    for (int i = 0; i < inventoryList.size(); i++) {
      if (inventoryList.get(i).getId().equals(id))
        return i;
    }

    return Constants.NOT_FOUND;
  }

  private boolean notFound(String id) {
    return getItemIndex(id) == Constants.NOT_FOUND;
  }

  private boolean within5Days(Date newDate, Date oldDate) {
    long dateDiff = Math.abs(newDate.getTime() - oldDate.getTime());
    return (int) (TimeUnit.MILLISECONDS.toDays(dateDiff)) <= 5;
  }
}
