/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.manobra.analitcs;

import br.net.gvt.efika.customer.CustomerAssert;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author G0042204
 */
public class CriterioMotivo implements Checker {

    private List<CustomerAssert> criterios;

    private ConclusaoMotivo conclusao;

    public CriterioMotivo() {
    }

    public void adicionarCriterio(CustomerAssert c) {
        getCriterios().add(c);
    }

    /**
     *
     * @param lst asserts do customer
     * @return
     */
    @Override
    public Boolean check(List<CustomerAssert> lst) {
        return lst.containsAll(criterios);
    }

    public List<CustomerAssert> getCriterios() {
        if (criterios == null) {
            criterios = new ArrayList<>();
        }
        return criterios;
    }

    public ConclusaoMotivo getConclusao() {
        return conclusao;
    }

    public void setConclusao(ConclusaoMotivo conclusao) {
        this.conclusao = conclusao;
    }

}
