package operation;

import interfaces.Selectable;
import util.Required;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDOperation {

    public void createTable() throws SQLException {

        try (Connection connection = Util.getSchemaConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(Selectable.CREATE_REFERENCED_TABLE);
            statement.executeUpdate(Selectable.CREATE_BASE_TABLE);
        }
    }

    public void insertDoctorTable() {

        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(Selectable.INSERT_REFERENCED_TABLE)) {

            int length1 = Required.requiredNumber("How many doctors will be added? ");
            for (int i = 0; i < length1; i++) {
                ps.setInt(1, Required.requiredNumber("id:"));
                ps.setString(2, Required.requiredText("first_name: "));
                ps.setString(3, Required.requiredText("last_name: "));
                ps.setString(4, Required.requiredText("email: "));
                ps.setString(5, Required.requiredText("contact number: "));
                ps.setString(6, Required.requiredText("address: "));
                ps.setInt(7, Required.requiredNumber("active: "));
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertPatientTable() {
        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps1 = connection.prepareStatement(Selectable.INSERT_BASE_TABLE)) {
            int length2 = Required.requiredNumber("How many patients will be added? ");
            for (int i = 0; i < length2; i++) {
                ps1.setInt(1, Required.requiredNumber("id:"));
                ps1.setString(2, Required.requiredText("first_name: "));
                ps1.setString(3, Required.requiredText("last_name: "));
                ps1.setString(4, Required.requiredText("region: "));
                ps1.setInt(5, Required.requiredNumber("doctor_id: "));
                ps1.setInt(6, Required.requiredNumber("active: "));
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDoctorTable() throws SQLException {

        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(Selectable.UPDATE_REFERENCED_TABLE)) {
            int doctorId = Required.requiredNumber("Which doctors will be updated? ");
            ps.setInt(1, doctorId);
            ps.executeUpdate();
        }

    }

    public void updatePatientTable() throws SQLException {

        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(Selectable.UPDATE_BASE_TABLE)) {
            int setNumber = Required.requiredNumber("Which doctor will be set? ");
            ps.setInt(1, setNumber);
            int patientId = Required.requiredNumber("Which patients will be updated?");
            ps.setInt(2, patientId);
            ps.executeUpdate();
        }

    }

    public List<String> showDoctors() throws SQLException {
        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(Selectable.SELECT_ALL_DOCTOR);
             ResultSet rs = ps.executeQuery()) {
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String str = rs.getInt("id") + " " + rs.getString("first_name") +
                        " " + rs.getString("last_name") + " " + rs.getString("email") +
                        " " + rs.getString("contact_number") + " " + rs.getString("address") +
                        " " + rs.getInt("active");
                if (list.isEmpty()) {
                    list.add(str);
                } else {
                    list.add("\n"+str);
                }
            }
            return list;
        }
    }

    public List<String> showPatients() throws SQLException {
        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(Selectable.SELECT_ALL_PATIENT);
             ResultSet rs = ps.executeQuery()) {
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String str = rs.getInt("id") + " " + rs.getString("first_name") +
                        " " + rs.getString("last_name") + " " + rs.getString("region") +
                        " " + rs.getInt("doctor_id") + " " + rs.getInt("active");
                if(list.isEmpty()){
                list.add(str);
                }
                else{
                    list.add("\n"+str);
                }
            }
            return list;
        }
    }

    public List<String> findDoctorByNumberOfPatient() throws SQLException {
        try (Connection connection = Util.getSchemaConnection();
             PreparedStatement ps = connection.prepareStatement(Selectable.QUERY)
        ) {
            int count = Required.requiredNumber("How many patients is treated by doctors?");
            ps.setInt(1, count);
            ResultSet rs = ps.executeQuery();
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String str = rs.getString("first_name") + " " + rs.getString("last_name");
                if(list.isEmpty()){
                list.add(str );}
                else{
                    list.add("\n"+str);
                }
            }
            return list;
        }
    }
}
