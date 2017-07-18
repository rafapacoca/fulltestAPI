/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fulltest.operacional.facade;

import br.net.gvt.efika.customer.EfikaCustomer;
import dao.dslam.impl.ConsultaGponDefault;
import java.util.List;
import model.fulltest.operacional.FullTest;
import model.fulltest.operacional.FullTestAdapter;
import model.fulltest.validacao.factory.FactoryValidacao;
import model.validacao.Validacao;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author G0042204
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true, value = {"cl", "dslam", "bateria"})
public class FullTestGponFacade extends FullTestFacadeAbs implements FullTestInterface {

    protected ConsultaGponDefault cg;

    public FullTestGponFacade() {
    }

    @Override
    public FullTest executar(EfikaCustomer cl) throws Exception {
        this.setCl(cl);
        return FullTestAdapter.adapter(this);
    }


}
