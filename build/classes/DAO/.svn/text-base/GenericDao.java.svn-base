package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class GenericDao {

    protected static <T> void persist(T t, Session currentSession) {
    	Transaction tx = currentSession.getTransaction();
        try {
            currentSession.saveOrUpdate(t);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
        	if (tx != null){
            	System.out.println("transaction rollback " + t);
            	tx.rollback();
            }
        }
    }

    protected static <T> void  delete(T t, Session currentSession) {
    	Transaction tx = currentSession.getTransaction();
        try {
            currentSession.delete(t);
            currentSession.getTransaction().commit();
        }
        catch (Exception e) {
        	e.printStackTrace();
            if (tx != null){
            	System.out.println("transaction rollback " + t);
            	tx.rollback();
            }
        }
    }
    

    
}