/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao;

/**
 *
 * @author G0042204
 * @param <T>
 */
public interface Validador <T>{
    
    public boolean validar();
    
    public T consultar();
    
}
