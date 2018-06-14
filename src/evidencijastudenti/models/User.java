/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.models;

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
public class User {
    private long ID;
    private String Username;
    private String Password;
    private String Ime;
    private String Prezime;
    private String Email;
    private String Privilegije;
    private String BrojIndeksa;
    private String BrojTelefona;
    
    
    public User(String username, String password, String email, String ime, String prezime, String privilegije, String brojIndeksa, String brojTelefona) {
        this.ID = 0;
        this.Username = username;
        this.Password = password;
        this.Email = email;
        this.Ime = ime;
        this.Prezime = prezime;
        this.Privilegije = privilegije;
        this.BrojIndeksa = brojIndeksa;
        this.BrojTelefona = brojTelefona;
    }

    public User() {
        this.ID = 0;
        this.Username = "";
        this.Password = "";
        this.Email = "";
        this.Ime = "";
        this.Prezime = "";
        this.Privilegije = "";
        this.BrojIndeksa = "";
        this.BrojTelefona = "";
    }
    
    
    public static User resultSetToUser(ResultSet rs){
        User user = null;
        try {
            user = new User(rs.getString("Username"), rs.getString("Password"), rs.getString("Email"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Privilegije"), rs.getString("BrojIndeksa"), rs.getString("BrojTelefona"));
            user.setID(rs.getLong("ID"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    
    public static ArrayList<User> getAll(){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList();
        DB db = new DB();
        db.select("SELECT * FROM user", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static ArrayList<User> getProfesoroveUcenike(long ProfesorID){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList(ProfesorID);
        DB db = new DB();
        db.select("SELECT DISTINCT \n" +
"	u.ID, u.Ime, u.Prezime, u.Username, u.Password, u.Email, u.BrojTelefona, u.BrojIndeksa, u.Privilegije\n" +
"FROM\n" +
"	user u, kolegijsudionik ks, kolegij k\n" +
"WHERE\n" +
"	k.PredavacID=? AND\n" +
"    ks.KolegijID=k.ID AND\n" +
"    u.ID=ks.StudentID AND\n" +
"    u.Privilegije='ucenik'", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static ArrayList<User> getSveUcenikeZaKolegij(long KolegijID){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList(KolegijID);
        DB db = new DB();
        db.select("SELECT DISTINCT * FROM user WHERE ID IN (SELECT StudentID FROM kolegijsudionik WHERE KolegijID=?) AND Privilegije='ucenik'", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static ArrayList<User> getSudioniciComboBoxData(long KolegijID){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList(KolegijID);
        DB db = new DB();
        db.select("SELECT * FROM user WHERE ID NOT IN (SELECT StudentID FROM kolegijsudionik WHERE KolegijID=?) AND Privilegije='ucenik'", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static ArrayList<User> getAllProfesors(){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList();
        DB db = new DB();
        db.select("SELECT * FROM user WHERE Privilegije='profesor'", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static ArrayList<User> getAllUcenike(){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList();
        DB db = new DB();
        db.select("SELECT * FROM user WHERE Privilegije='ucenik'", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
        
    public static User login(String username, String password){
        User user = null;
        
        DB db = new DB();
        List<Object> params = Arrays.asList(username, password);
        db.select("SELECT * FROM user WHERE Username=? AND Password=?", params);
        try {
            while(db.getResultSet().next()){
                user = resultSetToUser(db.getResultSet());
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return user;
    }
    
    public static User getById(long ID){
        User user = null;
        DB db = new DB();
        List<Object> params = Arrays.asList(ID);
        db.select("SELECT * FROM user WHERE ID=?", params);
        try {
            while(db.getResultSet().next()){
                user = resultSetToUser(db.getResultSet());
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return user;
    }
    
    public void delete(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.ID);
        db.delete("DELETE FROM ocjena WHERE StudentID=?", params);
        db.delete("DELETE FROM kolegijsudionik WHERE StudentID=?", params);
        db.delete("DELETE FROM kolegij WHERE PredavacID=?", params);
        db.delete("DELETE FROM user WHERE ID=?", params);
        db.disconnect();
    }
    
    public void insert(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.Ime, this.Prezime, this.Username, this.Password, this.Email, this.Privilegije, this.BrojIndeksa, this.BrojTelefona);
        db.insert("INSERT INTO user(Ime, Prezime, Username, Password, Email, Privilegije, BrojIndeksa, BrojTelefona) VALUES(?,?,?,?,?,?,?,?)", params);
        db.disconnect();
    }
    
    public void update(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.Ime, this.Prezime, this.Username, this.Password, this.Email, this.Privilegije, this.BrojIndeksa, this.BrojTelefona, this.ID);
        db.update("UPDATE user SET Ime=?, Prezime=?, Username=?, Password=?, Email=?, Privilegije=?, BrojIndeksa=?, BrojTelefona=? WHERE ID=?", params);
        db.disconnect();
    }
    
    

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String Ime) {
        this.Ime = Ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String Prezime) {
        this.Prezime = Prezime;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPrivilegije() {
        return Privilegije;
    }

    public void setPrivilegije(String Privilegije) {
        this.Privilegije = Privilegije;
    }
    
    public String getBrojIndeksa() {
        return BrojIndeksa;
    }

    public void setBrojIndeksa(String BrojIndeksa) {
        this.BrojIndeksa = BrojIndeksa;
    }

    public String getBrojTelefona() {
        return BrojTelefona;
    }

    public void setBrojTelefona(String BrojTelefona) {
        this.BrojTelefona = BrojTelefona;
    }
}
