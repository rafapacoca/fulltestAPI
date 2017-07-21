/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao.realtime;

import br.net.gvt.efika.customer.EfikaCustomer;
import dao.dslam.factory.exception.FalhaAoCorrigirException;
import dao.dslam.factory.exception.FuncIndisponivelDslamException;
import dao.dslam.impl.AbstractDslam;
import dao.dslam.impl.AlteracaoClienteInter;
import model.validacao.ValidacaoResult;

/**
 *
 * @author G0042204
 */
public abstract class Corretor extends Validador {

    protected AlteracaoClienteInter alter;
    
    protected Validador validador;

    public Corretor(AbstractDslam dslam, EfikaCustomer cust) {
        super(dslam, cust);
    }

    @Override
    protected void iniciar() throws FuncIndisponivelDslamException {
        super.iniciar();
        if (this.getDslam() instanceof AlteracaoClienteInter) {
            this.alter = (AlteracaoClienteInter) this.getDslam();
        } else {
            throw new FuncIndisponivelDslamException();
        }
    }

    @Override
    public ValidacaoResult validar() {
        try {
            iniciar();
            this.valid = consultar();
            processar();
            if (this.valid.getResultado()) {
                return new ValidacaoResult(valid.getNome(), valid.getMensagem(), valid.getResultado());
            } else {
                try {
                    corrigir();
                } catch (FalhaAoCorrigirException e) {
                    return new ValidacaoResult(valid.getNome(), fraseFalhaCorrecao(), Boolean.FALSE);
                }
            }
            return new ValidacaoResult(valid.getNome(), valid.getMensagem(), valid.getResultado());
        } catch (Exception ex) {
            return new ValidacaoResult(valid.getNome(), ex.getMessage(), Boolean.FALSE);
        }
    }

    protected abstract void corrigir() throws FalhaAoCorrigirException;

    protected abstract String fraseCorrecaoOk();

    protected abstract String fraseFalhaCorrecao();

}
