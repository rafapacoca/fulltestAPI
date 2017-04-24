/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.validacao.manobra;

import model.Motivos;
import model.dslam.consulta.metalico.TabelaRedeMetalico;
import model.validacao.ValidacaoRede;

/**
 *
 * @author G0042204
 */
public class ValidacaoRedeManobra extends ValidacaoRede {

    private transient Motivos m;

    public ValidacaoRedeManobra(TabelaRedeMetalico i, Motivos mot) {
        super(i);
        this.m = mot;

    }

    @Override
    public Boolean validar() {
        if (resyncA() && isCrcOk()) {
            this.setMensagem("Falha de rede. Quedas.");
        } else if (resyncA() && !isCrcOk()) {
            this.setMensagem("Falha de rede. Taxa de erro e quedas.");
        } else if (pctA()) {
            this.setMensagem("Possível falha de porta ou modem.");
        } else if (resyncB() && isCrcOk()) {
            this.setMensagem("Possível falha de porta ou modem.");
        } else if (resyncB() && !isCrcOk()) {
            this.setMensagem("Falha de rede.Taxa de erro e quedas.");
        } else if (resyncC() && pctB() && isCrcOk()) {
            this.setMensagem("Rede confiável, CRC dentro do padrão, pouca ou nenhuma queda.");
        } else if (resyncC() && !pctB() && isCrcOk()) {
            this.setMensagem("Quantidade de pacotes insuficiente para validacao");
        } else if (resyncC() && !isCrcOk()) {
            this.setMensagem("Falha de rede. Taxa de erro.");
        }
        if (m.equals(Motivos.SEMAUTH)) {
            this.setResultado(true);
            return true;
        } else if (m.equals(Motivos.SEMSINC)) {
            if ((pctA()) || (resyncB() && !isCrcOk()) || (resyncC() && pctB() && isCrcOk())) {
                this.setResultado(true);
                return true;
            }
        } else if (m.equals(Motivos.QUEDA)) {
            if (!resyncC() && isCrcOk()) {
                setResultado(true);
                return true;
            }
        } else if (m.equals(Motivos.SEMNAVEG)) {
            if (pctA() || isCrcOk()) {
                setResultado(true);
                return true;
            }
        } else if (m.equals(Motivos.SEMVEL)){
            setResultado(false);
            return false;
        }

        this.setResultado(false);
        return false;
    }
}