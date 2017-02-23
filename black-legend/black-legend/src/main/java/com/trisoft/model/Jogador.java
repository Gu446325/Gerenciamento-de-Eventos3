package com.trisoft.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jogador")
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;
	private Posicao posicao = Posicao.MC;
	private BigDecimal salario = BigDecimal.ZERO;
	private BigDecimal valorTransferenciaMaisEncargos = BigDecimal.ZERO;
	private Integer duracaoContrato = 1;
	private boolean integrado = true;

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "jogador_seq")
	// @SequenceGenerator(name = "jogador_seq", sequenceName = "jogador_bd_seq",
	// allocationSize = 1)
	// @Column(name="id", columnDefinition="serial")

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "serial", insertable = false, updatable = false)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Column(nullable = false, length = 30)
	@NotNull(message = "O Nome não pode estar nulo seu animal")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "valor_transferencia", nullable = false)
	public BigDecimal getValorTransferenciaMaisEncargos() {
		return valorTransferenciaMaisEncargos;
	}

	public void setValorTransferenciaMaisEncargos(BigDecimal valorTransferenciaMaisEncargos) {
		this.valorTransferenciaMaisEncargos = valorTransferenciaMaisEncargos;
	}

	@Column(nullable = false)
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Column(nullable = false)
	public boolean isIntegrado() {
		return integrado;
	}

	public void setIntegrado(boolean integrado) {
		this.integrado = integrado;
	}

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	@Column(name = "duracao_contrato", nullable = false)
	// @NotNull(message = "O contrato não pode estar nulo seu animal")
	public Integer getDuracaoContrato() {
		return duracaoContrato;
	}

	public void setDuracaoContrato(Integer duracaoContrato) {
		this.duracaoContrato = duracaoContrato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Transient
	public boolean isAcabando() {
		if (duracaoContrato == 1) {
			return true;
		}
		return false;
	}
}
