package hu.codingmentor.services;

import hu.codingmentor.annotations.exceptions.BadRequestException;
import hu.codingmentor.dto.MobileDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class InventoryService {

    List<MobileDTO> mobiles = new ArrayList<>();

    public InventoryService() {
    }

    @PostConstruct
    public void init() {
        mobiles.add(new MobileDTO("e250", "NOKIA", 10000, 3));
        mobiles.add(new MobileDTO("h550", "SAMSUNG", 20000, 12));
        mobiles.add(new MobileDTO("t430", "APPLE", 5000, 5));
        mobiles.add(new MobileDTO("k100", "LG", 30000, 0));
    }

    @Lock(LockType.READ)
    public Collection<MobileDTO> getMobileList() {
        return mobiles;
    }

    @Lock(LockType.WRITE)
    public MobileDTO addMobile(MobileDTO mobile) {
        for (MobileDTO mob : mobiles) {
            if (mob.equals(mobile)) {
                throw new BadRequestException("This phone is already exists");
            }
        }
        mobiles.add(mobile);
        return mobile;
    }

    @Lock(LockType.WRITE)
    public MobileDTO buyMobile(MobileDTO mobile) {
        for (MobileDTO mob : mobiles) {
            if (mobile.getId().equals(mob.getId())) {
                if (mob.getPiece() > 0) {
                    mob.setPiece(mob.getPiece() - 1);
                    return mob;
                } else {
                    throw new BadRequestException("there is no more mobile : " + mob.getType() + " : " + mob.getManufacturer());
                    // todo create an exception!!
                }
            }
        }
        throw new BadRequestException("there is no such product");
    }

}
