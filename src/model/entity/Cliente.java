/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import bean.ossturbonet.oss.gvt.com.GetInfoOut;
import model.dslam.consulta.TabelaParamAbstract;

/**
 *
 * @author G0041775
 */
public class Cliente extends AbstractEntity {

    private String nome, designador;

    private GetInfoOut cadastro;

    private TabelaParamAbstract tabela;

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesignador() {
        return designador;
    }

    public void setDesignador(String designador) {
        this.designador = designador;
    }

    public GetInfoOut getCadastro() {
        return cadastro;
    }

    public TabelaParamAbstract getTabela() {
        return tabela;
    }

    public void setTabela(TabelaParamAbstract tabela) {
        this.tabela = tabela;
    }
}
