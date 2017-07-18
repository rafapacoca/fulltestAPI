/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fulltest.operacional.facade;

import br.net.gvt.efika.customer.EfikaCustomer;
import model.fulltest.operacional.FullTest;
import model.fulltest.operacional.FullTestAdapter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author G0042204
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true, value = {"cl", "dslam", "bateria"})
public class FullTestCorrectiveGponFacade extends FullTestFacadeAbs implements FullTestInterface {

    public FullTestCorrectiveGponFacade() {
    }

    /**
     *
     * @param cl
     * @return
     * @throws Exception
     */
    @Override
    public FullTest executar(EfikaCustomer cl) throws Exception {
        super.exec(cl);
        this.finalizar();
        return FullTestAdapter.adapter(this);
    }

}
