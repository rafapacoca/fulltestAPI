/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.dslam.impl.gpon.alcatel;

import br.net.gvt.efika.efika_customer.model.customer.InventarioRede;
import br.net.gvt.efika.fulltest.model.telecom.properties.DeviceMAC;
import br.net.gvt.efika.fulltest.model.telecom.properties.EnumEstadoVlan;
import br.net.gvt.efika.fulltest.model.telecom.properties.EstadoDaPorta;
import br.net.gvt.efika.fulltest.model.telecom.properties.Porta;
import br.net.gvt.efika.fulltest.model.telecom.properties.Profile;
import br.net.gvt.efika.fulltest.model.telecom.properties.ReConexao;
import br.net.gvt.efika.fulltest.model.telecom.properties.VlanBanda;
import br.net.gvt.efika.fulltest.model.telecom.properties.VlanMulticast;
import br.net.gvt.efika.fulltest.model.telecom.properties.VlanVod;
import br.net.gvt.efika.fulltest.model.telecom.properties.VlanVoip;
import br.net.gvt.efika.fulltest.model.telecom.properties.gpon.AlarmesGpon;
import br.net.gvt.efika.fulltest.model.telecom.properties.gpon.PortaPON;
import br.net.gvt.efika.fulltest.model.telecom.properties.gpon.SerialOntGpon;
import br.net.gvt.efika.fulltest.model.telecom.properties.gpon.TabelaParametrosGpon;
import br.net.gvt.efika.fulltest.model.telecom.velocidade.VelocidadeVendor;
import br.net.gvt.efika.fulltest.model.telecom.velocidade.Velocidades;
import dao.dslam.impl.ComandoDslam;
import dao.dslam.impl.gpon.DslamGpon;
import dao.dslam.impl.login.LoginRapido;
import dao.dslam.impl.retorno.TratativaRetornoUtil;
import exception.SemGerenciaException;
import java.util.ArrayList;
import java.util.List;
import model.dslam.credencial.Credencial;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author G0042204
 */
public class AlcatelGponDslam extends DslamGpon {

    public AlcatelGponDslam(String ipDslam) {
        super(ipDslam, Credencial.ALCATEL, new LoginRapido());
    }

    @Override
    public void conectar() throws Exception {
        super.conectar();
    }

    @Override
    public void enableCommandsInDslam() throws Exception {
        if (this.getCd().consulta(this.getComandoInhibitAlarms()).getBlob().contains("Connection closed")) {
            throw new SemGerenciaException();
        }
        this.getCd().consulta(this.getComandoModeBatch());
        this.getCd().consulta(this.getComandoExit());
    }

    protected ComandoDslam getComandoInhibitAlarms() {
        return new ComandoDslam("environment inhibit-alarms");
    }

    protected ComandoDslam getComandoModeBatch() {
        return new ComandoDslam("environment mode batch");
    }

    protected ComandoDslam getComandoExit() {
        return new ComandoDslam("exit all");
    }

    protected ComandoDslam getComandoDumpRafael() {
        return new ComandoDslam("show equipment ont operational-data detail xml");
    }

    protected ComandoDslam getComandoPortaPON(InventarioRede i) {
        return new ComandoDslam("show equipment slot 1/1/" + i.getSlot() + " detail xml", 3000);
    }

