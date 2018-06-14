/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.models;

import static evidencijastudenti.models.Kolegij.resultSetToKolegij;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class KolegijSudionik {
    private long ID;
    private long StudentID;
    private long KolegijID;

    public KolegijSudionik(long StudentID, long KolegijID) {
        this.ID = 0;
        this.StudentID = StudentID;
        this.KolegijID = KolegijID;
    }
    
    public KolegijSudionik() {
        this.ID = 0;
        this.StudentID = 0;
        this.KolegijID = 0;
    }
    
    
    
    public static KolegijSudionik resultSetToKolegijSudionik(ResultSet rs){
        KolegijSudionik kolegijSudionik = null;
        try {
            kolegijSudionik = new KolegijSudionik(rs.getLong("StudentID"), rs.getLong("KolegijID"));
            kolegijSudionik.setID(rs.getLong("ID"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kolegijSudionik;
    }
    
    public static ArrayList<User> getAllForKolegij(long kolegijID){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList(kolegijID);
        DB db = new DB();
        db.select("SELECT * FROM user WHERE ID IN (SELECT StudentID FROM kolegijsudionik WHERE KolegijID=?)", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(User.resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static ArrayList<User> get(long StudentID, long KolegijID){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList(StudentID, KolegijID);
        DB db = new DB();
        db.select("SELECT * FROM kolegijsudionik WHERE StudentID=? AND KolegijID=?", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(User.resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public void delete(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.StudentID);
        db.delete("DELETE FROM ocjena WHERE StudentID=?", params);
        params = Arrays.asList(this.ID);
        db.delete("DELETE FROM kolegijsudionik WHERE ID=?", params);
        db.disconnect();
    }
    
    public static void delete(long StudentID, long KolegijID){
        DB db = new DB();
        List<Object> params = Arrays.asList(StudentID);
        db.delete("DELETE FROM ocjena WHERE StudentID=?", params);
        params = Arrays.asList(StudentID, KolegijID);
        db.delete("DELETE FROM kolegijsudionik WHERE StudentID=? AND KolegijID=?", params);
        db.disconnect();
    }
    
    public void insert(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.StudentID, this.KolegijID);
        db.insert("INSERT INTO kolegijsudionik(StudentID, KolegijID) VALUES(?,?)", params);
        db.disconnect();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getStudentID() {
        return StudentID;
    }

    public void setStudentID(long studentID) {
        this.StudentID = studentID;
    }

    public long getKolegijID() {
        return KolegijID;
    }

    public void setKolegijID(long KolegijID) {
        this.KolegijID = KolegijID;
    }
    
}
