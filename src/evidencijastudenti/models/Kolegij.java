/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.models;

import static evidencijastudenti.models.User.resultSetToUser;
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
public class Kolegij {
    private long ID;
    private String Naziv;
    private long PredavacID;

    public Kolegij(String Naziv, long predavacID) {
        this.ID = 0;
        this.Naziv = Naziv;
        this.PredavacID = predavacID;
    }
    
    public Kolegij() {
        this.ID = 0;
        this.Naziv = "";
        this.PredavacID = 0;
    }
    
    
    
    public static Kolegij resultSetToKolegij(ResultSet rs){
        Kolegij kolegij = null;
        try {
            kolegij = new Kolegij(rs.getString("Naziv"), rs.getLong("PredavacID"));
            kolegij.setID(rs.getLong("ID"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kolegij;
    }
    
    public static ArrayList<Kolegij> getAll(){
        ArrayList<Kolegij> kolegiji = new ArrayList<>();
        List<Object> params = Arrays.asList();
        DB db = new DB();
        db.select("SELECT * FROM kolegij", params);
        
        try {
            while(db.getResultSet().next()){
                kolegiji.add(resultSetToKolegij(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return kolegiji;
    }
    
    public static Kolegij getById(long ID){
        Kolegij k = null;
        DB db = new DB();
        List<Object> params = Arrays.asList(ID);
        db.select("SELECT * FROM kolegij WHERE ID=?", params);
        try {
            while(db.getResultSet().next()){
                k = resultSetToKolegij(db.getResultSet());
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return k;
    }
    
    public static ArrayList<Kolegij> getProfesorMojiKolegiji(long predavacID){
        ArrayList<Kolegij> kolegiji = new ArrayList<>();
        List<Object> params = Arrays.asList(predavacID);
        DB db = new DB();
        db.select("SELECT * FROM kolegij WHERE PredavacID=?", params);
        
        try {
            while(db.getResultSet().next()){
                kolegiji.add(resultSetToKolegij(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return kolegiji;
    }
    
    public static ArrayList<Kolegij> getStudentMojiKolegiji(long studentID){
        ArrayList<Kolegij> kolegiji = new ArrayList<>();
        List<Object> params = Arrays.asList(studentID);
        DB db = new DB();
        db.select("SELECT DISTINCT * FROM kolegij WHERE ID IN (SELECT KolegijID FROM kolegijsudionik WHERE StudentID=?)", params);
        
        try {
            while(db.getResultSet().next()){
                kolegiji.add(resultSetToKolegij(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return kolegiji;
    }
    
    /*
    public static ArrayList<Kolegij> getStudentMojiKolegiji(long predavacID){
        ArrayList<Kolegij> kolegiji = new ArrayList<>();
        List<Object> params = Arrays.asList(predavacID);
        DB db = new DB();
        db.select("SELECT * FROM kolegij WHERE PredavacID=?", params);
        
        try {
            while(db.getResultSet().next()){
                kolegiji.add(resultSetToKolegij(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return kolegiji;
    }
    */
    
    public void delete(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.ID);
        db.delete("DELETE FROM ocjena WHERE KolegijID=?", params);
        db.delete("DELETE FROM kolegijsudionik WHERE KolegijID=?", params);
        db.delete("DELETE FROM kolegij WHERE ID=?", params);
        db.disconnect();
    }
    
    public void insert(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.Naziv, this.PredavacID);
        db.insert("INSERT INTO kolegij(Naziv, PredavacID) VALUES(?,?)", params);
        db.disconnect();
    }
    
    public void update(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.Naziv, this.PredavacID, this.ID);
        db.update("UPDATE kolegij SET Naziv=?, PredavacID=? WHERE ID=?", params);
        db.disconnect();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
    }

    public long getPredavacID() {
        return PredavacID;
    }

    public void setPredavacID(long PredavacID) {
        this.PredavacID = PredavacID;
    }
    
    public String getPredavac(){
        User user = User.getById(this.PredavacID);
        return user != null ? user.getIme() + " " + user.getPrezime() : "";
    }
    

}