    @Override
    public PortaPON getPortaPON(InventarioRede i) throws Exception {
        PortaPON porta = new PortaPON();
        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoPortaPON(i)));
        String operStatus = TratativaRetornoUtil.getXmlParam(xml, "//info[@name='oper-status']");
        porta.setOperState(operStatus.equalsIgnoreCase("enabled"));
        return porta;
    }

    /**
     * Função utilizada para demonstração (
     *
     * @return
     * @throws Exception
     */
    protected Document getDumpRafael() throws Exception {
        //Document xml;
        //xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoDumpRafael()));
        return TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoDumpRafael()));
    }

    protected ComandoDslam getComandoTabelaParametros(InventarioRede i) {
        return new ComandoDslam("show equipment ont optics 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " detail xml", 5000);
    }

    @Override
    public TabelaParametrosGpon getTabelaParametros(InventarioRede i) throws Exception {

        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoTabelaParametros(i)));
        String potOnt = TratativaRetornoUtil.getXmlParam(xml, "//info[@name='rx-signal-level']");
        String potOlt = TratativaRetornoUtil.getXmlParam(xml, "//info[@name='olt-rx-sig-level']");
        if (potOnt.equals("invalid") || potOnt.equals("unknown")) {
            potOnt = "0";
        }
        if (potOlt.equals("invalid") || potOlt.equals("unknown")) {
            potOlt = "0";
        }
        TabelaParametrosGpon tabParam = new TabelaParametrosGpon();
        tabParam.setPotOlt(new Double(potOlt));
        tabParam.setPotOnt(new Double(potOnt));
        System.out.println(potOnt + " | " + potOlt);

        return tabParam;
    }

    protected ComandoDslam getComandoSerialOnt(InventarioRede i) {
        return new ComandoDslam("info configure equipment ont interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " detail xml", 3000);
    }

    @Override
    public SerialOntGpon getSerialOnt(InventarioRede i) throws Exception {
        ComandoDslam cd = this.getCd().consulta(this.getComandoSerialOnt(i));
        Document xml = TratativaRetornoUtil.stringXmlConfigData(cd);
        String sernum = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='sernum']").replace(":", "");
        if (sernum.contains("ALCL00")) {
            sernum = "";
        }
        SerialOntGpon ont = new SerialOntGpon();
        ont.setSerial(sernum);
        System.out.println(sernum);

        return ont;
    }

    protected ComandoDslam getComandoConsultaEstadoDaPorta(InventarioRede i) {
        return new ComandoDslam("info configure equipment ont interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " detail xml", 5000);
    }

    protected ComandoDslam getComandoConsultaEstadoDaPortaV2(InventarioRede i) {
        return new ComandoDslam("info configure equipment ont interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " xml", 5000);
    }

    @Override
    public EstadoDaPorta getEstadoDaPorta(InventarioRede i) throws Exception {
        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoConsultaEstadoDaPortaV2(i)));
        String adminState = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='admin-state']");
        String operState = TratativaRetornoUtil.getXmlParam(xml, "//info[@name='oper-state']");

        EstadoDaPorta state = new EstadoDaPorta();
        state.setAdminState(adminState.equalsIgnoreCase("UP"));
        state.setOperState(operState.equalsIgnoreCase("UP"));

        return state;
    }

    protected ComandoDslam getComandoConsultaVlanBanda(InventarioRede i) {
        return new ComandoDslam("info configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 600 detail xml", 5000);
    }

    @Override
    public VlanBanda getVlanBanda(InventarioRede i) throws Exception {
        ComandoDslam consulta = this.getCd().consulta(this.getComandoConsultaVlanBanda(i));
        List<String> leResp = consulta.getRetorno();

        Integer svlan = new Integer("0");
        Integer cvlan = new Integer("0");
        EnumEstadoVlan state = EnumEstadoVlan.DOWN;
        if (!leResp.contains("Error : instance does not exist")) {
            Document xml = TratativaRetornoUtil.stringXmlParse(consulta);
            String leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='network-vlan']");
            if (leVlan.isEmpty()) {
                leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='l2fwder-vlan']");
            }

            String[] pegaVlan = leVlan.split(":");
            svlan = new Integer(pegaVlan[1]);
            cvlan = new Integer(pegaVlan[2]);
            state = EnumEstadoVlan.UP;
        }

        VlanBanda vlanBanda = new VlanBanda(cvlan, svlan, state);

//        System.out.println(svlan);
//        System.out.println(cvlan);
        return vlanBanda;
    }

    protected ComandoDslam getComandoConsultaVlanVoip(InventarioRede i) {
        return new ComandoDslam("info configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 601 detail xml", 3000);
    }

    @Override
    public VlanVoip getVlanVoip(InventarioRede i) throws Exception {
        ComandoDslam consulta = this.getCd().consulta(this.getComandoConsultaVlanVoip(i));
        List<String> leResp = consulta.getRetorno();
        Integer cvlan = new Integer("0");
        Integer p100 = new Integer("0");
        EnumEstadoVlan state = EnumEstadoVlan.DOWN;
        if (!leResp.contains("Error : instance does not exist")) {
            Document xml = TratativaRetornoUtil.stringXmlParse(consulta);
            String leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='network-vlan']");
            if (leVlan.isEmpty()) {
                leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='l2fwder-vlan']");
            }
            String[] pegaVlan = leVlan.split(":");
            cvlan = new Integer(pegaVlan[1]);
            p100 = new Integer(pegaVlan[2]);
            state = EnumEstadoVlan.UP;
        }
        VlanVoip vlanVoip = new VlanVoip(p100, cvlan, state);

