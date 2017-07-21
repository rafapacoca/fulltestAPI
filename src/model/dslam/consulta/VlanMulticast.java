/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dslam.consulta;

import br.net.gvt.efika.customer.EfikaCustomer;
import model.EnumEstadoVlan;

/**
 *
 * @author G0041775
 */
public class VlanMulticast extends VlanAbstract {

    public VlanMulticast() {
        super(null, null);
    }

    @Override
    public String getNome() {
        return "Vlan Multicast";
    }

    public VlanMulticast(Integer p100, Integer cvlan, EnumEstadoVlan estado) {
        super(p100, cvlan, estado);
    }

    @Deprecated
    public VlanMulticast(Integer p100, Integer cvlan) {
        super(p100, cvlan);
    }

    @Override
    public Boolean validar(EfikaCustomer e) {
        return getSvlan().equals(e.getRede().getVlanMulticast()) && getState().equals(EnumEstadoVlan.UP);
    }

}
