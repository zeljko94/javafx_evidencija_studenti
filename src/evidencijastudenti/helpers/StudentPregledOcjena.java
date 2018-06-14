/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.helpers;

import evidencijastudenti.models.User;

/**
 *
 * @author owner
 */
public class StudentPregledOcjena {
    public static User student;
    public static User getStudent(){ return student; }
    public static void setStudent(User s){ student = s; }
}