//        System.out.println(cvlan);
//        System.out.println(p100);
        return vlanVoip;
    }

    protected ComandoDslam getComandoConsultaVlanVod(InventarioRede i) {
        return new ComandoDslam("info configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 602 detail xml", 3000);
    }

    @Override
    public VlanVod getVlanVod(InventarioRede i) throws Exception {
        ComandoDslam consulta = this.getCd().consulta(this.getComandoConsultaVlanVod(i));
        List<String> leResp = consulta.getRetorno();
        Integer cvlan = new Integer("0");
        Integer p100 = new Integer("0");
        EnumEstadoVlan state = EnumEstadoVlan.DOWN;
        if (!leResp.contains("Error : instance does not exist")) {
            Document xml = TratativaRetornoUtil.stringXmlParse(consulta);
            String leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='network-vlan']");
            if (leVlan.isEmpty()) {
                leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='l2fwder-vlan']");
            }
            String[] pegaVlan = leVlan.split(":");
            cvlan = new Integer(pegaVlan[1]);
            p100 = new Integer(pegaVlan[2]);
            state = EnumEstadoVlan.UP;
        }

        VlanVod vlanVod = new VlanVod(p100, cvlan, state);
//
//        System.out.println(cvlan);
//        System.out.println(p100);

        return vlanVod;
    }

    protected ComandoDslam getComandoConsultaVlanMulticast(InventarioRede i) {
        return new ComandoDslam("info configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 4000 detail xml", 3000);
    }

    @Override
    public VlanMulticast getVlanMulticast(InventarioRede i) throws Exception {
        ComandoDslam consulta = this.getCd().consulta(this.getComandoConsultaVlanMulticast(i));
        List<String> leResp = consulta.getRetorno();
        String leVlan = "";
        Integer svlan = new Integer("0");
        EnumEstadoVlan state = EnumEstadoVlan.DOWN;
        if (!leResp.contains("Error : instance does not exist")) {
            Document xml = TratativaRetornoUtil.stringXmlParse(consulta);
            leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='network-vlan']");
            if (leVlan.isEmpty()) {
                leVlan = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='l2fwder-vlan']");
            }
        }
        if (!leVlan.isEmpty()) {
            svlan = new Integer("4000");
            state = EnumEstadoVlan.UP;
        }

        VlanMulticast multz = new VlanMulticast();
        multz.setSvlan(svlan);
        multz.setState(state);

//        System.out.println(svlan);
        return multz;
    }

    protected ComandoDslam getComandoConsultaAlarmes(InventarioRede i) {
        return new ComandoDslam("show equipment ont operational-data 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " detail xml", 9000);
    }

    @Override
    public AlarmesGpon getAlarmes(InventarioRede i) throws Exception {
        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoConsultaAlarmes(i)));
        NodeList nodeList = xml.getElementsByTagName("info");
        AlarmesGpon alarmes = new AlarmesGpon();

        for (int e = 0; e < nodeList.getLength(); e++) {
            Node node = nodeList.item(e);

            String nomeAlarme = node.getAttributes().getNamedItem("name").getTextContent().trim();
            String estadoAlarme = node.getTextContent().trim();

            if (estadoAlarme.equalsIgnoreCase("yes")) {
                alarmes.getListAlarmes().add(nomeAlarme);
            }

        }
//        System.out.println(alarmes.getListAlarmes());

        return alarmes;
    }

    protected ComandoDslam getComandoConsultaProfile(InventarioRede i) {
        return new ComandoDslam("info configure qos interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 xml", 7500);
    }

    @Override
    public Profile getProfile(InventarioRede i) throws Exception {
        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoConsultaProfile(i)));

        String leProfileDown = TratativaRetornoUtil.getXmlParam(xml, "//parameter[@name='shaper-profile']");
        String profileDown = leProfileDown.substring(5, leProfileDown.length());
        String leProfileUp = TratativaRetornoUtil.getXmlParam(xml, "(//parameter[@name='bandwidth-profile'])[1]");
        if (leProfileUp.length() < 5) {
            leProfileUp = TratativaRetornoUtil.getXmlParam(xml, "(//parameter[@name='bandwidth-profile'])[2]");
        }
        String profileUp = leProfileUp.substring(5, leProfileUp.length());

        Profile prof = new Profile();
        prof.setProfileDown(profileDown);
        prof.setProfileUp(profileUp);
        prof.setDown(compare(profileDown, true));
        prof.setUp(compare(profileUp, false));

