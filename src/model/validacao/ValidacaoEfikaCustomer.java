/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao;

import br.net.gvt.efika.customer.EfikaCustomer;

/**
 *
 * @author G0042204
 */
public abstract class ValidacaoEfikaCustomer extends Validacao{
    
    protected EfikaCustomer cust;

    public ValidacaoEfikaCustomer(EfikaCustomer cust) {
        this.cust = cust;
    }

}