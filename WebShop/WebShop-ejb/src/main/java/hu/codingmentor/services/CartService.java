package hu.codingmentor.services;


import hu.codingmentor.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;


@Stateful
@StatefulTimeout(value = 300, unit = TimeUnit.SECONDS)
public class CartService {

    public CartService() {
    }

    @Resource
    SessionContext context;
    
    private final List<MobileDTO> products = new ArrayList<>();

    public Integer addProduct(MobileDTO product) {
        products.add(product);
        return products.size();
    }

    @Remove
    public void checkout() {
        products.clear();
    }

}
