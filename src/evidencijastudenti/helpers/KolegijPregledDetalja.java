/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencijastudenti.helpers;

import evidencijastudenti.models.Kolegij;

/**
 *
 * @author owner
 */
public class KolegijPregledDetalja {
    public static Kolegij kolegij;
    public static Kolegij getKolegij(){ return kolegij; }
    public static void setKolegij(Kolegij k){ kolegij = k; }
}
