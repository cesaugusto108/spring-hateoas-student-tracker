package augusto108.ces.studenttracker.repositories;

import augusto108.ces.studenttracker.entities.Address;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    private final SessionFactory sessionFactory;

    public AddressRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Address getAddress(Long id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Address a where id = :id", Address.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Address> getAddresses() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Address order by city", Address.class)
                .getResultList();
    }

    @Override
    public Address saveAddress(Address address) {
        sessionFactory
                .getCurrentSession()
                .save(address);

        return address;
    }

    @Override
    public Address updateAddress(Address address) {
        sessionFactory
                .getCurrentSession()
                .update(address);

        return address;
    }

    @Override
    public void deleteAddress(Address address) {
        sessionFactory
                .getCurrentSession()
                .delete(address);
    }

    @Override
    public List<Address> searchAddresses(String search) {
        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "from Address where lower(street) like :search" +
                                " or lower(number) like :search" +
                                " or lower(city) like :search",
                        Address.class
                )
                .setParameter("search", "%" + search.toLowerCase().trim() + "%")
                .getResultList();
    }
}
