package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(path = "/api/customer/{id}", method = RequestMethod.GET)
    public Customer find(@PathVariable("id") String id) {
        return customerRepository.findOne(id);
    }

    @RequestMapping(path = "/api/customer", method = RequestMethod.GET)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @RequestMapping(path = "/api/customer", method = RequestMethod.POST)
    public void add(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    // NOT CONCURRENT SAFE
    @RequestMapping(path = "/api/customer/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") String id, @RequestBody Customer customer){
        Customer current = customerRepository.findOne(id);
        current.setFirstName(customer.getFirstName());
        current.setLastName(customer.getLastName());
        customerRepository.save(current);
    }

    // CONCURRENT SAFE
    @RequestMapping(path = "/api2/customer/{id}", method = RequestMethod.PUT)
    public void update2(@PathVariable("id") String id, @RequestBody Customer customer){
        customerRepository.update(id, customer);
    }

    @RequestMapping(path = "/api/customer", method = RequestMethod.DELETE)
    public void delete(String id) {
        customerRepository.delete(id);
    }
}
