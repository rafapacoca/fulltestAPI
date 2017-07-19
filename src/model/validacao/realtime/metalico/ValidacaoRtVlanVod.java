/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao.realtime.metalico;

import br.net.gvt.efika.customer.EfikaCustomer;
import dao.dslam.impl.AbstractDslam;
import model.validacao.ValidacaoVlanVod;
import model.validacao.realtime.ValidacaoRealtimeGpon;

/**
 *
 * @author G0042204
 */
public class ValidacaoRtVlanVod extends ValidacaoRealtimeGpon {

    private ValidacaoVlanVod valid;

    public ValidacaoRtVlanVod(AbstractDslam dslam, EfikaCustomer cust) {
        super(dslam, cust, "Vlan VoD");
    }

    @Override
    public Boolean validar() {
        try {
            if (cust.getServicos().getIsHib()) {
                valid = new ValidacaoVlanVod(cg.getVlanVod(cust.getRede()), cust);
                valid.validar();
                this.merge(valid);
            } else {
                setMensagem("Cliente sem TV Híbrida.");
                setResultado(Boolean.TRUE);
            }
            return getResultado();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
