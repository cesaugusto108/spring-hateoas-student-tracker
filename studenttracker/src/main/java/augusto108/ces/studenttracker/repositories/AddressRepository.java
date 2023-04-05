package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.Address;

import java.util.List;

public interface AddressRepository {
    Address getAddress(Long id);

    List<Address> getAddresses(int pageValue, int maxResults);

    Address saveAddress(Address address);

    Address updateAddress(Address address);

    void deleteAddress(Address address);

    List<Address> searchAddresses(String search);
}
