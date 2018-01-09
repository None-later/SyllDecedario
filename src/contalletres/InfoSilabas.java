/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contalletres;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class InfoSilabas extends ArrayList{

    private int count;
    private String silaba;

    public Override contains(){
        return null;
        
    }
    
    public InfoSilabas(String silaba) {
        this.silaba = silaba;
        this.count = 0;
    }

    public InfoSilabas() {
        this.silaba = "";
        this.count = 0;
    }

    public void setSilaba(String silaba) {
        this.silaba = silaba;
    }

    public String getSilaba() {
        return this.silaba;
    }

    public void incrementCount() {
        ++this.count;
    }

    public int getCount() {
        return this.count;
    }
}
