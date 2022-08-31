package ru.azor.mri_constructor.services;

import ru.azor.mri_constructor.entities.MRIStudy;

import java.sql.*;

public class DBService {
    private static final String DB_URL = "jdbc:sqlite:data.db";
    private static final String ADD_NEW_STUDY_REQUEST = "INSERT INTO studies(full_name, date_of_birth, scoliosis_type, bending_angle, apex_of_the_bend) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE_STUDY_REQUEST = "UPDATE studies SET full_name = ?, date_of_birth = ?, scoliosis_type = ?, bending_angle = ?, apex_of_the_bend = ? WHERE id = ?";
    private static final String ADD_SCOLIOSIS_TYPE_REQUEST = "UPDATE studies SET scoliosis_type = ? WHERE id = ?";
    private static final String ADD_BENDING_ANGLE_REQUEST = "UPDATE studies SET bending_angle = ? WHERE id = ?";
    private static final String ADD_APEX_OF_THE_BEND_REQUEST = "UPDATE studies SET apex_of_the_bend = ? WHERE id = ?";
    private static final String GET_STUDY_REQUEST = "SELECT * FROM studies WHERE id = ?";
    private static final String REMOVE_STUDY_REQUEST = "DELETE FROM studies WHERE id = ?";
    private Connection connection;
    private PreparedStatement addNewStudyStatement;
    private PreparedStatement updateStudyStatement;
    private PreparedStatement addScoliosisTypeStatement;
    private PreparedStatement addBendingAngleStatement;
    private PreparedStatement addApexOfTheBendStatement;
    private PreparedStatement getStudyStatement;
    private PreparedStatement removeStudyStatement;

    public DBService() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            addNewStudyStatement = connection.prepareStatement(ADD_NEW_STUDY_REQUEST, Statement.RETURN_GENERATED_KEYS);
            updateStudyStatement = connection.prepareStatement(UPDATE_STUDY_REQUEST);
            addScoliosisTypeStatement = connection.prepareStatement(ADD_SCOLIOSIS_TYPE_REQUEST);
            addBendingAngleStatement = connection.prepareStatement(ADD_BENDING_ANGLE_REQUEST);
            addApexOfTheBendStatement = connection.prepareStatement(ADD_APEX_OF_THE_BEND_REQUEST);
            getStudyStatement = connection.prepareStatement(GET_STUDY_REQUEST);
            removeStudyStatement = connection.prepareStatement(REMOVE_STUDY_REQUEST);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public MRIStudy addNewStudy(MRIStudy study) {
        try {
            addNewStudyStatement.setString(1, study.getFullName());
            addNewStudyStatement.setString(2, study.getDateOfBirth());
            addNewStudyStatement.setString(3, study.getScoliosisType() == null ? null : study.getScoliosisType().name());
            addNewStudyStatement.setDouble(4, study.getBendingAngle());
            addNewStudyStatement.setInt(5, study.getApexOfTheBend());
            boolean isCreated = addNewStudyStatement.execute();

            if (! isCreated) {
                throw new SQLException("Creating study failed");
            }

            try (ResultSet generatedKeys = addNewStudyStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    study.setId(generatedKeys.getInt("id"));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return study;
    }

    public void updateStudy(MRIStudy study) {
        try {
            updateStudyStatement.setString(1, study.getFullName());
            updateStudyStatement.setString(2, study.getDateOfBirth());
            updateStudyStatement.setString(3, study.getScoliosisType().name());
            updateStudyStatement.setDouble(4, study.getBendingAngle());
            updateStudyStatement.setInt(5, study.getApexOfTheBend());
            updateStudyStatement.setInt(6, study.getId());
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void addScoliosisType(String scoliosisType, int id) {
        try {
            addScoliosisTypeStatement.setString(1, scoliosisType);
            addScoliosisTypeStatement.setInt(2, id);
            addScoliosisTypeStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void addBendingAngle(double bendingAngle, int id) {
        try {
            addBendingAngleStatement.setDouble(1, bendingAngle);
            addBendingAngleStatement.setInt(2, id);
            addBendingAngleStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void addApexOfTheBend(int apexOfTheBend, int id) {
        try {
            addApexOfTheBendStatement.setInt(1, apexOfTheBend);
            addApexOfTheBendStatement.setInt(2, id);
            addApexOfTheBendStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void removeStudy(int id) {
        try {
            removeStudyStatement.setInt(1, id);
            removeStudyStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public MRIStudy getStudy(int id) {
        MRIStudy study = new MRIStudy();
        try {
            getStudyStatement.setInt(1, id);
            ResultSet resultSet = getStudyStatement.executeQuery();
            while (resultSet.next()) {
                study.setId(resultSet.getInt("id"));
                study.setFullName(resultSet.getString("full_name"));
                study.setFullName(resultSet.getString("date_of_birth"));
                study.setFullName(resultSet.getString("scoliosis_type"));
                study.setBendingAngle(resultSet.getDouble("bending_angle"));
                study.setApexOfTheBend(resultSet.getInt("apex_of_the_bend"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return study;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}