//        System.out.println(prof.getDown());
//        System.out.println(prof.getUp());
        return prof;
    }

    @Override
    public List<VelocidadeVendor> obterVelocidadesUpVendor() {
        if (velsUp.isEmpty()) {
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_1024, "HSI_1M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_2048, "HSI_2M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_3072, "HSI_3M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_5120, "HSI_5M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_12800, "HSI_12.5M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_25600, "HSI_25M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_51200, "HSI_50M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_76800, "HSI_75M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_102400, "HSI_100M_RETAIL_UP"));
            velsUp.add(new VelocidadeVendor(Velocidades.VEL_153600, "HSI_150M_RETAIL_UP"));
        }

        return velsUp;
    }

    @Override
    public List<VelocidadeVendor> obterVelocidadesDownVendor() {
        if (velsDown.isEmpty()) {
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_1024, "HSI_1M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_3072, "HSI_3M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_5120, "HSI_5M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_10240, "HSI_10M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_15360, "HSI_15M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_25600, "HSI_25M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_35840, "HSI_35M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_51200, "HSI_50M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_102400, "HSI_100M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_153600, "HSI_150M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_204800, "HSI_200M_RETAIL_DOWN"));
            velsDown.add(new VelocidadeVendor(Velocidades.VEL_307200, "HSI_300M_RETAIL_DOWN"));
        }

        return velsDown;
    }

    protected ComandoDslam getComandoConsultaDeviceMAC(InventarioRede i) {
        return new ComandoDslam("show vlan bridge-port-fdb 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 xml", 9000);
    }

    @Override
    public DeviceMAC getDeviceMac(InventarioRede i) throws Exception {
        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoConsultaDeviceMAC(i)));
        NodeList nodeList = xml.getElementsByTagName("instance");
        Integer e;
        String leMac = "";
        for (e = 0; e < nodeList.getLength(); e++) {
            NodeList listin = nodeList.item(e).getChildNodes();
            Integer o;
            for (o = 0; o < listin.getLength(); o++) {
                System.out.println("item " + o + " ->" + listin.item(o).getTextContent());
                if (listin.item(o).getTextContent().equals("600")) {
                    Node leNode = listin.item(o).getNextSibling();
                    leMac = leNode.getNextSibling().getTextContent();
                }
            }
        }
        return new DeviceMAC(leMac.toUpperCase());
    }

    protected ComandoDslam getComandoSetEstadoDaPorta(InventarioRede i, EstadoDaPorta e) {
        return new ComandoDslam("configure equipment ont interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " admin-state " + e.toString());
    }

    @Override
    public EstadoDaPorta setEstadoDaPorta(InventarioRede i, EstadoDaPorta e) throws Exception {
        List<String> oi = getCd().consulta(getComandoSetEstadoDaPorta(i, e)).getRetorno();
        for (String string : oi) {
            System.out.println(string);
        }
        return getEstadoDaPorta(i);
    }

    protected ComandoDslam getComandoSetOntToOlt(InventarioRede i, SerialOntGpon s) {
        return new ComandoDslam("configure equipment ont interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " sw-ver-pland disabled sernum " + s.getSerial());
    }

    @Override
    public SerialOntGpon setOntToOlt(InventarioRede i, SerialOntGpon s) throws Exception {
        if (!s.getSerial().contains(":")) {
            String first = s.getSerial().substring(0, 4);
            String second = s.getSerial().substring(4, s.getSerial().length());
            System.out.println(first);
            System.out.println(second);
            s.setSerial(first + ":" + second);
            System.out.println(s.getSerial());
        }
        List<String> leResp = getCd().consulta(getComandoSetOntToOlt(i, s)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
        return getSerialOnt(i);
    }

    protected ComandoDslam getComandoSetProfileDown(InventarioRede i, Velocidades v) {
        return new ComandoDslam("configure qos interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 queue 0 shaper-profile name:" + compare(v, true).getSintaxVel());
    }

    protected ComandoDslam getComandoSetProfileUp(InventarioRede i, Velocidades v) {
        return new ComandoDslam("configure qos interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 upstream-queue 0 bandwidth-profile name:" + compare(v, false).getSintaxVel());
    }

    @Override
    public void setProfileDown(InventarioRede i, Velocidades v) throws Exception {
        List<String> leResp = getCd().consulta(getComandoSetProfileDown(i, v)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
//        return getProfile(i);
    }

    @Override
    public void setProfileUp(InventarioRede i, Velocidades vDown, Velocidades vUp) throws Exception {
        List<String> leResp = getCd().consulta(getComandoSetProfileUp(i, vUp)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
//        return getProfile(i);
    }

    protected ComandoDslam getComandoCreateVlanBanda(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 600 tag single-tagged network-vlan stacked:" + i.getRin() + ":" + i.getCvlan() + " vlan-scope local");
    }

    @Override
    public VlanBanda createVlanBanda(InventarioRede i, Velocidades vDown, Velocidades vUp) throws Exception {
        List<String> leResp = getCd().consulta(getComandoCreateVlanBanda(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
        return getVlanBanda(i);
    }

    protected ComandoDslam getComandoCreateVlanVoip(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 601 tag single-tagged network-vlan stacked:" + i.getVlanVoip() + ":" + i.getCvlan() + " vlan-scope local qos profile:14");
    }

    @Override
    public VlanVoip createVlanVoip(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoCreateVlanVoip(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
        return getVlanVoip(i);
    }

    protected ComandoDslam getComandoCreateVlanVod(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 602 tag single-tagged network-vlan stacked:" + i.getVlanVod() + ":" + i.getCvlan() + " vlan-scope local qos profile:12");
    }

    @Override
    public VlanVod createVlanVod(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoCreateVlanVod(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
        return getVlanVod(i);
    }

    protected ComandoDslam getComandoCreateVlanMulticast(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 vlan-id 4000 tag single-tagged qos profile:13");
    }

    @Override
    public VlanMulticast createVlanMulticast(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoCreateVlanMulticast(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
        return getVlanMulticast(i);
    }

    protected ComandoDslam getComandoUnsetOntFromOlt(InventarioRede i) {
        return new ComandoDslam("configure equipment ont interface 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + " no sernum");
    }

    @Override
    public void unsetOntFromOlt(InventarioRede i) throws Exception {
        EstadoDaPorta e = new EstadoDaPorta();
        e.setAdminState(Boolean.FALSE);
        getCd().consulta(getComandoSetEstadoDaPorta(i, e));
        getCd().consulta(getComandoUnsetOntFromOlt(i));
        e.setAdminState(Boolean.TRUE);
        getCd().consulta(getComandoSetEstadoDaPorta(i, e));
    }

    protected ComandoDslam getComandoDeleteVlanBanda(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 no vlan-id 600");
    }

    @Override
    public void deleteVlanBanda(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoDeleteVlanBanda(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
    }

    protected ComandoDslam getComandoDeleteVlanVoip(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 no vlan-id 601");
    }

    @Override
    public void deleteVlanVoip(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoDeleteVlanVoip(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
    }

    protected ComandoDslam getComandoDeleteVlanVod(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 no vlan-id 602");
    }

    @Override
    public void deleteVlanVod(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoDeleteVlanVod(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
    }

    protected ComandoDslam getComandoDeleteVlanMulticast(InventarioRede i) {
        return new ComandoDslam("configure bridge port 1/1/" + i.getSlot() + "/" + i.getPorta() + "/" + i.getLogica() + "/4/1 no vlan-id 4000");
    }

    @Override
    public void deleteVlanMulticast(InventarioRede i) throws Exception {
        List<String> leResp = getCd().consulta(getComandoDeleteVlanMulticast(i)).getRetorno();
        for (String string : leResp) {
            System.out.println(string);
        }
    }

//    @Override
//    public Profile castProfile(Velocidades v) {
//        Profile p = new Profile();
//
//        p.setProfileDown("HSI_" + v.getVel() + "M_RETAIL_DOWN");
//        p.setProfileUp("HSI_" + v.getVel() + "M_RETAIL_UP");
//
//        return p;
//    }
    protected ComandoDslam getComandoListaOntPorSlot() {
        return new ComandoDslam("show pon unprovision-onu xml", 3000);
    }

    @Override
    public List<SerialOntGpon> getSlotsAvailableOnts(InventarioRede i) throws Exception {
        Document xml = TratativaRetornoUtil.stringXmlParse(this.getCd().consulta(this.getComandoListaOntPorSlot()));
        NodeList nodeList = xml.getElementsByTagName("info");
        List<SerialOntGpon> serialList = new ArrayList<>();
        for (int e = 0; e < nodeList.getLength(); e++) {
            Node node = nodeList.item(e);

            if (node.getTextContent().contains("1/1/")) {
                String[] slotPorta = node.getTextContent().split("/");
                SerialOntGpon s = new SerialOntGpon();
                s.setSerial(node.getNextSibling().getNextSibling().getTextContent());
                s.setPorta(new Integer(slotPorta[3]));
                s.setSlot(new Integer(slotPorta[2]));
                serialList.add(s);
            }

        }
        return serialList;
    }

    @Override
    public List<Porta> getEstadoPortasProximas(InventarioRede i) throws Exception {
        InventarioRede inventario = i;
        List<Porta> list = new ArrayList<>();
        for (int j = 1; j < 33; j++) {
            inventario.setLogica(j);
            EstadoDaPorta estado = getEstadoDaPorta(inventario);
            Porta porta = new Porta();
            porta.setEstadoPorta(estado);
            porta.setNumPorta(j);
            list.add(porta);
        }
        return list;
    }

    @Override
    public ReConexao getReconexoes(InventarioRede i) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}