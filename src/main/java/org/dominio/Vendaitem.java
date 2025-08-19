package org.dominio;

import java.math.BigDecimal;

public class Vendaitem {
    private Venda venda;
    private Produto produto;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal total;
    private BigDecimal desconto;

    public Vendaitem() {
    }

    public Vendaitem(Venda venda, Produto produto, Integer quantidade, BigDecimal preco, BigDecimal total, BigDecimal desconto) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.total = total;
        this.desconto = desconto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
}
