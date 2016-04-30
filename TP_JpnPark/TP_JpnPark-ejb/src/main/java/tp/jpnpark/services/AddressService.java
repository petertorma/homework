/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp.jpnpark.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import tp.jpnpark.entities.Address;
import tp.jpnpark.entities.Park;
import tp.jpnpark.facade.EntityFacade;

/**
 *
 * @author Torma Péter
 */
@Stateless
public class AddressService {

    @Inject
    private EntityFacade entityManager;
    @Inject
    private ParkService parkService;

    public Address create(Address address) {
        return entityManager.create(address);
    }

    public Address find(long addressId) {
        return entityManager.find(Address.class, addressId);
    }

    public Address update(long addressId, Address address) {
        checkAddress(addressId);
        address.setId(addressId);
        entityManager.update(address.getId());

        for (Park park : entityManager.findAll(Park.class)) {
            if (park.getId().equals(address.getId())) {
                park.setAdress(address);
                entityManager.update(park);
            }
        }
        return address;
    }

    public void delete(long addressId) {
        checkAddress(addressId);
        Address address = entityManager.find(Address.class, addressId);
        for (Park park : entityManager.findAll(Park.class)) {
            if (park.getAdress().getId().equals(address.getId())) {
                parkService.Delete(park.getId());
            }
        }
        entityManager.delete(addressId);
    }

    public void checkAddress(long addressId) {
        if (entityManager.find(Address.class, addressId) == null) {
            throw new RuntimeException("there is nod address with this id");
        }
    }
}
