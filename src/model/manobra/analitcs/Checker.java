/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.manobra.analitcs;

import br.net.gvt.efika.customer.CustomerAssert;
import java.util.List;

/**
 *
 * @author G0042204
 */
public interface Checker {
     public Boolean check(List<CustomerAssert> lst);
}
