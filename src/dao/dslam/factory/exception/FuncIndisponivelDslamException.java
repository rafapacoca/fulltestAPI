/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.dslam.factory.exception;

/**
 *
 * @author G0042204
 */
public class FuncIndisponivelDslamException extends Exception {

    public FuncIndisponivelDslamException() {
        super("Funcionalidade indisponível para este modelo de DSLAM.");
    }

}
