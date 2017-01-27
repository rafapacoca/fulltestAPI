/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dslam.vivo2.gpon;

import dao.dslam.telnet.ComandoDslam;
import java.math.BigInteger;
import model.dslam.AbstractDslam;
import model.dslam.consulta.AlarmesGpon;
import model.dslam.consulta.ConsultaGponDefault;
import model.dslam.consulta.EstadoDaPorta;
import model.dslam.consulta.SerialOntGpon;
import model.dslam.consulta.TabelaParametrosGpon;
import model.dslam.consulta.Vlan;
import model.dslam.consulta.VlanMulticast;

/**
 *
 * @author G0041775
 */
public abstract class DslamGpon extends AbstractDslam
        implements
        ConsultaGponDefault {

    private BigInteger slot;
    private BigInteger porta;
    private BigInteger logica;
    private BigInteger sequencial;


    @Override
    public abstract TabelaParametrosGpon getTabelaParametros() throws Exception;

    @Override
    public abstract SerialOntGpon getSerialOnt() throws Exception;

    @Override
    public abstract EstadoDaPorta getEstadoDaPorta() throws Exception;

    @Override
    public abstract Vlan getVlanBanda() throws Exception;

    @Override
    public abstract Vlan getVlanVoip() throws Exception;

    @Override
    public abstract Vlan getVlanVod() throws Exception;

    @Override
    public abstract VlanMulticast getVlanMulticast() throws Exception;

    @Override
    public abstract AlarmesGpon getAlarmes() throws Exception;
    
    public void setSlot(BigInteger slot) {
        this.slot = slot;
    }

    public void setPorta(BigInteger porta) {
        this.porta = porta;
    }

    public void setLogica(BigInteger logica) {
        this.logica = logica;
    }

    public void setSequencial(BigInteger sequencial) {
        this.sequencial = sequencial;
    }

    public BigInteger getSlot() {
        return this.slot;
    }

    public BigInteger getPorta() {
        return this.porta;
    }

    public BigInteger getLogica() {
        return this.logica;
    }

    public BigInteger getSequencial() {
        return this.sequencial;
    }
}