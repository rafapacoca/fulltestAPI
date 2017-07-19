/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao.realtime;

import br.net.gvt.efika.customer.EfikaCustomer;
import dao.dslam.impl.AbstractDslam;
import dao.dslam.impl.ConsultaClienteInter;
import model.validacao.ValidacaoEfikaCustomer;

/**
 *
 * @author G0042204
 */
public abstract class ValidacaoRealtime extends ValidacaoEfikaCustomer {

    protected ConsultaClienteInter consulta;

    public ValidacaoRealtime(AbstractDslam alter, EfikaCustomer cust, String nome) {
        super(cust, nome);
        this.consulta = (ConsultaClienteInter) alter;
    }

}
