/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contalletres;

/**
 *
 * @author jesus
 */
public enum GameType {
    WORDS("words"),
    SENTENCES("sentences");

    private final String type;

    private GameType(String type) {
        this.type = type;
    }

    public String getGameTypeName() {
        return this.type;
    }
}
