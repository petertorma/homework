package hu.codingmentor.services;

import hu.codingmentor.dto.MobileDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Stateful
@SessionScoped
public class CartService {

    public CartService() {
    }

    @Inject
    InventoryService inventoryService;

    @Resource
    SessionContext context;

    private final List<MobileDTO> products = new ArrayList<>();

    public List<MobileDTO> addToCart(MobileDTO product) {
        products.add(product);
        return products;
    }

    public List<MobileDTO> itemsInCart() {
        return products;
    }

    
    public void checkout() {
        for (MobileDTO product : products) {
           inventoryService.buyMobile(product);
        }
        products.clear();
    }
}
