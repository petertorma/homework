package hu.codingmentor.services;

import hu.codingmentor.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public enum InventoryService {
    INSTANCE;

    List<MobileDTO> mobiles = new ArrayList<>();

    @PostConstruct
    public void init() {
        mobiles.add(new MobileDTO("e250", "NOKIA", 10000, 3));
        mobiles.add(new MobileDTO("h550", "SAMSUNG", 20000, 12));
        mobiles.add(new MobileDTO("t430", "APPLE", 5000, 5));
        mobiles.add(new MobileDTO("k100", "LG", 30000, 0));
    }

    public List<MobileDTO> getMobileList() {
        return mobiles;
    }

    public MobileDTO addMobile(MobileDTO mobile) {
        mobiles.add(mobile);
        return mobile;
    }

    public Integer buyMobile(MobileDTO mobile) {
        mobiles.remove(mobile);
        return mobiles.size();
    }

}
