/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.models;

import static evidencijastudenti.models.Kolegij.resultSetToKolegij;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class Ocjena {
    private long ID;
    private int Value;
    private String Opis;
    private Date Datum;
    private long StudentID;
    private long KolegijID;

    public Ocjena(int Value, String Opis, Date Datum, long studentID, long KolegijID) {
        this.ID = 0;
        this.Value = Value;
        this.Opis = Opis;
        this.Datum = Datum;
        this.StudentID = studentID;
        this.KolegijID = KolegijID;
    }
    
    public Ocjena() {
        this.ID = 0;
        this.Value = 0;
        this.Opis = "";
        this.Datum = null;
        this.StudentID = 0;
        this.KolegijID = 0;
    }
    
    public static Ocjena resultSetToOcjena(ResultSet rs){
        Ocjena ocjena = null;
        try {
            ocjena = new Ocjena(rs.getInt("Value"), rs.getString("Opis"), rs.getDate("Datum"), rs.getLong("StudentID"), rs.getLong("KolegijID"));
            ocjena.setID(rs.getLong("ID"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ocjena;
    }
    
    public static ArrayList<Ocjena> getByProfesorID(long ProfesorID){
        ArrayList<Ocjena> ocjene = new ArrayList<>();
        List<Object> params = Arrays.asList(ProfesorID);
        DB db = new DB();
        db.select("SELECT \n" +
"	o.ID, o.Value, o.Opis, o.Datum, o.KolegijID, o.StudentID\n" +
"FROM\n" +
"	user u, kolegij k, ocjena o\n" +
"WHERE\n" +
"	o.KolegijID=k.ID AND\n" +
"    k.PredavacID=u.ID AND\n" +
"    u.Privilegije='profesor' AND\n" +
"	u.ID=? ", params);
        
        try {
            while(db.getResultSet().next()){
                ocjene.add(resultSetToOcjena(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return ocjene;
    }
    
    
    public static ArrayList<Ocjena> getByStudentID(long StudentID){
        ArrayList<Ocjena> ocjene = new ArrayList<>();
        List<Object> params = Arrays.asList(StudentID);
        DB db = new DB();
        db.select("SELECT  \n" +
"	o.ID, o.Value, o.Opis, o.Datum, o.KolegijID, o.StudentID\n" +
"FROM\n" +
"	user u, kolegij k, ocjena o\n" +
"WHERE\n" +
"	o.KolegijID=k.ID AND\n" +
"    o.StudentID=u.ID AND\n" +
"   u.Privilegije='ucenik' AND\n" +
"	u.ID=?", params);
        
        try {
            while(db.getResultSet().next()){
                ocjene.add(resultSetToOcjena(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return ocjene;
    }
    
    public static ArrayList<Ocjena> getForStudentAndKolegij(long StudentID, long KolegijID){
        ArrayList<Ocjena> ocjene = new ArrayList<>();
        List<Object> params = Arrays.asList(StudentID, KolegijID);
        DB db = new DB();
        db.select("SELECT * FROM ocjena WHERE StudentID=? AND KolegijID=?", params);
        
        try {
            while(db.getResultSet().next()){
                ocjene.add(resultSetToOcjena(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return ocjene;
    }
    
    
    public void delete(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.ID);
        db.delete("DELETE FROM ocjena WHERE ID=?", params);
        db.disconnect();
    }
    
    public void insert(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.Value, this.Opis, this.Datum, this.StudentID, this.KolegijID);
        db.insert("INSERT INTO ocjena(Value, Opis, Datum, StudentID, KolegijID) VALUES(?,?,?,?,?)", params);
        db.disconnect();
    }
    
    public void update(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.Value, this.Opis, this.Datum, this.StudentID, this.KolegijID, this.ID);
        db.update("UPDATE ocjena SET Value=?, Opis=?, Datum=?, StudentID=?, KolegijID=? WHERE ID=?", params);
        db.disconnect();
    }
    
    public String getNazivKolegija(){
        Kolegij k = Kolegij.getById(this.KolegijID);
        return k != null ? k.getNaziv() : "";
    }

    public String getNazivStudenta(){
        User u = User.getById(this.StudentID);
        return u != null ? u.getIme() + " " + u.getPrezime() : "";
    }
    
    public String getOpis() {
        return Opis;
    }

    public void setOpis(String Opis) {
        this.Opis = Opis;
    }

    public String getDatumString(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(Datum);
    }
    
    public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date Datum) {
        this.Datum = Datum;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
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
