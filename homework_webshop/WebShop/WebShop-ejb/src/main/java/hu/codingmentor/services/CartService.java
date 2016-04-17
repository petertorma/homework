package hu.codingmentor.services;

import hu.codingmentor.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Stateful
@SessionScoped
public class CartService {

    @Inject
    private InventoryService inventoryService;

    private final List<MobileDTO> products = new ArrayList<>();

    public List<MobileDTO> addToCart(MobileDTO product) {
        products.add(product);
        return products;
    }

    public List<MobileDTO> itemsInCart() {
        return products;
    }

    public CartService() {
    }

    public void checkout() {
        products.stream().forEach((product) -> {
            inventoryService.buyMobile(product);
        });
        products.clear();
    }
}
