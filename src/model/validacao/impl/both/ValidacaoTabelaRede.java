/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao.impl.both;

import br.net.gvt.efika.customer.EfikaCustomer;
import java.util.Locale;
import model.dslam.consulta.metalico.TabelaRedeMetalico;

/**
 *
 * @author G0042204
 */
public class ValidacaoTabelaRede extends ValidacaoValidavel {

    public ValidacaoTabelaRede(TabelaRedeMetalico tab, EfikaCustomer cust, Locale local) {
        super(cust, tab, local);
    }

    @Override
    protected String frasePositiva() {
        return "Rede confiável.";
    }

    @Override
    protected String fraseNegativa() {
        return "Rede não confiável.";
    }

}
