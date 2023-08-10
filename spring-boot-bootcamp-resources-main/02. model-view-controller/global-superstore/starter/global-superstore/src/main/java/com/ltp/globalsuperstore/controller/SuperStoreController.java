package com.ltp.globalsuperstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.service.SuperStoreService;

@Controller
public class SuperStoreController {

  SuperStoreService superStoreService;

  public SuperStoreController(SuperStoreService superStoreService) {
    this.superStoreService = superStoreService;
  }

  @GetMapping("/")
  public String getForm(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("item", superStoreService.getItem(id));
    return "form";
  }

  @GetMapping("/inventory")
  public String getInventory(Model model) {
    model.addAttribute("inventories", superStoreService.getItems());
    return "inventory";
  }

  @PostMapping("/submitItem")
  public String submitInventory(
      @Valid Item item,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (item.getPrice() < item.getDiscount()) {
      bindingResult.rejectValue(
          "price", "", "Price cannot be less than discount");
    }

    if (bindingResult.hasErrors())
      return "form";

    String status = superStoreService.submitItem(item);

    redirectAttributes.addFlashAttribute("status", status);
    return "redirect:/inventory";
  }
}
