/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.dslam.impl.gpon.alcatel;

import br.net.gvt.efika.customer.EfikaCustomer;
import br.net.gvt.efika.customer.InventarioRede;
import java.util.List;
import model.dslam.consulta.DeviceMAC;
import model.dslam.consulta.EstadoDaPorta;
import model.dslam.consulta.Porta;
import model.dslam.consulta.Profile;
import model.dslam.consulta.VlanBanda;
import model.dslam.consulta.VlanMulticast;
import model.dslam.consulta.VlanVod;
import model.dslam.consulta.VlanVoip;
import model.dslam.consulta.gpon.AlarmesGpon;
import model.dslam.consulta.gpon.SerialOntGpon;
import model.dslam.consulta.gpon.TabelaParametrosGpon;
import model.dslam.velocidade.VelocidadeVendor;
import model.dslam.velocidade.Velocidades;
import model.fulltest.operacional.CustomerMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.GsonUtil;

/**
 *
 * @author G0034481
 */
public class Alcatel7302GponDslamVivo1IT {

    /**
     * 2430282756 - Ready | 5137240278 - Falha Leitura
     */
    private static EfikaCustomer cust = CustomerMock.alcatel7302();
    private static Alcatel7302GponDslamVivo1 instance = new Alcatel7302GponDslamVivo1(cust.getRede().getIpDslam());
    private static InventarioRede i = cust.getRede();

