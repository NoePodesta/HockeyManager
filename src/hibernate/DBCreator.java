package hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class DBCreator{
    
	public static void createDatabase() {
    	Configuration c = new AnnotationConfiguration().configure();
        SchemaExport se = new SchemaExport(c);
        se.setOutputFile("resources_schema_export.sql");
        se.create(true, true);     
    }
	
    public static void main(String[] args) {
    	createDatabase();
 
    }
 
}

    


