package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void update(String id, Customer customer) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);

        Update update = new Update();
        update.set("firstName", customer.getFirstName());
        update.set("lastName", customer.getLastName());

        mongoTemplate.findAndModify(query, update, Customer.class);
    }
}
