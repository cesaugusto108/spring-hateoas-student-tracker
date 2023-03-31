package augusto108.ces.studenttracker.services;

import augusto108.ces.studenttracker.entities.Address;
import augusto108.ces.studenttracker.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getAddress(Long id) {
        return addressRepository.getAddress(id);
    }

    @Override
    public List<Address> getAddresses() {
        return addressRepository.getAddresses();
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.saveAddress(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.updateAddress(address);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.deleteAddress(address);
    }

    @Override
    public List<Address> searchAddresses(String search) {
        return addressRepository.searchAddresses(search);
    }
}
