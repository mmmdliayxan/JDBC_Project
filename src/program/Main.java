package program;

import operation.CRUDOperation;
import operation.DBOperation;
import util.Required;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            DBOperation dbOperation = new DBOperation();
            dbOperation.createDatabase();
            CRUDOperation crudOperation = new CRUDOperation();
            crudOperation.createTable();
        } catch (Exception e) {
            CRUDOperation crudOperation = new CRUDOperation();
            while (true) {
                int number = Required.requiredNumber("1.Insert doctor table\n" +
                        "2.Insert patient table\n" +
                        "3.Update doctor table\n" +
                        "4.Update patient table\n" +
                        "5.Show all doctor\n" +
                        "6.Show all patient\n" +
                        "7.Run query\n" +
                        "0.Stop operation\n");
                switch (number) {
                    case 1 -> {
                        crudOperation.insertDoctorTable();
                        System.out.println("Inserting successfully completed");
                    }
                    case 2 -> {
                        crudOperation.insertPatientTable();
                        System.out.println("Inserting successfully completed");
                    }
                    case 3 -> {
                        crudOperation.updateDoctorTable();
                        System.out.println("Updating successfully completed");
                    }
                    case 4 -> {
                        crudOperation.updatePatientTable();
                        System.out.println("Updating successfully completed");
                    }
                    case 5 -> System.out.println(crudOperation.showDoctors());
                    case 6 -> System.out.println(crudOperation.showPatients());
                    case 7 -> System.out.println(crudOperation.findDoctorByNumberOfPatient());
                    default -> System.exit(0);
                }
                System.out.println();
            }
        }
    }
}