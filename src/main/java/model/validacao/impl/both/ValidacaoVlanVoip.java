/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao.impl.both;

import br.net.gvt.efika.efika_customer.model.customer.EfikaCustomer;
import br.net.gvt.efika.fulltest.model.telecom.properties.VlanVoip;
import java.util.Locale;

/**
 *
 * @author G0042204
 */
public class ValidacaoVlanVoip extends ValidacaoValidavel {

    private final transient VlanVoip vlan;

    public ValidacaoVlanVoip(VlanVoip v, EfikaCustomer cust, Locale local) {
        super(cust, v, local);
        this.vlan = v;
    }

    @Override
    protected String frasePositiva() {
        return "Vlan de VoIP configurado corretamente.";
    }

    @Override
    protected String fraseNegativa() {
        return "Vlan de VoIP configurado incorretamente.";
    }
}
