/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.dslam.impl.metalico.keymile;

import dao.dslam.impl.retorno.TratativaRetornoUtil;
import model.dslam.consulta.Profile;
import model.dslam.consulta.metalico.TabelaParametrosMetalico;
import model.dslam.velocidade.Velocidades;

/**
 *
 * @author G0041775
 */
public class KeymileMetalicoSuad5 extends KeymileMetalicoSuadDslam{

    public KeymileMetalicoSuad5(String ipDslam) {
        super(ipDslam);
    }

    @Override
    public TabelaParametrosMetalico getTabelaParametrosIdeal(Velocidades v) throws Exception {
        TabelaParametrosMetalico t = new TabelaParametrosMetalico();
        t.setSnrDown(1d);
        t.setSnrUp(5d);
        t.setAtnDown(1d);
        t.setAtnUp(2d);
        t.setVelSincDown(TratativaRetornoUtil.velocidadeMinima(v).get(0));
        t.setVelSincUp(TratativaRetornoUtil.velocidadeMinima(v).get(1));
        
        return t;
    }

    @Override
    public Profile castProfile(Velocidades v) {
        Profile p = new Profile();
        p.setProfileDown("HSI_" + v.getVel() + "Mb_1Mb");
        p.setProfileUp("HSI_" + v.getVel() + "Mb_1Mb_SUAD5");
        return p;
    }
}
