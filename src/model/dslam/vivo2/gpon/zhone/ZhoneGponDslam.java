/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dslam.vivo2.gpon.zhone;

import dao.dslam.telnet.ComandoDslam;
import dao.dslam.telnet.ConsultaDslam;
import java.util.List;
import model.dslam.consulta.AlarmesGpon;
import model.dslam.consulta.EstadoDaPorta;
import model.dslam.consulta.SerialOntGpon;
import model.dslam.consulta.TabelaParametrosGpon;
import model.dslam.consulta.Vlan;
import model.dslam.consulta.VlanMulticast;
import model.dslam.credencial.Credencial;
import model.dslam.login.LoginLento;
import model.dslam.vivo2.gpon.DslamGpon;

/**
 *
 * @author G0042204
 */
public class ZhoneGponDslam extends DslamGpon {

    public ZhoneGponDslam() {
        this.setCredencial(Credencial.ZHONE);
        this.setLoginStrategy(new LoginLento());
        this.setCd(new ConsultaDslam(this));
    }

    private static String[] tratZhone(List<String> list, String qqqro, Integer o){
        Integer i = 1;
        for (String leLine : list) {
            if(leLine.contains(qqqro)){
                if(i.equals(o)){
                   String[] lineBroken = leLine.split("\\s\\s+");
                   return lineBroken;
                }
                i++;
            }
        }
        
        return null;
    }
    
    public ComandoDslam getComandoTabelaParametros() {
        return new ComandoDslam("onu status "+this.getSlot()+"/"+this.getPorta()+"/"+this.getLogica());
    }

    @Override
    public TabelaParametrosGpon getTabelaParametros() throws Exception {
        List<String> leParams = this.getCd().consulta(this.getComandoTabelaParametros()).getRetorno();
        String[] pegaParams = tratZhone(leParams, "1-1-"+this.getSlot()+"-"+this.getPorta(), 1);
        System.out.println(pegaParams);
        
        
        return null;
    }

    
    public ComandoDslam getComandoSerialOnt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SerialOntGpon getSerialOnt() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ComandoDslam getComandoConsultaEstadoDaPorta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EstadoDaPorta getEstadoDaPorta() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ComandoDslam getComandoConsultaVlanBanda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vlan getVlanBanda() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ComandoDslam getComandoConsultaVlanVoip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vlan getVlanVoip() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ComandoDslam getComandoConsultaVlanVod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vlan getVlanVod() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ComandoDslam getComandoConsultaVlanMulticast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VlanMulticast getVlanMulticast() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ComandoDslam getComandoConsultaAlarmes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AlarmesGpon getAlarmes() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}