    public Alcatel7302GponDslamVivo1IT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of obterVelocidadesDownVendor method, of class
     * Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testObterVelocidadesDownVendor() {
        System.out.println("obterVelocidadesDownVendor");
        Alcatel7302GponDslamVivo1 instance = null;
        List<VelocidadeVendor> expResult = null;
        List<VelocidadeVendor> result = instance.obterVelocidadesDownVendor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obterVelocidadesUpVendor method, of class
     * Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testObterVelocidadesUpVendor() {
        System.out.println("obterVelocidadesUpVendor");
        Alcatel7302GponDslamVivo1 instance = null;
        List<VelocidadeVendor> expResult = null;
        List<VelocidadeVendor> result = instance.obterVelocidadesUpVendor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEstadoDaPorta method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetEstadoDaPorta() throws Exception {
        System.out.println("getEstadoDaPortaV1");
        EstadoDaPorta result = instance.getEstadoDaPorta(i);
        System.out.println(GsonUtil.serialize(result));
    }

    /**
     * Test of getDeviceMac method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetDeviceMac() throws Exception {
        System.out.println("getDeviceMac");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        DeviceMAC expResult = null;
        DeviceMAC result = instance.getDeviceMac(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProfile method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetProfile() throws Exception {
        System.out.println("testGetProfile");
        Profile result = instance.getProfile(i);
        System.out.println(GsonUtil.serialize(result));
    }

    /**
     * Test of getVlanBanda method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetVlanBanda() throws Exception {
        System.out.println("getVlanBanda");
        VlanBanda result = instance.getVlanBanda(i);
        System.out.println(GsonUtil.serialize(result));
    }

    /**
     * Test of getVlanMulticast method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetVlanMulticast() throws Exception {
        System.out.println("getVlanBanda");
        VlanMulticast result = instance.getVlanMulticast(i);
        System.out.println(GsonUtil.serialize(result));
    }

    /**
     * Test of getVlanVoip method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetVlanVoip() throws Exception {
        System.out.println("getVlanBanda");
        VlanVoip result = instance.getVlanVoip(i);
        System.out.println(GsonUtil.serialize(result));
    }

    /**
     * Test of getVlanVod method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetVlanVod() throws Exception {
        System.out.println("getVlanVod");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        VlanVod expResult = null;
        VlanVod result = instance.getVlanVod(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSerialOnt method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetSerialOnt() throws Exception {
        System.out.println("getSerialOnt");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        SerialOntGpon expResult = null;
        SerialOntGpon result = instance.getSerialOnt(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabelaParametros method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetTabelaParametros() throws Exception {
        System.out.println("getTabelaParametros");
        TabelaParametrosGpon result = instance.getTabelaParametros(i);
        System.out.println(GsonUtil.serialize(result));
    }

    /**
     * Test of getAlarmes method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetAlarmes() throws Exception {
        System.out.println("getAlarmes");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        AlarmesGpon expResult = null;
        AlarmesGpon result = instance.getAlarmes(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSlotsAvailableOnts method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetSlotsAvailableOnts() throws Exception {
        System.out.println("getSlotsAvailableOnts");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        List<SerialOntGpon> expResult = null;
        List<SerialOntGpon> result = instance.getSlotsAvailableOnts(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEstadoPortasProximas method, of class
     * Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testGetEstadoPortasProximas() throws Exception {
        System.out.println("getEstadoPortasProximas");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        List<Porta> expResult = null;
        List<Porta> result = instance.getEstadoPortasProximas(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOntToOlt method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testSetOntToOlt() throws Exception {
        System.out.println("setOntToOlt");
        InventarioRede i = null;
        SerialOntGpon s = null;
        Alcatel7302GponDslamVivo1 instance = null;
        SerialOntGpon expResult = null;
        SerialOntGpon result = instance.setOntToOlt(i, s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unsetOntFromOlt method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testUnsetOntFromOlt() throws Exception {
        System.out.println("unsetOntFromOlt");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.unsetOntFromOlt(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEstadoDaPorta method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testSetEstadoDaPorta() throws Exception {
        System.out.println("setEstadoDaPorta");
        InventarioRede i = null;
        EstadoDaPorta e = null;
        Alcatel7302GponDslamVivo1 instance = null;
        EstadoDaPorta expResult = null;
        EstadoDaPorta result = instance.setEstadoDaPorta(i, e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProfileDown method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testSetProfileDown() throws Exception {
        System.out.println("setProfileDown");
        InventarioRede i = null;
        Velocidades v = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.setProfileDown(i, v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProfileUp method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testSetProfileUp() throws Exception {
        System.out.println("setProfileUp");
        InventarioRede i = null;
        Velocidades vDown = null;
        Velocidades vUp = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.setProfileUp(i, vDown, vUp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createVlanBanda method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testCreateVlanBanda() throws Exception {
        System.out.println("createVlanBanda");
        InventarioRede i = null;
        Velocidades vDown = null;
        Velocidades vUp = null;
        Alcatel7302GponDslamVivo1 instance = null;
        VlanBanda expResult = null;
        VlanBanda result = instance.createVlanBanda(i, vDown, vUp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createVlanVoip method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testCreateVlanVoip() throws Exception {
        System.out.println("createVlanVoip");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        VlanVoip expResult = null;
        VlanVoip result = instance.createVlanVoip(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createVlanVod method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testCreateVlanVod() throws Exception {
        System.out.println("createVlanVod");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        VlanVod expResult = null;
        VlanVod result = instance.createVlanVod(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createVlanMulticast method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testCreateVlanMulticast() throws Exception {
        System.out.println("createVlanMulticast");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        VlanMulticast expResult = null;
        VlanMulticast result = instance.createVlanMulticast(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVlanBanda method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testDeleteVlanBanda() throws Exception {
        System.out.println("deleteVlanBanda");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.deleteVlanBanda(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVlanVoip method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testDeleteVlanVoip() throws Exception {
        System.out.println("deleteVlanVoip");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.deleteVlanVoip(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVlanVod method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testDeleteVlanVod() throws Exception {
        System.out.println("deleteVlanVod");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.deleteVlanVod(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteVlanMulticast method, of class Alcatel7302GponDslamVivo1.
     */
    @Test
    public void testDeleteVlanMulticast() throws Exception {
        System.out.println("deleteVlanMulticast");
        InventarioRede i = null;
        Alcatel7302GponDslamVivo1 instance = null;
        instance.deleteVlanMulticast(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
