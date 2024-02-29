package interfaces;

public interface Selectable {

    String CREATE_DATABASE = "CREATE DATABASE medical";
    String CREATE_REFERENCED_TABLE = "CREATE TABLE doctor " +
            "(id INTEGER PRIMARY KEY," +
            "first_name VARCHAR(30) NOT NULL," +
            "last_name VARCHAR(30)," +
            "email VARCHAR(40)," +
            "contact_number VARCHAR(15) UNIQUE," +
            "address VARCHAR(30)," +
            "active INTEGER DEFAULT 1" +
            ")";
    String CREATE_BASE_TABLE = "CREATE TABLE patient " +
            "(id INTEGER PRIMARY KEY," +
            "first_name VARCHAR(30) NOT NULL," +
            "last_name VARCHAR(30)," +
            "region VARCHAR(30)," +
            "doctor_id INTEGER ," +
            "active INTEGER DEFAULT 1," +
            "CONSTRAINT fk_patient_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctor(id)" +
            ")";

    String INSERT_REFERENCED_TABLE = "INSERT INTO doctor(id,first_name,last_name,email,contact_number," +
            "address,active) " +
            "values(?,?,?,?,?,?,?)";
    String INSERT_BASE_TABLE = "INSERT INTO patient(id,first_name,last_name,region,doctor_id,active) values(?,?,?,?,?,?)";

    String UPDATE_REFERENCED_TABLE = "UPDATE doctor SET first_name=LOWER(first_name) WHERE id>?";
    String UPDATE_BASE_TABLE = "UPDATE doctor SET doctor_id=? WHERE id<? and first_name like 'A%'";

    String SELECT_ALL_DOCTOR = "SELECT * FROM doctor";
    String SELECT_ALL_PATIENT = "SELECT * FROM patient";

    String QUERY = "SELECT first_name,last_name FROM doctor " +
            "WHERE id IN (" +
            "SELECT p.doctor_id FROM patient p JOIN doctor d ON p.doctor_id=d.id " +
            "GROUP BY p.doctor_id  " +
            "having count(p.id)>?) " +
            "ORDER BY first_name DESC";
}
