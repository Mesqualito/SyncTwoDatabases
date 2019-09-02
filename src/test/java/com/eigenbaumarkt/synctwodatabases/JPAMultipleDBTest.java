package com.eigenbaumarkt.synctwodatabases;

import com.eigenbaumarkt.synctwodatabases.config.PersistenceProductAutoConfiguration;
import com.eigenbaumarkt.synctwodatabases.config.PersistenceUserAutoConfiguration;
import com.eigenbaumarkt.synctwodatabases.dao.product.ProductRepository;
import com.eigenbaumarkt.synctwodatabases.dao.user.UserRepository;
import com.eigenbaumarkt.synctwodatabases.model.product.Product;
import com.eigenbaumarkt.synctwodatabases.model.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceUserAutoConfiguration.class, PersistenceProductAutoConfiguration.class})
public class JPAMultipleDBTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {

        User user = new User();
        user.setName("Jochen");
        user.setEmail("it@generica.net");
        user.setAge(43);
        user = userRepository.save(user);

        assertNotNull(userRepository.findById(user.getId()));
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUserWithSameEmail_thenRollback() {

        User user1 = new User();
        user1.setName("Test2");
        user1.setEmail("test2@abc.de");
        user1.setAge(21);
        user1 = userRepository.save(user1);
        assertNotNull(userRepository.findById(user1.getId()));

        User user2 = new User();
        user2.setEmail("Test3");
        user2.setEmail("test2@abc.de");
        user2.setAge(51);
        try {
            user2 = userRepository.save(user2);
        } catch (DataIntegrityViolationException e) {

        }

        assertNull(userRepository.findById(user2.getId()));
    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {

        Product product = new Product();
        product.setName("Umreifungskopf SSH 32");
        product.setId(2);
        product.setPrice(23.000);
        product = productRepository.save(product);

        assertNotNull(productRepository.findById(product.getId()));
    }
}
