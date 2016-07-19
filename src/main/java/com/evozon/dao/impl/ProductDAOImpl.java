package com.evozon.dao.impl;

import com.evozon.dao.ProductDAO;
import com.evozon.domain.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //@Autowired
   // private List<Product> sortedProducts;

    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Product as P").list();
    }


    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    public void deleteProduct(int product_id) {
        Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("DELETE FROM Product WHERE productId=:id");
        query.setParameter("id", product_id);
        query.executeUpdate();
    }

    public void importFromFile(String filename) {
        Session session=sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("LOAD DATA LOCAL INFILE :filename INTO TABLE product FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (code, description, name, price, stockLevel)").setString("filename", filename);
        query.executeUpdate();
    }

    public List<Product> getProductsForPage(int startPageIndex, int recordsPerPage){
        int infRange = ((startPageIndex-1 )*recordsPerPage);
        int supRange = recordsPerPage ;

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product as P");

        query.setFirstResult(infRange);
        query.setMaxResults(supRange);
        List<Product> products = query.list();

        return products;
    }

    public Product getProductById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Product as P WHERE P.productId = :id");
        query.setParameter("id", id);
        List<Product> products = query.list();
        return products.get(0);
    }

    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    public List<Product> getSortedProducts(String queryCommand) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryCommand);
        List<Product> products = query.list();
        return products;

    }
}
